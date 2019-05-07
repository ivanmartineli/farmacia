/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.entity.Fornecedores;
import br.com.farmacia.entity.Produto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Ivan
 */
public class ProdutoDAOTeste {

    @Test
    @Ignore
    public void salvar() throws SQLException, ClassNotFoundException {
        Produto p1 = new Produto();
        Produto p2 = new Produto();
        Fornecedores f1 = new Fornecedores();
        Fornecedores f2 = new Fornecedores();

        ProdutoDAO pdao = new ProdutoDAO();

        f1.setCodigo(10L);
        f2.setCodigo(2L);

        p1.setDescricao("AMOXILINA");
        p1.setPreco(10.99);
        p1.setQuantidade(10L);
        p1.setFornecedores(f1);

        p2.setDescricao("NEOSORO");
        p2.setPreco(7.99);
        p2.setQuantidade(20L);
        p2.setFornecedores(f2);

        pdao.salvar(p2);
        pdao.salvar(p1);

    }

    @Test
    @Ignore
    public void listar() {
        ProdutoDAO pdao = new ProdutoDAO();

        List<Produto> listaProdutos = new ArrayList<>();
        listaProdutos = pdao.listar();

        for (Produto listaProduto : listaProdutos) {
            System.out.println(listaProduto.getDescricao());
            System.out.println(listaProduto.getPreco());
            System.out.println(listaProduto.getQuantidade());
            System.out.println(listaProduto.getFornecedores().getDescricao());

        }
    }

    @Test
    @Ignore
    public void excluir() {

        Produto p1 = new Produto();
        ProdutoDAO pdao = new ProdutoDAO();

        p1.setCodigo(5L);
        pdao.excluir(p1);
    }

    @Test
    public void editar() {

        Produto p1 = new Produto();
        ProdutoDAO pdao = new ProdutoDAO();
        Fornecedores f1 = new Fornecedores();
        
        f1.setCodigo(3L);

        p1.setCodigo(15L);
        p1.setDescricao("CATAFLAN");
        p1.setFornecedores(f1);
        p1.setPreco(10.00);
        p1.setQuantidade(22L);
        
        pdao.editar(p1);
    }

}
