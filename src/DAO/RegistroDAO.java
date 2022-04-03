
package DAO;

import bean.Registro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import util.Conexao;

public class RegistroDAO {
    
    public static void inserir(Registro rg)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "insert into REGISTROS(id_pessoa, data_hora, descricao) values(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, rg.getId_pessoa().getId());
        stmt.setTimestamp(2, rg.getDatahora());
        stmt.setString(3, rg.getDescricao());
        //System.out.println("DAO: "+rg.getId_pessoa().getId());
        stmt.execute();
        //JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        stmt.close();
        con.close();
    }
    
    public static void excluir(Registro rg)throws SQLException{
        Connection con = Conexao.getConexao();
        String sql = "DELETE from PESSOA where id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, rg.getId());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        stmt.close();
        con.close();
    }
    
    
}
