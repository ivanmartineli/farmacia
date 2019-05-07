package br.com.farmacia.DAO;

import br.com.farmacia.entity.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedoresDAO {

    public void salvar(Fornecedores f) throws SQLException, ClassNotFoundException {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO fornecedores ");
        sql.append("(descricao) ");
        sql.append("VALUES(?)");

        Connection conexao = ConexaoFactory.conectar();
        PreparedStatement ps = conexao.prepareStatement(sql.toString());
        ps.setString(1, f.getDescricao());
        ps.executeUpdate();

    }

    public void excluir(Fornecedores f) {

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM fornecedores ");
        sql.append("WHERE codigo = ? ");

        try {
            Connection conexao = ConexaoFactory.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ps.setLong(1, f.getCodigo());
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println("Erro ao excluir: " + e);
        }

    }

    public void editar(Fornecedores f) {

        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE fornecedores ");
        sql.append("SET descricao = ? ");
        sql.append("WHERE codigo = ? ");

        System.out.println(sql);

        try {
            Connection conexao = ConexaoFactory.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setString(1, f.getDescricao());
            ps.setLong(2, f.getCodigo());
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println("Erro ao atualizar: " + e);
        }

    }

    public Fornecedores buscaPorCodigo(Fornecedores f) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM fornecedores ");
        sql.append("WHERE codigo = ? ");

        Fornecedores fornecedores = null;

        try {
            Connection conexao = ConexaoFactory.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setLong(1, f.getCodigo());

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                fornecedores = new Fornecedores();
                fornecedores.setCodigo(resultSet.getLong("codigo"));
                fornecedores.setDescricao(resultSet.getString("descricao"));

            }

        } catch (Exception e) {

            System.out.println("Erro ao atualizar: " + e);
        }
        return fornecedores;
    }

    public List<Fornecedores> listar() {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM fornecedores ");
        sql.append("ORDER BY  descricao ASC ");

        List<Fornecedores> listaFornecedores = new ArrayList<>();

        try {
            Connection conexao = ConexaoFactory.conectar();
            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Fornecedores fresultado = new Fornecedores();
                fresultado.setCodigo(resultSet.getLong("codigo"));
                fresultado.setDescricao(resultSet.getString("descricao"));

                listaFornecedores.add(fresultado);
            }

        } catch (Exception e) {

            System.out.println("Erro ao atualizar: " + e);
        }
        return listaFornecedores;
    }

    public List<Fornecedores> buscaPorDescricao(Fornecedores f) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT codigo, descricao ");
        sql.append("FROM fornecedores ");
        sql.append("WHERE descricao LIKE ? ");
        sql.append("ORDER BY  descricao ASC ");

        List<Fornecedores> listaFornecedores = new ArrayList<>();

        try {
            Connection conexao = ConexaoFactory.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setString(1, "%" + f.getDescricao() + "%");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Fornecedores forne = new Fornecedores();
                forne.setCodigo(resultSet.getLong("codigo"));
                forne.setDescricao(resultSet.getString("descricao"));

                listaFornecedores.add(forne);
            }

        } catch (Exception e) {

            System.out.println("Erro ao buscar por descricao: " + e);
        }
        return listaFornecedores;
    }

}
