
package DAO;

import bean.Controle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import util.Conexao;

public class ControleDAO {
    
    public static void inserir(Controle ct)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into CONTROLE(dia_inicio, dia_fim, hora_inicio, hora_fim, descricao) VALUES(?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, ct.getDia_inicio());
        stmt.setString(2, ct.getDia_fim());
        stmt.setTime(3, ct.getHora_inicio());
        stmt.setTime(4, ct.getHora_fim());
        stmt.setString(5, ct.getDescricao());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void excluir(Controle ct)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM CONTROLE where id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, ct.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void alterar(Controle ct)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE CONTROLE SET dia_inicio=?, dia_fim=?, hora_inicio=?, hora_fim=?, descricao=? WHERE id= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, ct.getDia_inicio());
        stmt.setString(2, ct.getDia_fim());
        stmt.setTime(3, ct.getHora_inicio());
        stmt.setTime(4, ct.getHora_fim());
        stmt.setString(5, ct.getDescricao());
        stmt.setInt(6, ct.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        stmt.close();
        con.close();
    }
    
    public static List<Controle> listar() throws SQLException {
        List<Controle> listaControle = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from CONTROLE order by descricao";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Controle cont = new Controle();
            cont.setId(rs.getInt("id"));
            stmt.setString(1, cont.getDia_inicio());
            stmt.setString(2, cont.getDia_fim());
            stmt.setTime(3, cont.getHora_fim());
            stmt.setTime(4, cont.getHora_fim());
            stmt.setString(5, cont.getDescricao());
            stmt.setInt(6, cont.getId());
            listaControle.add(cont);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaControle;
     }
     
     public static List<Controle> pesquisar(Controle contPesquisa) throws SQLException {
        //List é uma interface e ArrayList é uma classe que implementa List
        List<Controle> listaControle = new ArrayList<>();
        Connection con = Conexao.getConexao();
        //posso usar a pesquisa por "like" que serve para pesquisa por aproximidade ou "=" que serve para igualdade
        //String sql = "select * from tb_paciente where nome like'"+pacientePesquisa.getNome()+"%' limit 0,20 order by nome";
        String sql = "select * from CONTROLE where descricao like'"+contPesquisa.getDescricao()+"%' order by descricao";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Controle c = new Controle();
            c.setId(rs.getInt("id"));
            c.setDia_inicio(rs.getString("dia_inicio"));
            c.setDia_fim(rs.getString("dia_fim"));
            c.setHora_inicio(rs.getTime("hora_inicio"));
            c.setHora_fim(rs.getTime("hora_fim"));
            c.setDescricao(rs.getString("descricao"));
            listaControle.add(c);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaControle;
     }
        
    
}
