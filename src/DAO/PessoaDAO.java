
package DAO;

import bean.Controle;
import bean.Pessoa;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import util.Conexao;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
//import static org.bytedeco.opencv.global.opencv_cudaimgproc.cvtColor;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
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


public class PessoaDAO {
    
    public static void inserir(Pessoa ps)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into PESSOA(id_controle, nome, descricao, ativo) values(?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, ps.getControle().getId());
        stmt.setString(2, ps.getNome());
        stmt.setString(3, ps.getDescricao());
        stmt.setBoolean(4, ps.getAtivo());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void excluir(Pessoa ps)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE from PESSOA where id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        System.out.println("eitaaa: "+ps.getId());
        stmt.setInt(1, ps.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void alterar(Pessoa ps)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE PESSOA SET id_controle=?, nome=?, descricao=?, ativo=? WHERE id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, ps.getControle().getId());
        stmt.setString(2, ps.getNome());
        stmt.setString(3, ps.getDescricao());
        stmt.setBoolean(4, ps.getAtivo());
        stmt.setInt(5, ps.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        stmt.close();
        con.close();
    }
    
    public static List<Pessoa> listar() throws SQLException {
        List<Pessoa> listaPessoa = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from PESSOA order by nome";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Pessoa cont = new Pessoa();
            cont.setId(rs.getInt("id"));
            stmt.setInt(1, cont.getControle().getId());
            stmt.setString(2, cont.getNome());
            stmt.setString(3, cont.getDescricao());
            stmt.setBoolean(4, cont.getAtivo());
            listaPessoa.add(cont);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaPessoa;
     }
    
    public static List<Pessoa> pesquisar(Pessoa pes) throws FrameGrabber.Exception, InterruptedException, SQLException {
        //List é uma interface e ArrayList é uma classe que implementa List
        List<Pessoa> listaPessoa = new ArrayList<>();
        Connection con = Conexao.getConexao();
        //posso usar a pesquisa por "like" que serve para pesquisa por aproximidade ou "=" que serve para igualdade
        //String sql = "select * from tb_paciente where nome like'"+pacientePesquisa.getNome()+"%' limit 0,20 order by nome";
        String sql = "select P.id, P.nome, P.descricao, CT.descricao, CT.id, P.ativo from PESSOA as P inner join CONTROLE as CT on P.id_controle=CT.id where P.nome like'"+pes.getNome()+"%' order by P.nome";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            
            Controle c = new Controle();
            c.setId(rs.getInt("CT.id"));
            c.setDescricao(rs.getString("CT.descricao"));
            
            Pessoa p = new Pessoa();
            p.setId(rs.getInt("P.id"));
            p.setNome(rs.getString("P.nome"));
            p.setDescricao(rs.getString("P.descricao"));
            p.setAtivo(rs.getBoolean("P.ativo"));
            p.setControle(c);
            listaPessoa.add(p);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaPessoa;
     }
    
    public static int IdProxPessoa() throws SQLException{
        Connection con = Conexao.getConexao();
        //String sql = "select max(id) as max_id from PESSOA";
        String sql = "select count(id) as max_id from PESSOA";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        int id=0;
        if (rs.next()) {
            id =rs.getInt("max_id");
        }
        stmt.close();
        rs.close();
        con.close();
            return id +1;
    }
  
    
    
    public static void CapturaImagens(int cam, String classificador, int numAmostra, int idPessoa, String camAmostra) throws FrameGrabber.Exception, InterruptedException{
        KeyEvent tecla=null;
      
        OpenCVFrameConverter.ToMat convertemat = new OpenCVFrameConverter.ToMat();
        //o "0" no final do parametro indica o primeiro dispositivo; comeca em zero, se tivesse mais uma entao a segunda seria a 1.
        OpenCVFrameGrabber camera = new OpenCVFrameGrabber(cam);
        camera.start();
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
        JOptionPane.showMessageDialog(null, "Pressione Tecla 'Q' Para Capturar as Imagens.");
//System.out.println("digite seu id: ");remover
//Scanner cadastro= new Scanner(System.in);remover
//int idPessoa = cadastro.nextInt();remover
        
       
        while (cFrame.isVisible() && (frameCapturado = camera.grab()) != null){
            
            
            imagemColorido = convertemat.convert(frameCapturado);
            Mat imagemCinza = new Mat();
            cvtColor(imagemColorido, imagemCinza, CV_BGR2GRAY);
            //na linha abaixo, ficarão armazenadas todas as faces detectadas
            RectVector facesDetectadas = new RectVector();
            //(imagem, facesDetectadas, ScaleFactory-EscalaDaImagem, numeroDeVisinhos, Flag-usadoEmVersoesAntigas, tamanhoMinimoImagem, TamanhoMaximoImagem)
            detectorFace.detectMultiScale(imagemCinza, facesDetectadas, 1.1, 1, 0, new Size(150, 150), new Size(500, 500));
            //linha abaixo evita erros com o javaCv ao pressionar a tecla para capturar as 25 fotos    aula 008 12:00
            if(tecla == null){
                //faz a tecla receber um delay de 50 milisegundos esperando
                tecla = cFrame.waitKey(50);
                ////////////////////////////System.out.println("bostica");
            }
            for(long i=0; i<facesDetectadas.size(); i++){
                //Rect significa o retangulo ao redor da face 
                Rect dadosFace = facesDetectadas.get(0);
                //vamos desenhar um retangulo na face colorida quando reconhecer uma face;   padrao R,G,B, naoUsado
                rectangle(imagemColorido, dadosFace, new Scalar(0, 0, 255, 0)); 
                
                Mat faceCapturada = new Mat(imagemCinza, dadosFace);
                //linha abaixo padroniza o tamanho das imagens para 160 x 160    aula 008
         //       resize(faceCapturada, faceCapturada, new Size(160, 160));
                //linha abaixo evita erros com o javaCv ao pressionar a tecla para capturar as 25 fotos    aula 008 12:00
                
                if(tecla == null){
                  //faz a tecla receber um delay de 5 milisegundos esperando
                    tecla = cFrame.waitKey(5);
                    System.out.println("eitaaaaaaaaaaaaa");
                }
                
                if(tecla != null){
                    JOptionPane.showMessageDialog(null, "merda1");
                    if(tecla.getKeyChar() == 'q'){
                        if(amostra <= numeroAmostras){
                            imwrite(camAmostra+"pessoa."+idPessoa+"."+amostra+".jpg", faceCapturada);
                            JOptionPane.showMessageDialog(null, "Imagem "+amostra+"/"+numAmostra+" capturada!");
                            System.out.println("foto"+amostra+" capturada\n");
                            amostra++;
                            tecla = null;
                        }
                    }else if(tecla.getKeyChar() == 'a'){
                        JOptionPane.showMessageDialog(null, "merda2");
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
                    tecla = cFrame.waitKey(500);
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
    
    
    public static List<Pessoa> listarReconhecimento() throws SQLException {
        List<Pessoa> listaPessoa = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select P.id, P.nome, P.ativo, CT.id, CT.dia_inicio, CT.dia_fim, CT.hora_inicio, CT.hora_fim from PESSOA as P inner join CONTROLE as CT on P.id_controle=CT.id order by P.id";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        listaPessoa.add(null);//teste para ficar com a primeira posicao vazia
        while (rs.next()) {
            Controle c = new Controle();
            Pessoa p = new Pessoa();
            
            c.setId(rs.getInt("CT.id"));
            c.setDia_inicio(rs.getString("CT.dia_inicio"));
            c.setDia_fim(rs.getString("CT.dia_fim"));
            c.setHora_inicio(rs.getTime("CT.hora_inicio"));
            c.setHora_fim(rs.getTime("CT.hora_fim"));
            
            p.setId(rs.getInt("P.id"));
            p.setNome(rs.getString("P.nome"));
            p.setAtivo(rs.getBoolean("P.ativo"));
            p.setControle(c);
            listaPessoa.add(p);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaPessoa;
     }
    
    
}


/*
select P.id, P.nome, P.ativo, CT.dia_inicio, CT.dia_fim, CT.hora_inicio, CT.hora_fim from PESSOA as P inner join CONTROLE as CT on P.id_controle=CT.id where P.ativo=1;
para o reconhecimento e abertura
para reconhecer todos tirar o final do select
*/

