
package reconhecimento;

import java.text.DecimalFormat;
import java.util.concurrent.Delayed;
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
import org.bytedeco.opencv.opencv_core.AbstractScalar;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;





/*

Esta classe é praticamente igual a classe "Captura", apenas pequenas modificações

*/




public class Reconhecimento_LBPH{
    
    
    
    public static void main(String[] args) throws FrameGrabber.Exception, InterruptedException {
        

        OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();
        //o "0" no final do parametro indica o primeiro dispositivo; comeca em zero, se tivesse mais uma entao a segunda seria a 1.
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        
        //abaixo a primeira opcao esta vazia pelo fato de o primeiro id ser reconhecido como 1 na tirada das fotos.   Aula 013 7:44
        String[] pessoas = {"", "Joao", "Rooh", "Alan"};
         
        camera.start();
        
        CascadeClassifier detectorFace = new CascadeClassifier("src/recursos/haarcascade-frontalface-alt2.xml");
        
        
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
        CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
        Frame frameCapturado = null;

        Mat imagemColorido  = new Mat();
    
        while ((frameCapturado = camera.grab()) != null){
            
            imagemColorido = convertemat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            cvtColor(imagemColorido, imagemCinza, COLOR_BGRA2GRAY);
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
                
             
                //para indeicar qual rotulo é; se é rotulo 1 ou rotulo 2. numero 1 passado por padrao no metodo como parametro para evitar erros.  aula 013 3:52
                IntPointer rotulo = new IntPointer(1);
                DoublePointer confianca = new DoublePointer(1);
               
                reconhecedor.predict(faceCapturada, rotulo, confianca);
                int predicao = rotulo.get(0);
                
                String nome;
                //abaixo se verificacao retornar -1 é porque nao encontrou nenhuma classe
                if(predicao == -1){
                    nome = "Desconhecido";
                }else{
    /**********************************************************************************/  
                /**    TESTE     **/
                    String teste = String.valueOf(confianca.get());
                    double teste2 = Double.parseDouble(teste);
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.format(teste2); 
                    //System.out.println(df);
                    //System.out.println(String.format("%.1f", teste2));
                 /*   
                    int aa = Math.max(dadosFace.tl().x() - 100, 0);
                    int bb = Math.max(dadosFace.tl().y() - 100, 0);
                    putText(imagemColorido, String.format("%.1f", teste2), new Point(aa, bb), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 255, 0, 0));
                 */
                int aa = Math.max(20, 0);
                int bb = Math.max(20, 0);
                String texto = String.format("%.1f", teste2);
                String eita = texto.replace(",", ".");
                Double num = Double.parseDouble(eita);
                if(num>50.0){
                    /* teste
                    putText(imagemColorido, texto+" %", new Point(aa, bb), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 0, 156, 0));
                    Thread.sleep(1000);
                    */
                    //new Thread(t1).start();
                }
    /**********************************************************************************/
                    
                    
                    nome = pessoas[predicao]+ " - " + confianca.get(0);
                }
                
                //para colocar o nome ao lado da foto que foi reconhecida   aula 013 3:51
                int x = Math.max(dadosFace.tl().x() - 10, 0);
                int y = Math.max(dadosFace.tl().y() - 10, 0);
                //linha abaixo coloca o texto para aparecer na webcam
                putText(imagemColorido, nome, new Point(x, y), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 255, 0, 0));
                
            }
            if(cFrame.isVisible()){
                    cFrame.showImage(frameCapturado);
                }    

        }
        
        
        cFrame.dispose();
        camera.stop();
        
    }
    /******teste******/
    
    
    /*
    private static Runnable t1 = new Runnable() {
        public void run(String texto, Mat imagemColorido, int aa, int bb) {
            try{
                putText(imagemColorido, texto+" %", new Point(aa, bb), FONT_HERSHEY_PLAIN, 1.4, new Scalar(0, 0, 156, 0));
            } catch (Exception e){}
        }

        @Override
        public void run() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    */
}
