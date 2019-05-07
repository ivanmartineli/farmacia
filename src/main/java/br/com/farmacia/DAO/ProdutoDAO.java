package br.com.farmacia.DAO;

import br.com.farmacia.entity.Fornecedores;
import br.com.farmacia.entity.Produto;
import br.com.farmacia.factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto p) throws SQLException, ClassNotFoundException {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO produtos ");
        sql.append("(descricao, preco, quantidade, fornecedores_codigo) ");
        sql.append("VALUES (?,?,?,?)");

        Connection conexao = ConexaoFactory.conectar();
        PreparedStatement ps = conexao.prepareStatement(sql.toString());
        ps.setString(1, p.getDescricao());
        ps.setDouble(2, p.getPreco());
        ps.setLong(3, p.getQuantidade());
        ps.setLong(4, p.getFornecedores().getCodigo());

        ps.executeUpdate();

    }

    public List<Produto> listar() {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
        sql.append("FROM produtos p ");
        sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo");

        List<Produto> listaProdutos = new ArrayList<>();

        try {
            Connection conexao = ConexaoFactory.conectar();
            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ResultSet resultado = ps.executeQuery();

            while (resultado.next()) {
                Fornecedores fornecedores = new Fornecedores();
                fornecedores.setCodigo(resultado.getLong("f.codigo"));
                fornecedores.setDescricao(resultado.getString("f.descricao"));

                Produto produto = new Produto();
                produto.setCodigo(resultado.getLong("codigo"));
                produto.setDescricao(resultado.getString("descricao"));
                produto.setPreco(resultado.getDouble("preco"));
                produto.setQuantidade(resultado.getLong("quantidade"));

                produto.setFornecedores(fornecedores);

                listaProdutos.add(produto);
            }

        } catch (Exception e) {

            System.out.println("Erro ao buscar: " + e);
        }
        return listaProdutos;
    }

    public void excluir(Produto p) {

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM produtos ");
        sql.append("WHERE codigo = ? ");

        try {
            Connection conexao = ConexaoFactory.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ps.setLong(1, p.getCodigo());
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println("Erro ao excluir o produto: " + e);
        }

    }

    public void editar(Produto p) {

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE produtos ");
        sql.append("SET descricao = ? , preco = ?, quantidade = ?, fornecedores_codigo = ? ");
        sql.append("WHERE codigo = ?");

        System.out.println(sql);

        try {
            Connection conexao = ConexaoFactory.conectar();

            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setString(1, p.getDescricao());
            ps.setDouble(2, p.getPreco());
            ps.setLong(3, p.getQuantidade());
            ps.setLong(4, p.getFornecedores().getCodigo());
            ps.setLong(5, p.getCodigo());

            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println("Erro ao atualizar: " + e);
        }

    }
}
