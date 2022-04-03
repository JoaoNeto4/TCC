
package viw;

import DAO.PessoaDAO;
import bean.Controle;
import bean.Pessoa;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
//import static org.bytedeco.opencv.global.opencv_cudaimgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
//erro: Exception in thread "main" java.lang.UnsatisfiedLinkError: no jniopencv_cudaarithm in java.library.path
//gooogle mandou importar a biblioteca abaixo:
//import org.bytedeco.javacpp.opencv_imgproc. *;
//porem baixei:
import static org.bytedeco.opencv.global.opencv_imgproc.*;
import org.bytedeco.opencv.opencv_core.Point;
import viw.CadPessoas.DaemonThread;



public class CadPessoas extends javax.swing.JDialog {
    
    /****
     * para gerar apartir do 3 tela de dialog a linha- this.setLocationRelativeTo(null); nao funciona
     * Clicar no painel do frame do projeto, botao direito, propriedades, código,
     * em: Politica de tamanho de form, selecionar: Gerar codigo de redimensionamento,
     * marcar o checkbox: Gerar centralizado.
     */ 
    
    
    
/**
 * na documentacao do opencv pede que seja usado em torno de 25 imagens
 * para que exista um desempenho satisfatorio no reconhecimento.
 */
    boolean salvar = true;
    //int idPes = -1;
    int idControle = -1;
    int numAmostra=0;
    int cam=0;
    int idProxPessoa=-1;
    int idPess=-1;
    String classificador="";
    String camAmostras="";
    String nome="";
    boolean ativo=false;
    private ListaPessoas parent;
    //String camAmostra="";
    /**********/
    private DaemonThread myThread = null;
    //resolve problema no main
    //dialog = new CadPessoas(new javax.swing.JFrame(), true);//estava assim e mudei para
    //o que esta embaixo. resolveu problema de a thread ficar atraz da tela.
    //-------> dialog = new CadPessoas();

    public CadPessoas(java.awt.Frame parent, boolean modal) throws IOException {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);//inicializa o JFrame no centro da tela
    
        txtCaminhoImagens.setEditable(false);
        txtGrupo.setEditable(false);
        getProp();
    }

   public CadPessoas() throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);//inicializa o JFrame no centro da tela
        txtCaminhoImagens.setEditable(false);
        txtGrupo.setEditable(false);
        getProp();
    }

    CadPessoas(java.awt.Frame parent, boolean b, Pessoa pes) {
        super(parent, b);
        initComponents();
        this.setLocationRelativeTo(null);//inicializa o JFrame no centro da tela
        salvar=false;
        txtCaminhoImagens.setEditable(false);
        btnCamera.setEnabled(false);
        btnCamera.setVisible(false);
        txtGrupo.setEditable(false);
        txtNome.setText(pes.getNome());
        txtGrupo.setText(pes.getControle().getDescricao());
        txtDescricao.setText(pes.getDescricao());
        idPess=pes.getId();
        idControle=pes.getControle().getId();
        ativoPessoa.setSelected(pes.getAtivo());
        /*if(pes.getAtivo()){
            ativoPessoa.setSelected(true);
            ativo=true;
        }*/
        try {
            getProp();
        } catch (IOException ex) {
            Logger.getLogger(CadPessoas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    
    
    public Pessoa retornaObjeto(){
        Pessoa p = new Pessoa();
        Controle c = new Controle();
        p.setId(idPess);
        p.setNome(txtNome.getText());
        p.setDescricao(txtDescricao.getText());
        c.setId(idControle);
        c.setDescricao(txtGrupo.getText());
        if(ativoPessoa.isSelected()){
            p.setAtivo(true);
        }else{
            p.setAtivo(false);
        }
        p.setControle(c);
        
        System.out.println("retorno nome: "+p.getNome());
        System.out.println("retorno id pessoa: "+p.getId());
        System.out.println("retorno id controle: "+p.getControle().getId());
        System.out.println("retorno descricao: "+p.getDescricao());
        System.out.println("retorno ativo: "+p.getAtivo());
        
        return p;
    }
    
    public boolean validaCampos(){
        if(txtNome.getText() == ""){
            JOptionPane.showMessageDialog(null, "Campo Nome não pode ser vazio!");
            return false;
        }
        if(idControle == -1){
            JOptionPane.showMessageDialog(null, "Selecione um grupo de permissão!");
            return false;
        }
        if(txtDescricao.getText() == ""){
            JOptionPane.showMessageDialog(null, "Campo Descrição não pode ser vazio!");
            return false;
        }
   /*     if(){
            verificar se tirou todas as fotos
        }
    */    
        return true;
    }
    
    public void getProp() throws IOException{
        
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("/home/melena/NetBeansProjects/TCC-JoaoNeto/src/Config/config.properties");
        prop.load(file);
        
        try {
            numAmostra=Integer.parseInt(prop.getProperty("prop.server.amostras"));
            txtCaminhoImagens.setText(prop.getProperty("prop.server.camamostras"));
            cam=Integer.parseInt(prop.getProperty("prop.server.camera"));
            classificador=prop.getProperty("prop.server.classificador");
            camAmostras=prop.getProperty("prop.server.camamostras");
            PessoaDAO p = new PessoaDAO();
            idProxPessoa=p.IdProxPessoa();/**  pegar o id pra setar numero aos nomes  ***/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void recebeObjetoListaControle(Controle con){
        txtGrupo.setText(con.getDescricao());
        idControle=con.getId();
        this.idControle=con.getId();
    }
    
    public void setaNome(){
        nome= txtNome.getText();
    }
    

    class DaemonThread implements Runnable {

        public void run() {
            synchronized (this) {

            KeyEvent tecla=null;
            OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();
            //o "0" no final do parametro indica o primeiro dispositivo; comeca em zero, se tivesse mais uma entao a segunda seria a 1.
            OpenCVFrameGrabber camera = new OpenCVFrameGrabber(cam);
                try {
                    camera.start();
                } catch (FrameGrabber.Exception ex) {
                    Logger.getLogger(CadPessoas.class.getName()).log(Level.SEVERE, null, ex);
                }
            CascadeClassifier detectorFace = new CascadeClassifier(classificador);
            //essa linha cria a tela de frame automatico 
            //os parametros sao: ("titulo janela", tamanho da janela / tamanho janela)
            // poderia ser tbm: ("titulo", 1);
            CanvasFrame cFrame = new CanvasFrame("Captura de Imagens", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
            Frame frameCapturado = null;
            Mat imagemColorido  = new Mat();
            //variavel para armazenar fotos para a comparacao no reconhecimento facil
            //video 008
            int numeroAmostras = numAmostra;
            int amostra = 1;
            JOptionPane.showMessageDialog(null, "Pressione 'Enter' Para Capturar as Imagens.");

        
                try {
                    while (cFrame.isVisible() && (frameCapturado = camera.grab()) != null){
                        
                        
                        //imagemColorido = convertemat.convert(frameCapturado);
                        imagemColorido = convertemat.convert(frameCapturado);
                        
                        Mat imagemCinza = new Mat();
                        cvtColor(imagemColorido, imagemCinza, CV_BGR2GRAY);
                        //cvtColor(imagemColorido, imagemCinza, CV_BGR2GRAY);
                        //na linha abaixo, ficarão armazenadas todas as faces detectadas
                        RectVector facesDetectadas = new RectVector();
                        //(imagem, facesDetectadas, ScaleFactory-EscalaDaImagem, numeroDeVisinhos, Flag-usadoEmVersoesAntigas, tamanhoMinimoImagem, TamanhoMaximoImagem)
                        detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 1, 0, new Size(160, 160), new Size(500, 500));
                        //linha abaixo evita erros com o javaCv ao pressionar a tecla para capturar as 25 fotos    aula 008 12:00
                        if(tecla == null){
                            try {
                                //faz a tecla receber um delay de 50 milisegundos esperando
                                tecla = cFrame.waitKey(50);
                                ////////////////////////////System.out.println("bostica");
                            } catch (InterruptedException ex) {
                                Logger.getLogger(CadPessoas.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        for(long i=0; i<facesDetectadas.size(); i++){
                            //Rect significa o retangulo ao redor da face
                            Rect dadosFace = facesDetectadas.get(0);
                            //vamos desenhar um retangulo na face colorida quando reconhecer uma face;   padrao R,G,B, naoUsado
                            rectangle(imagemColorido, dadosFace, new Scalar(0, 0, 255, 0));
                            
                            Mat faceCapturada = new Mat(imagemCinza, dadosFace);
                            //linha abaixo padroniza o tamanho das imagens para 160 x 160    aula 008
                            org.bytedeco.opencv.global.opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));
                            //linha abaixo evita erros com o javaCv ao pressionar a tecla para capturar as 25 fotos    aula 008 12:00
                            
                            //para mostrar contador de fotos junto com o retangulo
                            int x = Math.max(dadosFace.tl().x() - 10, 0);
                            int y = Math.max(dadosFace.tl().y() - 10, 0);
                            //linha abaixo coloca o texto para aparecer na webcam
                            putText(imagemColorido, amostra+"/"+(numAmostra-1), new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 0, 255, 0));
                
                            
                            if(tecla == null){
                                //faz a tecla receber um delay de 5 milisegundos esperando
                                tecla = cFrame.waitKey(3);
                                //System.out.println("eitaaaaaaaaaaaaa");
                            }
                            //addKeyListener (this);
                            if(tecla != null){
                                //if(tecla.getKeyChar() == 'q'){
                                if(tecla.getKeyCode() == KeyEvent.VK_ENTER){
                                    if(amostra <= numeroAmostras){
                                        imwrite(camAmostras+nome+"."+idProxPessoa+"."+amostra+".jpg", faceCapturada);
                                        //JOptionPane.showMessageDialog(null, "Imagem "+amostra+"/"+numAmostra+" capturada!");
                                        System.out.println("foto"+amostra+" capturada\n");
                                        amostra++;
                                        tecla = null;
                                    }
                                }else if(tecla.getKeyChar() == 'a'){
                                    //JOptionPane.showMessageDialog(null, "merda2");
                                    System.exit(0);
                                    tecla = null;
                                    
                                }
                                //linha abaixo evita erros com o javaCv ao pressionar a tecla para capturar as 25 fotos    aula 008 13:00
                                
                            }
                        }
                        //linha abaixo evita erros com o javaCv ao pressionar a tecla para capturar as 25 fotos    aula 008 13:00
                        if(tecla == null){
                            //faz a tecla receber um delay de 20 milisegundos esperando
                            //aqui temos (10 milisegundos)500ms para deixar mais comodo o pc tirar a foto e armazenar em disco
                            tecla = cFrame.waitKey(60);
                        }
                        
                        if (cFrame.isVisible()){
                            cFrame.showImage(frameCapturado);
                        }                    
                        
                        //linha abaixo: se ja tirou as 25 fotos dará um break e sairá fora do while
                        if(amostra > numeroAmostras){
                            break;
                        }
                    }       } catch (FrameGrabber.Exception ex) {
                    Logger.getLogger(CadPessoas.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CadPessoas.class.getName()).log(Level.SEVERE, null, ex);
                }
        cFrame.dispose();
                try {
                    camera.stop();
                } catch (FrameGrabber.Exception ex) {
                    Logger.getLogger(CadPessoas.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        
    }
        


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        txtCaminhoImagens = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        ativoPessoa = new javax.swing.JCheckBox();
        txtGrupo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnCamera = new javax.swing.JButton();
        btnPesqGrupo = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.foreground"));

        btnCancelar.setText("Cancelar");
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarMouseReleased(evt);
            }
        });

        jLabel4.setText("Descrição");

        jLabel1.setText("Nome");

        ativoPessoa.setText("Ativo");

        jLabel2.setText("Grupo");

        btnCamera.setText("Fotos");
        btnCamera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCameraMouseReleased(evt);
            }
        });

        btnPesqGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/LupaPesquisar.png"))); // NOI18N
        btnPesqGrupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPesqGrupoMouseReleased(evt);
            }
        });

        jLabel3.setText("Imagens");

        btnSalvar.setText("Salvar");
        btnSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnSalvarMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCaminhoImagens, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ativoPessoa))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPesqGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ativoPessoa))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPesqGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnCamera, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCaminhoImagens, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(505, 272));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCameraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCameraMouseReleased
        setaNome();
        myThread = new DaemonThread(); // Instancia o objeto da classe Thread
        Thread t = new Thread(myThread);
        //t.setDaemon(true);
        t.setDaemon(false);
        
        t.start();
        

    }//GEN-LAST:event_btnCameraMouseReleased

    private void btnSalvarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalvarMouseReleased
        PessoaDAO p = new PessoaDAO();
        try {
            if(salvar){
                if(validaCampos()){
                    p.inserir(retornaObjeto());
                }
            }else{
                if(validaCampos()){
                    p.alterar(retornaObjeto());
                    
                    this.dispose();
                     
                  /* 
                   // ListaPessoas lp = (ListaPessoas) parent;
                    ListaPessoas lp = (ListaPessoas) parent;
                    lp.setVisible(true);
                    this.dispose();
                    */
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSalvarMouseReleased

    private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseReleased
        this.dispose();
        
    }//GEN-LAST:event_btnCancelarMouseReleased

    private void btnPesqGrupoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesqGrupoMouseReleased
        ListaControle lp = new ListaControle(null, true, this);//nao mudar
        lp.setVisible(true);
    }//GEN-LAST:event_btnPesqGrupoMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                
                
                CadPessoas dialog = null;
                try {
                   //dialog = new CadPessoas(new javax.swing.JFrame(), true);//estava assim e mudei para
                   //o que esta embaixo. resolveu problema de a thread ficar atraz da tela.
                    dialog = new CadPessoas(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                } catch (IOException ex) {
                    Logger.getLogger(CadPessoas.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
               dialog.setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox ativoPessoa;
    private javax.swing.JButton btnCamera;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnPesqGrupo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtCaminhoImagens;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtGrupo;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
