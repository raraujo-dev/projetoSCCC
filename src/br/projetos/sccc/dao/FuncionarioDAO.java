package br.projetos.sccc.dao;

import java.util.ArrayList;
import java.util.List;

import br.projetos.sccc.pojo.FuncionarioPOJO;

public class FuncionarioDAO extends DAO {
	
	
	
	public String inserir(FuncionarioPOJO funcionario){
		
		
		String retorno = "Colaborador "+funcionario.getMatricula()+" inserido com sucesso!";

		try {

			abreConexao();
			st = cn.prepareStatement("INSERT INTO tb_funcionario (matricula, nome, senha, equipe, celular) VALUES (?,?,?,?,?)");
			st.setString(1, funcionario.getMatricula());
			st.setString(2, funcionario.getNome());
			st.setString(3, funcionario.getSenha());
			st.setString(4, funcionario.getEquipe());
			st.setString(5, funcionario.getCelular());
			
			
			

			st.execute();

		} catch (Exception e) {

			System.out.println(e);
			retorno = e.getMessage();

		} finally {

			try {
				fecharConexao();
			} catch (Exception e) {

				retorno = e.getMessage();
			}
		}

		return retorno;

		
	}

	
	
	
	public List<FuncionarioPOJO> pesquisarFuncionario() throws Exception{
		
		
		List<FuncionarioPOJO> listaFunc = new ArrayList<FuncionarioPOJO>();
		
		
		try {
			
			
			abreConexao();
			st = cn.prepareStatement("SELECT * FROM tb_funcionario");
			rs = st.executeQuery();
			while(rs.next()){
				
				FuncionarioPOJO funcionario = new FuncionarioPOJO();
				
				funcionario.setMatricula(rs.getString("matricula"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setEquipe(rs.getString("equipe"));
				funcionario.setSenha(rs.getString("senha"));
				funcionario.setCelular(rs.getString("celular"));
				
				System.out.println(funcionario.getNome());
				
				listaFunc.add(funcionario);
				
			}
			
			
		} catch (Exception e) {
			 
		System.out.println(e.getMessage());
		}
		
		
		finally{
			
			fecharConexao();
		}
		
		return listaFunc;
	}
	
	
	
	
	
	
	
	
	
	
	
	public String deletarFuncionario(FuncionarioPOJO funcionario){
		
		String retorno = "Colaborador "+funcionario.getMatricula()+" excluído com sucesso!";

		try {

			abreConexao();
			
			
			st = cn.prepareStatement("DELETE FROM tb_funcionario WHERE MATRICULA = ?");
			st.setString(1, funcionario.getMatricula());
			st.execute();

			

		} catch (Exception e) {

			System.out.println(e);
			retorno = e.getMessage();

		} finally {

			try {
				fecharConexao();
			} catch (Exception e) {

				retorno = e.getMessage();
			}
		}

		return retorno;
		
		
		
	}

	
	
		
	
public String alterarFuncionario(FuncionarioPOJO funcionario){
		
		String retorno = "Colaborador "+funcionario.getMatricula()+" alterado com sucesso!";

		try {

			abreConexao();
			
			
			st = cn.prepareStatement("UPDATE tb_funcionario SET MATRICULA = ?, NOME = ?, EQUIPE = ?, CELULAR = ? WHERE MATRICULA = ?");

			st.setString(1, funcionario.getMatricula());
			st.setString(2, funcionario.getNome());
			st.setString(3, funcionario.getEquipe());
			st.setString(4, funcionario.getCelular());
			st.setString(5, funcionario.getMatricula());

			st.execute();
			

		} catch (Exception e) {

			System.out.println(e);
			retorno = e.getMessage();

		} finally {

			try {
				fecharConexao();
			} catch (Exception e) {

				retorno = e.getMessage();
			}
		}

		return retorno;
		
}


			public String validarLogin(String matricula){
				String senha = "1233456789";
				
				try {

					abreConexao();
					
					
					st = cn.prepareStatement("SELECT * FROM tb_funcionario WHERE MATRICULA = ?");

					st.setString(1, matricula);

					rs = st.executeQuery();
					
					
					
					while(rs.next()){
						
						FuncionarioPOJO funcionario = new FuncionarioPOJO();
						
						
						funcionario.setMatricula(rs.getString("matricula"));
						funcionario.setNome(rs.getString("nome"));
						funcionario.setEquipe(rs.getString("equipe"));
						funcionario.setSenha(rs.getString("senha"));
						funcionario.setCelular(rs.getString("celular"));
						senha = funcionario.getSenha();
				
				System.out.println("Saiu da DAO");
			}
					
				}	catch (Exception e) {
				 
				System.out.println(e.getMessage());
				}
				
				
				finally{
					
					try {
						fecharConexao();
					} catch (Exception e) {
						
						
						e.getMessage();
					}
				}
				
				return senha;

			}
}
