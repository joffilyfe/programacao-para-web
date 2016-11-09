package br.edu.ifpb.memoriam.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.memoriam.dao.ContatoDAO;
import br.edu.ifpb.memoriam.dao.OperadoraDAO;
import br.edu.ifpb.memoriam.dao.PersistenceUtil;
import br.edu.ifpb.memoriam.entity.Operadora;

public class OperadoraController {
	
	Operadora operadora;
	List<String> mensagensErro;

	public List<Operadora> consultar() {
		OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.findAll();
	}
	
	public Resultado cadastrar(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();

		if (isParametrosValidos(parametros)) {
			OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
			dao.beginTransaction();
			if (this.operadora.getId() == null) {
				dao.insert(this.operadora);
			} else {
				dao.update(this.operadora);
			}
			dao.commit();
			resultado.setErro(false);
			resultado.setMensagensErro(
				Collections.singletonList("Operadora criada com sucesso")
			);
		} else {
			resultado.setEntidade(this.operadora);
			resultado.setErro(true);
			resultado.setMensagensErro(this.mensagensErro);
		}
		return resultado;
	}

	public Resultado editar(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();
		OperadoraDAO dao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
		this.operadora = dao.find(Integer.parseInt(parametros.get("id")[0]));

		if (this.operadora == null) {
			resultado.setErro(true);
			resultado.setMensagensErro(Collections.singletonList("Operadora não localizado"));
		} else {
			resultado.setErro(false);
			resultado.setEntidade(this.operadora);
		}

		return resultado;
	}
	
	private boolean isParametrosValidos(Map<String, String[]> parametros) {
		String[] id = parametros.get("id");
		String[] nome = parametros.get("nome");
		String[] prefixo = parametros.get("prefixo");

		this.mensagensErro = new ArrayList<String>();
		this.operadora = new Operadora();
		
		// Verifica ID
		if (id!= null && id.length > 0 && !id[0].isEmpty()) {
			this.operadora.setId(Integer.parseInt(id[0]));
		}

		// Verifica nome
		if (nome == null || nome.length == 0 || nome[0].isEmpty()) {
			this.mensagensErro.add("Nome é campo obrigatório!");
		} else {
			operadora.setNome(nome[0]);
		}

		// Verifica prefixo
		if (prefixo == null || prefixo.length == 0 || prefixo[0].isEmpty()) {
			this.mensagensErro.add("Prefixo é campo obrigatório!");
		} else {
			operadora.setPrefixo(Integer.parseInt(prefixo[0]));
		}
		
		return this.mensagensErro.isEmpty();
	}
}
