package br.projetos.sccc.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.projetos.sccc.dao.FuncionarioDAO;
import br.projetos.sccc.pojo.FuncionarioPOJO;




@ViewScoped
@ManagedBean(name = "funcionario")
public class FuncionarioBEAN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FuncionarioPOJO funcionarioPOJO;
	private List<FuncionarioPOJO> listaFunc;

	public FuncionarioBEAN() {
		setFuncionarioPOJO(new FuncionarioPOJO());

	}

	// Método para adicionar mensagens de validação

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// getters e setters

	public FuncionarioPOJO getFuncionarioPOJO() {
		return funcionarioPOJO;
	}

	public void setFuncionarioPOJO(FuncionarioPOJO funcionarioPOJO) {
		this.funcionarioPOJO = funcionarioPOJO;
	}

	public List<FuncionarioPOJO> getListaFunc() {

		this.listaFunc = new ArrayList<FuncionarioPOJO>();

		FuncionarioDAO dao = new FuncionarioDAO();

		try {
			setListaFunc(dao.pesquisarFuncionario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		return listaFunc;
	}

	public void setListaFunc(List<FuncionarioPOJO> listaFunc) {
		this.listaFunc = listaFunc;

	}

	public void novo() {

		funcionarioPOJO = new FuncionarioPOJO();

	}

	public void inserirFuncionario() {

		FuncionarioDAO dao = new FuncionarioDAO();
		String retorno = dao.inserir(funcionarioPOJO);

		funcionarioPOJO = new FuncionarioPOJO();

		addMessage(retorno);

	}

	public void limpar() {

		this.funcionarioPOJO.setCelular(null);
		this.funcionarioPOJO.setEquipe(null);
		this.funcionarioPOJO.setNome(null);

	}

	public void limparTudo() {

		this.funcionarioPOJO.setCelular(null);
		this.funcionarioPOJO.setEquipe(null);
		this.funcionarioPOJO.setNome(null);
		this.funcionarioPOJO.setMatricula(null);

	}

	public void deletarFuncionario() {

		FuncionarioDAO dao = new FuncionarioDAO();

		addMessage(dao.deletarFuncionario(funcionarioPOJO));

	}

	public void alterarFuncionario() {

		FuncionarioDAO dao = new FuncionarioDAO();

		addMessage(dao.alterarFuncionario(funcionarioPOJO));

		this.novo();
	}

	public void fechar() {

		funcionarioPOJO = null;

	}

}
