package br.com.farmacia.bean;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.entity.Fornecedores;
import br.com.farmacia.entity.Produto;
import br.com.farmacia.util.JSFUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutoBean {

    private List<Produto> itens;
    private List<Produto> itensFiltrados;

    private Produto produtos;

    private List<Fornecedores> listaFornecedores;

    // Carrega os dados antes da página ser inicializada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            ProdutoDAO pdao = new ProdutoDAO();
            itens = pdao.listar();

        } catch (Exception e) {
            JSFUtil.adicionarMensagemErro("e.getMessage()");
            e.printStackTrace();
        }

    }

    public void novo() {
        try {
            ProdutoDAO pdao = new ProdutoDAO();
            pdao.salvar(produtos);

            itens = pdao.listar();

            JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionarMensagemErro("e.getMessage()");
            e.printStackTrace();
        }

    }

    public void excluir() {

        try {
            ProdutoDAO pdao = new ProdutoDAO();
            pdao.excluir(produtos);

            itens = pdao.listar();

            JSFUtil.adicionarMensagemSucesso("Produto excluido com sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionarMensagemErro("Não é possível exluir um fornecedor que tenha um produto associado");
            e.printStackTrace();
        }
    }

    public void editar() {

        try {
            ProdutoDAO pdao = new ProdutoDAO();
            pdao.editar(produtos);

            itens = pdao.listar();

            JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionarMensagemErro("ex.getMessage()");
            e.printStackTrace();
        }
    }

    public void preparaInstancia() {
        produtos = new Produto();
        FornecedoresDAO fdao = new FornecedoresDAO();
        listaFornecedores = fdao.listar();
    }

    public void preparaEditar() {
        produtos = new Produto();
        FornecedoresDAO fdao = new FornecedoresDAO();
        listaFornecedores = fdao.listar();
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }

    public List<Produto> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(List<Produto> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public Produto getProdutos() {
        return produtos;
    }

    public void setProdutos(Produto produtos) {
        this.produtos = produtos;
    }

    public List<Fornecedores> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<Fornecedores> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

}
