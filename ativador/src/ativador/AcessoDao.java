package ativador;



import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcessoDao {

    public void adicionar(Acesso acesso) {
        Connection con = conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement("INSERT INTO credencial (usuario,login,senha) values (?,?,?)");
            pstm.setString(1, acesso.getNomeUsuario());
            pstm.setString(2, acesso.getNomeLogin());
            pstm.setString(3, acesso.getSenha());

            pstm.execute();
            JOptionPane.showMessageDialog(null, "Criado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ErroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar no banco.", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexao.closeConnection(con, pstm);
        }
    }

    public void alterar(Acesso acesso) {
        Connection con = conexao.getConnection();
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement("UPDATE  credencial SET usuario =?, login = ?, senha = ? WHERE id = ?");
            pstm.setString(1, acesso.getNomeUsuario());
            pstm.setString(2, acesso.getNomeLogin());
            pstm.setString(3, acesso.getSenha());

            pstm.execute();
            JOptionPane.showMessageDialog(null, "Adicionado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ErroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar no banco.", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexao.closeConnection(con, pstm);
        }
    }

    public void remover(Acesso acesso) {
        Connection con = conexao.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = con.prepareStatement("DELETE FROM credencial WHERE id = ?");
            pstm.setLong(1, acesso.getId());

            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Removido com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ErroSql) {

            JOptionPane.showMessageDialog(null, "Erro ao Remover:" + ErroSql, "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexao.closeConnection(con, pstm);
        }
    }

    public List<Acesso> listaAcesso() {
        List<Acesso> controleAcessos = new ArrayList<>();

        Connection con = conexao.getConnection();

        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = con.prepareStatement("SELECT id, usuario, login, senha, saida FROM credencial");
            rs = pstm.executeQuery();
            while (rs.next()) {

                Acesso controleAcesso = new Acesso();

                controleAcesso.setId(rs.getLong("id"));

                controleAcesso.setNomeLogin(rs.getString("usuario"));
                controleAcesso.setNomeLogin(rs.getString("login"));
                controleAcesso.setSenha(rs.getString("senha"));

                controleAcessos.add(controleAcesso);
            }
        } catch (SQLException ErroSql) {
            JOptionPane.showMessageDialog(null, "Erro ao listar dados: " + ErroSql, "EERRO", JOptionPane.ERROR_MESSAGE);
        } finally {
            conexao.closeConnection(con, pstm, rs);
        }
        return controleAcessos;
    }

}
