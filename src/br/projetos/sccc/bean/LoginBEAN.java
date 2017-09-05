package br.projetos.sccc.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.projetos.sccc.dao.FuncionarioDAO;
import br.projetos.sccc.pojo.FuncionarioPOJO;

@SessionScoped
@ManagedBean(name = "login")
public class LoginBEAN {

	private FuncionarioPOJO funcionario;

	public LoginBEAN() {

		setFuncionario(new FuncionarioPOJO());

	}

	public FuncionarioPOJO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioPOJO funcionario) {
		this.funcionario = funcionario;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String validarLogin() {

		String senha = null;

		System.out.println(funcionario.getMatricula());
		FuncionarioDAO dao = new FuncionarioDAO();

		try {
			
			senha = dao.validarLogin(funcionario.getMatricula());

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			addMessage("Matrícula ou senha inválidos");
			
		}
		
		System.out.println("retornou");
		
		
		
		 if (senha.equals(funcionario.getSenha())) {

			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext()
					.getSession(false);
			session.setAttribute("FUNCIONARIO", this.funcionario);
				
			System.out.println("senha correta");
			
			return "/Manter/ManterProjetos";
				
		} else {

			
			
			addMessage("Matrícula ou senha inválidos");
			
			return "/Login/Login";
			
			
		}
		
		
	}
	
	
	
	public String realizarLogout() {            
	    FacesContext fc = FacesContext.getCurrentInstance();    
	    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);    
	    session.invalidate();     
	    return "/Login/Login";     
	}  
	
	
	

}
