
package reconhecimento;

import bean.TreinamentoBEAN;
import java.awt.Color;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
//import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.CV_BGR2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;
import org.bytedeco.opencv.opencv_face.FisherFaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import viw.Config;

/**

Esta é a classe para gerar o treinamentodo do algoritmo,
Antes de executar essa classe, certifique-se de ter fotos de 2 pessoas diferentes, com 25 fotos de cada uma.

apague todas as fotos do pacote "fotos" para que o pacote fique vazio, em seguida execute a classe "Captura", de
o numero de ID para a pessoa e va pressionando a tecla "q" para o sistema ir tirando as fotos.

certifique-se de ter excluido  os arquivos "classificadorEigenFaces.yml" "classificadorFisherFaces.yml" "classificadorLBPH.yml"
* pois será gerado novos arquivos.

então seguido os passos acima pode ser executado esta classe "treinamento";
 **/

public class Treinamento_LBPH extends JFrame{
    
    JProgressBar barra = new JProgressBar();
    //construtor para herdar do JFrame
    
    public Treinamento_LBPH(){
        configuraJanela();
        barra.setBounds(40,40, 500, 20);//este metodo pq no metodo configurarJanela esta setLayout(null)
        add(barra);
    }
/*
    public Treinamento_LBPH(Config aThis, boolean b) {
        configuraJanela();
        barra.setBounds(40,40, 500, 20);//este metodo pq no metodo configurarJanela esta setLayout(null)
        //barra.setStringPainted(true);
        //barra.setValue(50);
        //barra.setMaximum(1000);
        barra.setIndeterminate(true);
        //barra.setForeground(Color.black);
        add(barra);
    }*/
    public void configuraJanela(){
        setTitle("Treinamento");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 100);
        setLocationRelativeTo(null);
        setVisible(true);
        barra.setBounds(20,20, 550, 20);//este metodo pq no metodo configurarJanela esta setLayout(null)
        barra.setIndeterminate(true);
        add(barra);
    }
    
    public void treinamento(TreinamentoBEAN t){
    
        new Thread() {

            @Override
            public void run() {

                synchronized (this) {
                    configuraJanela();

        //  public void treinar(){
                    File diretorio = new File("src/fotos");
                    FilenameFilter filtroImagem = new FilenameFilter() {
                        //sobreescrever o metodo do java para filtrar apenas imagens   aula 010
                        @Override
                        public boolean accept(File file, String nome) {
                                return nome.endsWith(".jpg") || nome.endsWith(".gif") || nome.endsWith(".png");
                        }
                    };

                    File[] arquivos = diretorio.listFiles(filtroImagem);
                    //a linha abaixo cria um vetor de matrizes com o tamanho da quantidade de fotos armazenadas de cada uma das pessoas     aula 010
                    MatVector fotos = new MatVector(arquivos.length);

                    //armazenar os rotulos exemplo pessoa 1 e pessoa 2    
                    //Mat(numLinhas, colunas-seClasse1_ou_classe2, tipoDeDado32bits)
                    Mat rotulos = new Mat(arquivos.length, 1, CV_32SC1);
                    //abaoixo variavel para armazenar corretamente os rotulos
                    IntBuffer rotulosBuffer = rotulos.createBuffer();
                    int contador = 0;

                    for(File imagem: arquivos){
                        //imread-> faz a leitura da foto  (localFoto, constanteParaConverterScalaEmCinza)  
                      //  Mat foto = imread(imagem.getAbsolutePath(), opencv_imgcodecs.IMREAD_GRAYSCALE);
                        
                        Mat foto = imread(imagem.getAbsolutePath(), CV_BGR2GRAY);
                        //cvtColor(imagemColorido, imagemCinza, COLOR_BGRA2GRAY);//essa linha esta no Reconhecimento
                        //Mat foto = imread(imagem.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
                        //abaixo para saber se é pessoa 1 ou pessoa 2 - apenas para mostrar pessoa: 1.1    
                        int classe = Integer.parseInt(imagem.getName().split("\\.")[1]);
                        //System.out.println("eitaaaaaa"+classe);
                        org.bytedeco.opencv.global.opencv_imgproc.resize(foto, foto, new Size(160, 160));
                        
                        //abaixo serve para dizer a qual classe cada foto pertence   
                        fotos.put(contador, foto);
                        rotulosBuffer.put(contador, classe);
                        contador++;    
                    }
                    //nestas linha abaixo prestar atencao na importacao das bibliotecas
                    //se a biblioteca javaCv for mais nova que 1.3 olhar conversao de nome de metodos para essas linhas abaixo.
                    /*
                    FaceRecognizer eigenfaces = createEigenFaceRecognizer();
                    FaceRecognizer fisherfaces = createFisherFaceRecognizer();
                    FaceRecognizer lbph = createLBPHFaceRecognizer();
                    */
             /**
              * ********************************************************************************
              *                                                                                *
                PARA FAZER O TRINAMNTO DO ALGORITMO       *
              *                                                                                *
              **********************************************************************************
              ***/       
                    //AUMENTAR OU DIMINUIR O PRIMEIRO NUMERO ALTERA A QUALIDADE. 50 É MAIS DO QUE SUFICIENTE
                    //LEMBRAR DE REFAZER O TREINAMENTO SEMPRE QUE FOR ALTERADO PARA OBTER RESULTADOS.
                    //ASSISTIR A AULA DO YOUTUBE, LINK NO MATERIAL, PARA CALCULAR O THRESHHOLD
                    //(QUALIDADE, THRASHOLD)
                 //   EigenFaceRecognizer eigenfaces = EigenFaceRecognizer.create(50, 0);
                    //EigenFaceRecognizer eigenfaces = EigenFaceRecognizer.create();
                 //   FisherFaceRecognizer fisherfaces = FisherFaceRecognizer.create();    
             /*     
              *      alterando o valor do LBPH para para almentar a confiança. 
              * ATE ESTA AULA ESTAVA SEM PARAMETRO "LBPHFaceRecognizer.create();"
              */
                    //LBPHFaceRecognizer lbph = LBPHFaceRecognizer.create(2,9,9,9,1);
                    LBPHFaceRecognizer lbph = LBPHFaceRecognizer.create(
                        t.getRaio(),
                        t.getVizinhos(),
                        t.getEixo_x(),
                        t.getEixo_y(),
                        t.getLimiar()
                    );
                /*
                    eigenfaces.train(fotos, rotulos);
                    //sera gerado um aquivo correspondente para cada classificador no referido caminho, esse sera o arquivo de aprendizagem
                    eigenfaces.save("src/recursos/classificadorEigenFaces.yml");
                    fisherfaces.train(fotos, rotulos);
                    fisherfaces.save("src/recursos/classificadorFisherFaces.yml");
                */
                    lbph.train(fotos, rotulos);
                    //lbph.save("src/recursos/classificadorLBPH.yml"); 
                    lbph.save("src/recursos/classificadorLBPH.yml"); 
                    JOptionPane.showMessageDialog(null, "Treinamento finalizado com sucesso!");
                    setVisible(false);
                    dispose();
                        //}
                } 
            }
            
        }.start();
    }
}
