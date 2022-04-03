
package DAO;

import bean.Controle;
import bean.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import util.Conexao;

public class LoginDAO {
    
    public static void inserir(Login lg)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into LOGIN(login, senha, descricao) values(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, lg.getLogin());
        stmt.setString(2, lg.getSenha());
        stmt.setString(3, lg.getDescricao());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void excluir(Login lg)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE FROM LOGIN where id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, lg.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void alterar(Login lg)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "UPDATE LOGIN SET login=?, senha=?, descricao=? WHERE id= ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, lg.getLogin());
        stmt.setString(2, lg.getSenha());
        stmt.setString(3, lg.getDescricao());
        stmt.setInt(4, lg.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        stmt.close();
        con.close();
    }
    
    public static List<Login> listar() throws SQLException {
        List<Login> listaLogin = new ArrayList<>();
        Connection con = Conexao.getConexao();
        String sql = "select * from LOGIN order by login";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Login cont = new Login();
            cont.setId(rs.getInt("id"));
            stmt.setString(1, cont.getLogin());
            stmt.setString(2, cont.getSenha());
            stmt.setString(3, cont.getDescricao());
            stmt.setInt(4, cont.getId());
            listaLogin.add(cont);
        }
        stmt.close();
        rs.close();
        con.close();
        return listaLogin;
     }
}
