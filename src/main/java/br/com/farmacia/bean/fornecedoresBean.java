package br.com.farmacia.bean;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.entity.Fornecedores;
import br.com.farmacia.util.JSFUtil;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class fornecedoresBean {

    private List<Fornecedores> itens;
    private List<Fornecedores> itensFiltrados;

    private Fornecedores fornecedores;

    // Carrega os dados antes da página ser inicializada
    @PostConstruct
    public void prepararPesquisa() {
        try {
            FornecedoresDAO fdao = new FornecedoresDAO();
            itens = fdao.listar();

        } catch (Exception e) {
            JSFUtil.adicionarMensagemErro("e.getMessage()");
            e.printStackTrace();
        }

    }

    public void novo() {
        try {
            FornecedoresDAO fdao = new FornecedoresDAO();
            fdao.salvar(fornecedores);

            itens = fdao.listar();

            JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionarMensagemErro("e.getMessage()");
            e.printStackTrace();
        }

    }

    public void excluir() {

        try {
            FornecedoresDAO fdao = new FornecedoresDAO();
            fdao.excluir(fornecedores);

            itens = fdao.listar();

            JSFUtil.adicionarMensagemSucesso("Fornecedor excluido com sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionarMensagemErro("Não é possível exluir um fornecedor que tenha um produto associado");
            e.printStackTrace();
        }
    }

    public void editar() {

        try {
            FornecedoresDAO fdao = new FornecedoresDAO();
            fdao.editar(fornecedores);

            itens = fdao.listar();

            JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso.");

        } catch (Exception e) {
            JSFUtil.adicionarMensagemErro("ex.getMessage()");
            e.printStackTrace();
        }
    }

    public void prepareInstancia() {
        fornecedores = new Fornecedores();
    }

    public List<Fornecedores> getItens() {
        return itens;
    }

    public void setItens(List<Fornecedores> itens) {
        this.itens = itens;
    }

    public List<Fornecedores> getItensFiltrados() {
        return itensFiltrados;
    }

    public void setItensFiltrados(List<Fornecedores> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public Fornecedores getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(Fornecedores fornecedores) {
        this.fornecedores = fornecedores;
    }

}
