
package reconhecimento;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
//import static org.bytedeco.javacpp.opencv_imgcodecs.CV_LOAD_IMAGE_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_core.CV_32SC1;
import static org.bytedeco.opencv.global.opencv_imgcodecs.IMREAD_GRAYSCALE;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Size;

import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;
import org.bytedeco.opencv.opencv_face.FisherFaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;



/**

Esta é a classe para gerar o treinamentodo do algoritmo,
Antes de executar essa classe, certifique-se de ter fotos de 2 pessoas diferentes, com 25 fotos de cada uma.

apague todas as fotos do pacote "fotos" para que o pacote fique vazio, em seguida execute a classe "Captura", de
o numero de ID para a pessoa e va pressionando a tecla "q" para o sistema ir tirando as fotos.

certifique-se de ter excluido  os arquivos "classificadorEigenFaces.yml" "classificadorFisherFaces.yml" "classificadorLBPH.yml"
* pois será gerado novos arquivos.

então seguido os passos acima pode ser executado esta classe "treinamento";
 **/







public class Treinamento {
    public static void main(String[] args) {
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
        
        //armazenar os rotulos exemplo pessoa 1 e pessoa 2      aula 010
        //Mat(numLinhas, colunas-seClasse1_ou_classe2, tipoDeDado32bits)
        Mat rotulos = new Mat(arquivos.length, 1, CV_32SC1);
        //abaoixo variavel para armazenar corretamente os rotulos
        IntBuffer rotulosBuffer = rotulos.createBuffer();
        int contador = 0;
        
        for(File imagem: arquivos){
            //imread-> faz a leitura da foto  (localFoto, constanteParaConverterScalaEmCinza)   aula 010 8:20
            Mat foto = imread(imagem.getAbsolutePath(), IMREAD_GRAYSCALE);//Mat foto = imread(imagem.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
            //abaixo para saber se é pessoa 1 ou pessoa 2 - apenas para mostrar pessoa: 1.1      aula 010 9:40
            int classe = Integer.parseInt(imagem.getName().split("\\.")[1]);
            //System.out.println(classe);
            resize(foto, foto, new Size(160, 160));
            //abaixo serve para dizer a qual classe cada foto pertence     aula 010  11:30
            fotos.put(contador, foto);
            rotulosBuffer.put(contador, classe);
            contador++;
            
            
        }
        
        //nestas linha abaixo prestar atencao na importacao das bibliotecas    aula 010    12:30
        //se a biblioteca javaCv for mais nova que 1.3 olhar conversao de nome de metodos para essas linhas abaixo.
        /*
        FaceRecognizer eigenfaces = createEigenFaceRecognizer();
        FaceRecognizer fisherfaces = createFisherFaceRecognizer();
        FaceRecognizer lbph = createLBPHFaceRecognizer();
        */
       
        //AUMENTAR OU DIMINUIR O PRIMEIRO NUMERO ALTERA A QUALIDADE. 50 É MAIS DO QUE SUFICIENTE
        //LEMBRAR DE REFAZER O TREINAMENTO SEMPRE QUE FOR ALTERADO PARA OBTER RESULTADOS.
        //ASSISTIR A AULA DO YOUTUBE, LINK NO MATERIAL, PARA CALCULAR O THRESHHOLD
        //(QUALIDADE, THRASHOLD)
        EigenFaceRecognizer eigenfaces = EigenFaceRecognizer.create(20, 0);
        //EigenFaceRecognizer eigenfaces = EigenFaceRecognizer.create();
        FisherFaceRecognizer fisherfaces = FisherFaceRecognizer.create();
        
 /*      
  *      alterando o valor do LBPH para para almentar a confiança. 
  * ATE ESTA AULA ESTAVA SEM PARAMETRO "LBPHFaceRecognizer.create();"
  */
        //LBPHFaceRecognizer lbph = LBPHFaceRecognizer.create(2,9,9,9,1);
        LBPHFaceRecognizer lbph = LBPHFaceRecognizer.create(2,10,10,10,1);
       
        
        eigenfaces.train(fotos, rotulos);
        //sera gerado um aquivo correspondente para cada classificador no referido caminho, esse sera o arquivo de aprendizagem
        //aula 010   15:50
        eigenfaces.save("src/recursos/classificadorEigenFaces.yml");
        fisherfaces.train(fotos, rotulos);
        fisherfaces.save("src/recursos/classificadorFisherFaces.yml");
        lbph.train(fotos, rotulos);
        lbph.save("src/recursos/classificadorLBPH.yml");
        
    }
    
    
}
