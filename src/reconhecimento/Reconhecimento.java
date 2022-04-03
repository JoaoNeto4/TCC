
package reconhecimento;

import DAO.RegistroDAO;
import bean.Pessoa;
import bean.Registro;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
//import static org.bytedeco.opencv.global.opencv_cudaimgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_BGR2GRAY;
//erro: Exception in thread "main" java.lang.UnsatisfiedLinkError: no jniopencv_cudaarithm in java.library.path
//gooogle mandou importar a biblioteca abaixo:
//import org.bytedeco.javacpp.opencv_imgproc. *;
//porem baixei:
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static org.opencv.imgcodecs.Imgcodecs.IMREAD_GRAYSCALE;




public class Reconhecimento {
    
    int contagemRegressiva = 5;
    boolean abrirPorta=true;
    String conArduino="";
    Socket s;  
    InetAddress inet;
    byte[] ipServer={ (byte)192 , (byte)168 , (byte)0, (byte)107 };
    //inet = InetAddress.getByAddress(new byte[] { 192, 168, 0, 107 });

    /*
    //TESTEEE
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println(contagemRegressiva);
                contagemRegressiva--;
                abrirPorta=false;
                if (contagemRegressiva <= 0) {
                    System.out.println("Tempo esgotado!");
                    abrirPorta=true;
                    contagemRegressiva = 5;
                    scheduler.shutdown();
                }
            }
        };
    */
       // scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    //FIM TESTE
    
    public void abrePortao(String nome){
        Timer timer = new Timer();
        JFrame frame = new JFrame("Abrir portão!");
        frame.setSize(250, 100);
        frame.setUndecorated(true);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension d2 = new Dimension((d.width - frame.getWidth()) / 2, (d.height - frame.getHeight()) / 2);
        frame.setLocation(d2.width, d2.height);
        JLabel label = new JLabel();
        label.setSize(250, 100);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Acesso Liberado "+nome+"!");
        frame.add(label);
        frame.setVisible(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                frame.dispose();
                timer.cancel();
            }
        };
        timer.schedule(task, 4000L);
    }
        

    Thread t;
    RegistroDAO rd = new RegistroDAO();
    Registro rg = new Registro();
    Pessoa p = new Pessoa();
    
    
    
    public void registraPessoa(int pessoa){
        p.setId(pessoa);
        rg.setId_pessoa(p);
        //System.out.println("eitaaa"+rg.getId_pessoa().getId());
        Timestamp datahora = new Timestamp(System.currentTimeMillis());
        rg.setDatahora(datahora);
        rg.setDescricao("Portao 01");
        try {
            rd.inserir(rg);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public int retornaDiaSemanaNUM(String nome){
        Date d = new Date(); 
        Calendar c = new GregorianCalendar(); 
        c.setTime(d); 
        int diaSem = 0; 
        int dia = c.get(c.DAY_OF_WEEK);
        //pega o dia da semana que estamos
        switch(nome){
            case "Domingo":
                diaSem=1;
                break;
            case "Segunda":
                diaSem=2;
                break;
            case "Terca":
                diaSem=3;
                break;
            case "Quarta":
                diaSem=4;
                break;
            case "Quinta":
                diaSem=5;
                break;
            case "Sexta":
                diaSem=6;
                break;
            case "Sabado":
                diaSem=7;
                break;
        }
        return diaSem;
    }

    
    public static int retornaDia(){
        Date d = new Date(); 
        Calendar c = new GregorianCalendar(); 
        c.setTime(d); 
        int dia = c.get(c.DAY_OF_WEEK);
        //pega o dia da semana que estamos
        switch(dia){
            case Calendar.SUNDAY: dia = 1;
                break; 
            case Calendar.MONDAY: dia = 2;
                break;
            case Calendar.TUESDAY: dia = 3;
                break; 
            case Calendar.WEDNESDAY: dia = 4;
                break; 
            case Calendar.THURSDAY: dia = 5;
                break; 
            case Calendar.FRIDAY: dia = 6;
                break; 
            case Calendar.SATURDAY: dia = 7;
                break; 
        }
        return(dia);
    }
    
    public boolean verificaDataHora(Pessoa p){
        Date d = new Date(); 
        Calendar c = new GregorianCalendar(); 
        c.setTime(d); 
        String nome = "";
        
        Time HI, HF;
        int DI, DF;
        int inicio, fim, agora;
        DI=retornaDiaSemanaNUM(p.getControle().getDia_inicio());
        DF=retornaDiaSemanaNUM(p.getControle().getDia_fim());
        HI=p.getControle().getHora_inicio();
        HF=p.getControle().getHora_fim();
        
        long now = System.currentTimeMillis();
        Time horaAgora = new Time(now);
        int diaHoje=retornaDia();
        
        inicio=Integer.parseInt(HI.toString().replaceAll(":", ""));
        fim=Integer.parseInt(HF.toString().replaceAll(":", ""));
        agora=Integer.parseInt(horaAgora.toString().replaceAll(":", ""));
        
        if(diaHoje>=DI && diaHoje<=DF){
                /*
                //after=depois de
                //before=antes de
                */
                System.out.println("hora agora: "+horaAgora);
                System.out.println("hora in: "+HI);
                System.out.println("hora fi: "+HF);
             /*
                tive que converter o Time em int para poder verificar no if,
                Time.after ou Time.before estava pegando apenas a hora sem os minutos
             */
            if(agora>inicio && agora<fim){ 
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
   /* 
    public String retornaDiaSemana(Pessoa p){
        Date d = new Date(); 
        Calendar c = new GregorianCalendar(); 
        c.setTime(d); 
        String nome = ""; 
        int dia = c.get(c.DAY_OF_WEEK);
        //pega o dia da semana que estamos
        switch(dia){
            case Calendar.SUNDAY: nome = "Domingo";
                break; 
            case Calendar.MONDAY: nome = "Segunda";
                break;
            case Calendar.TUESDAY: nome = "Terça";
                break; 
            case Calendar.WEDNESDAY: nome = "Quarta";
                break; 
            case Calendar.THURSDAY: nome = "Quinta";
                break; 
            case Calendar.FRIDAY: nome = "Sexta";
                break; 
            case Calendar.SATURDAY: nome = "sabado";
                break; 
        }
        if(p.getControle().getDia_inicio().equals(nome)){
            
        }
        
        return(nome);
    }
    public static Date retornaHora(){
        Date dataHoraAtual = new Date();
        //String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        //String hora = new SimpleDateFormat("HH:mm").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm").format(dataHoraAtual);
        //System.out.println(hora);
        SimpleDateFormat s = new SimpleDateFormat("HH:mm");
        Date h = new Date();
        try {
            h=s.parse(hora);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return h;
    }
    */
    public void reconhece(List<Pessoa> pessoas){
        //para uso da conexao do arduino
        
        new Thread() {

            @Override
            public void run() {

                synchronized (this) {

                    OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();
                    //o "0" no final do parametro indica o primeiro dispositivo; comeca em zero, se tivesse mais uma entao a segunda seria a 1.
                    OpenCVFrameGrabber camera = new OpenCVFrameGrabber(2);

                    //abaixo a primeira opcao esta vazia pelo fato de o primeiro id ser reconhecido como 1 na tirada das fotos.   Aula 013 7:44
                    //String[] pessoas = {"", "Joao", "Rooh","barbudo"};

                    try {
                        camera.start();
                    } catch (FrameGrabber.Exception ex) {
                        Logger.getLogger(Reconhecimento.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    CascadeClassifier detectorFace = new CascadeClassifier("src/recursos/haarcascade-frontalface-alt.xml");

        /****************************************************************************************
         * A unica coisa que muda do EigenFace para o FisherFace sao apenas as linhas abaixo
         * desde esta até "reconhecedor.read("src/recursos/classificadorFisherFaces.yml");"
         * ate a linha 80.
         * **************************************************************************************
        */   
                  
                    //FisherFaceRecognizer reconhecedor = FisherFaceRecognizer.create();
                    //reconhecedor.read("src/recursos/classificadorFisherFaces.yml");
                    LBPHFaceRecognizer reconhecedor = LBPHFaceRecognizer.create();
                    reconhecedor.read("src/recursos/classificadorLBPH.yml");

                    //essa linha cria a tela de frame automatico 
                    //os parametros sao: ("titulo janela", tamanho da janela / tamanho janela)
                    // poderia ser tbm: ("titulo", 1);
                    //CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
                    CanvasFrame cFrame = new CanvasFrame("Reconhecimento", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());

                    Frame frameCapturado= null;

                    Mat imagemColorido  = new Mat();
        // teste// cFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        try {

                            byte[] ipServer={ (byte)192 , (byte)168 , (byte)0, (byte)107 };
                            String conArduino="";
                            Socket s=null;  
                            InetAddress inet;

                            String cmd="ping -c 2 192.168.0.107";
                            Process myProcess = Runtime.getRuntime().exec(cmd);
                            myProcess.waitFor();
                            if(myProcess.exitValue() == 0) {
                                inet = InetAddress.getByAddress(ipServer);
                                System.out.println("Enviando Ping Request para saber se está ativo " + inet);
                                s = new Socket(InetAddress.getByAddress(ipServer),5000);

                            } else {
                                JOptionPane.showMessageDialog(null, "Arduino não está ativo na rede!");
                            }

                            OutputStream sout=s.getOutputStream();
                           // DataInputStream sin=new DataInputStream(s.getInputStream());
                            DataInputStream keyboard=new DataInputStream(System.in);




                            while ((frameCapturado = camera.grab()) != null){
                      //         if(cFrame.isShowing()){

                                imagemColorido = convertemat.convert(frameCapturado);
                                Mat imagemCinza = new Mat();
                                //cvtColor(imagemColorido, imagemCinza, COLOR_BGRA2GRAY);
                                cvtColor(imagemColorido, imagemCinza, COLOR_BGRA2GRAY);//CV_BGR2GRAY
                                //na linha abaixo, ficarão armazenadas todas as faces detectadas
                                RectVector facesDetectadas = new RectVector();
                                //(imagem, facesDetectadas, ScaleFactory-EscalaDaImagem, numeroDeVisinhos, Flag-usadoEmVersoesAntigas, tamanhoMinimoImagem, TamanhoMaximoImagem)
                                detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 2, 0, new Size(150, 150), new Size(500, 500));


                                //quando entra neste "for" ele ja conseguiu detectar uma face
                                for(int i=0; i<facesDetectadas.size(); i++){
                                    //Rect significa o retangulo ao redor da face
                                    Rect dadosFace = facesDetectadas.get(i);
                                    //vamos desenhar um retangulo na face colorida quando reconhecer uma face;   padrao R,G,B, naoUsado
                                    rectangle(imagemColorido, dadosFace, new Scalar(0, 255, 0, 0));

                                    Mat faceCapturada = new Mat(imagemCinza, dadosFace);
                                    //linha abaixo padroniza o tamanho das imagens para 160 x 160    aula 008
                                    resize(faceCapturada, faceCapturada, new Size(160, 160));


                                    //Aula 013
                                    //para indeicar qual rotulo é; se é rotulo 1 ou rotulo 2. numero 1 passado por padrao no metodo como parametro para evitar erros.  aula 013 3:52
                                    IntPointer rotulo = new IntPointer(1);
                                    DoublePointer confianca = new DoublePointer(1);
                                    //Aula 013  5:12
                                    reconhecedor.predict(faceCapturada, rotulo, confianca);
                                    
                                    int predicao = rotulo.get(0);

                                    String nome;
                                    //abaixo se verificacao retornar -1 é porque nao encontrou nenhuma classe
                                    if(predicao == -1){
                                        nome = "Desconhecido";
                                        //System.out.println("desconhecido: Tam. Lista reconhec.: "+pessoas.size());
                                        
                                    }
                                    else{
                                        //System.out.println(pessoas.get(predicao).getId());
                                        nome = pessoas.get(predicao).getNome();//+confianca.get(0);
                                        System.out.println(pessoas.get(predicao).getNome()+confianca.get(0));
                                        //nome = pessoas.get(predicao).getNome()+ " - " + confianca.get(0);
                                        //nome = pessoas.get(predicao).getNome()+ " - " +num+"%";
                                        //System.out.println(nome);
                                        //System.out.println("conhecido: Tam. Lista reconhec.: "+pessoas.size());
                                    }

                                    //para colocar o nome ao lado da foto que foi reconhecida   aula 013 3:51
                                    int x = Math.max(dadosFace.tl().x() - 10, 0);
                                    int y = Math.max(dadosFace.tl().y() - 10, 0);
                                    //linha abaixo coloca o texto para aparecer na webcam
                                    //putText(imagemColorido, nome, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 255, 0, 0));
                                    if(predicao==(-1)){
                                        putText(imagemColorido, nome, new Point(x, y), FONT_HERSHEY_PLAIN, 2.1, new Scalar(0, 0, 255, 0));
                                    }else{
                                        if(pessoas.get(predicao).getAtivo().equals(true) && verificaDataHora(pessoas.get(predicao)) ){
                                            putText(imagemColorido, nome, new Point(x, y), FONT_HERSHEY_PLAIN, 2.1, new Scalar(0, 255, 0, 0));
                                            if(abrirPorta==true){

                                                t= new Thread(
                                                    new Runnable(){
                                                        public void run(){
                                                            try {
                                                                abrirPorta=false;

                                                                sout.write("a".getBytes());
                                                                registraPessoa(pessoas.get(predicao).getId());
                                                                abrePortao(pessoas.get(predicao).getNome());/////testeeeee
                                                                t.sleep(4000);
                                                                System.out.println("terminouuuuuu");
                                                                sout.write("b".getBytes());

                                                               abrirPorta=true;

                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }  
                                                        }
                                                    }
                                                );

                                                t.start();
                                                //contagemRegressiva = 5;
                                                //abrirPorta=false;
                                            }
                                        }else{
                                            putText(imagemColorido, nome, new Point(x, y), FONT_HERSHEY_PLAIN, 2.1, new Scalar(0, 0, 255, 0));
                                        }
                                    }

                                }

                                if(cFrame.isVisible()){
                                    cFrame.showImage(frameCapturado);
                                }else{
                                    camera.close();
                                    camera.stop();
                                }
                            }

                        } catch (FrameGrabber.Exception ex) {
                            //Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("FrameGrabber Finalizado!!");
                        }catch(NullPointerException ex){
                            System.out.println("Aplicação Finalizada!!");  
                        } catch (UnknownHostException ex) {
                            System.out.println("A aplicação não possui IP para o Arduino"+ex);
                        } catch (IOException ex) {
                            System.out.println("O IP não possui rota na rede"+ex);
                        } catch (InterruptedException ex) {
                            System.out.println("Não foi possivel 'pingar' no endereço IP");
                        }

                }  
            }
        }.start();
    } 
}
