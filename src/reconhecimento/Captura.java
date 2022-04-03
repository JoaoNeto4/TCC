
package reconhecimento;

import java.awt.event.KeyEvent;
import java.util.Scanner;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
//import static org.bytedeco.opencv.global.opencv_cudaimgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
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

public class Captura {
    
    
    public static void main(String[] args, Object KeyEvent) throws FrameGrabber.Exception, InterruptedException {
        
        KeyEvent tecla = null;
        OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();
        //o "0" no final do parametro indica o primeiro dispositivo; comeca em zero, se tivesse mais uma entao a segunda seria a 1.
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(0);
        camera.start();
        
        
        CascadeClassifier detectorFace = new CascadeClassifier("src/recursos/haarcascade-frontalface-alt.xml");
        
        
        //essa linha cria a tela de frame automatico 
        //os parametros sao: ("titulo janela", tamanho da janela / tamanho janela)
        // poderia ser tbm: ("titulo", 1);
        CanvasFrame cFrame = new CanvasFrame("Previw", CanvasFrame.getDefaultGamma() / CanvasFrame.getDefaultGamma());
        Frame frameCapturado = null;
        
        
        Mat imagemColorido  = new Mat();
        
        //variavel para armazenar fotos para a comparacao no reconhecimento facil
        int numeroAmostras = 25;
        int amostra = 1;
        System.out.println("digite seu id: ");
        Scanner cadastro= new Scanner(System.in);
        int idPessoa = cadastro.nextInt();
        
        while ((frameCapturado = camera.grab()) != null){
            
            imagemColorido = convertemat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            cvtColor(imagemColorido, imagemCinza, COLOR_BGRA2GRAY);
            //na linha abaixo, ficarão armazenadas todas as faces detectadas
            RectVector facesDetectadas = new RectVector();
            //(imagem, facesDetectadas, ScaleFactory-EscalaDaImagem, numeroDeVisinhos, Flag-usadoEmVersoesAntigas, tamanhoMinimoImagem, TamanhoMaximoImagem)
            detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 1, 0, new Size(160, 160), new Size(500, 500));
            
            //linha abaixo evita erros com o javaCv ao pressionar a tecla para capturar as 25 fotos    aula 008 12:00
            if(tecla == null){
                //faz a tecla receber um delay de 50 milisegundos esperando
                tecla = cFrame.waitKey(50);
            }
            
            for(int i=0; i<facesDetectadas.size(); i++){
                //Rect significa o retangulo ao redor da face 
                Rect dadosFace = facesDetectadas.get(0);
                //vamos desenhar um retangulo na face colorida quando reconhecer uma face;   padrao R,G,B, naoUsado
                rectangle(imagemColorido, dadosFace, new Scalar(0, 0, 255, 0)); 
                
                Mat faceCapturada = new Mat(imagemCinza, dadosFace);
                //linha abaixo padroniza o tamanho das imagens para 160 x 160    aula 008
               
                resize(faceCapturada, faceCapturada, new Size(160, 160));
                //linha abaixo evita erros com o javaCv ao pressionar a tecla para capturar as 25 fotos    aula 008 12:00
                if(tecla == null){
                  //faz a tecla receber um delay de 5 milisegundos esperando
                    tecla = cFrame.waitKey(5);
                }
                if(KeyEvent != null){
                    if(tecla.getKeyChar() == 'q'){
                        if(amostra <= numeroAmostras){
                            imwrite("src/fotos/pessoa."+idPessoa+"."+amostra+".jpg", faceCapturada);
                            System.out.println("foto"+amostra+" capturada\n");
                            amostra++;
                        }
                    }
                    //linha abaixo evita erros com o javaCv ao pressionar a tecla para capturar as 25 fotos    aula 008 13:00
                    tecla = null;
                }
            }
            //linha abaixo evita erros com o javaCv ao pressionar a tecla para capturar as 25 fotos    aula 008 13:00
                if(tecla == null){
                  //faz a tecla receber um delay de 20 milisegundos esperando
                  //aqui temos (10 milisegundos)500ms para deixar mais comodo o pc tirar a foto e armazenar em disco
                    tecla = cFrame.waitKey(20);
                }
            
            if (cFrame.isVisible()){
                cFrame.showImage(frameCapturado);
            }
            
            //linha abaixo: se ja tirou as 25 fotos dará um break e sairá fora do while
            if(amostra > numeroAmostras){
                break;
            }
        }
        
        cFrame.dispose();
        camera.stop();
  
    }
}
