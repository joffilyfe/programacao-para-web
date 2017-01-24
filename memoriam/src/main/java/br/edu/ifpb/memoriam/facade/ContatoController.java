package br.edu.ifpb.memoriam.facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.edu.ifpb.memoriam.dao.ContatoDAO;
import br.edu.ifpb.memoriam.dao.OperadoraDAO;
import br.edu.ifpb.memoriam.dao.PersistenceUtil;
import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Operadora;
import br.edu.ifpb.memoriam.entity.Usuario;

public class ContatoController {
	private Contato contato;
	private List<String> mensagensErro;

	public ContatoController() {};

	public List<Contato> consultar() {
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		return dao.findAll();
	}

	public List<Contato> consultar(Usuario usuario) {
		ContatoDAO dao = new ContatoDAO();
		return dao.findAllFromUser(usuario);
	}

	public Resultado editar(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		this.contato = dao.find(Integer.parseInt(parametros.get("id")[0]));

		if (this.contato == null) {
			resultado.setErro(true);
			resultado.addMensagem(new Mensagem("Contato não localizado", Categoria.ERRO));
		} else {
			resultado.setErro(false);
			resultado.setEntidade(this.contato);
		}

		return resultado;
	}

	public Resultado cadastrar(Map<String, String[]> parametros) {
		Resultado resultado = new Resultado();

		if (isParametrosValidos(parametros)) {
			ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
			dao.beginTransaction();
			if (this.contato.getId() == null) {
				dao.insert(this.contato);
			} else {
				dao.update(this.contato);
			}
			dao.commit();
			resultado.setErro(false);
			resultado.addMensagem(new Mensagem("Contato criado com sucesso", Categoria.INFO));
		} else {
			resultado.setEntidade(this.contato);
			resultado.setErro(true);
			for (String msg : this.mensagensErro) {
				resultado.addMensagem(new Mensagem(msg, Categoria.ERRO));
			}

		}
		return resultado;
	}

	public Resultado deletar(String[] ids) {
		ContatoDAO dao = new ContatoDAO(PersistenceUtil.getCurrentEntityManager());
		Resultado resultado = new Resultado();

		resultado.setErro(false);
		dao.beginTransaction();

		for(String id : ids) {
			Contato contato = dao.find(Integer.parseInt(id));
			if (contato == null) {
				resultado.setErro(true);
				resultado.addMensagem(new Mensagem("Erro ao deletar contato de id: " + id, Categoria.ERRO));
			}
			dao.delete(contato);
		}

		dao.commit();

		return resultado;

	}

	private boolean isParametrosValidos(Map<String, String[]> parametros) {
		String[] id = parametros.get("id");
		String[] nome = parametros.get("nome");
		String[] fone = parametros.get("fone");
		String[] dataAniv = parametros.get("dataaniv");
		Operadora operadora = null;
		String[] idOperadora = parametros.get("operadora");

		this.mensagensErro = new ArrayList<String>();
		this.contato = new Contato();

		// Verifica ID
		if (id!= null && id.length > 0 && !id[0].isEmpty()) {
			this.contato.setId(Integer.parseInt(id[0]));
		}

		// Verifica nome
		if (nome == null || nome.length == 0 || nome[0].isEmpty()) {
			this.mensagensErro.add("Nome é campo obrigatório!");
		} else {
			contato.setNome(nome[0]);
		}

		// Verifica telefone
		if (fone == null|| fone.length == 0 || fone[0].isEmpty()) {
			this.mensagensErro.add("Fone é campo obrigatório!");
		} else {
			contato.setFone(fone[0]);
		}

		if (dataAniv== null || dataAniv.length== 0 || dataAniv[0].isEmpty()) {
			this.mensagensErro.add("Data de aniversário é campo obrigatório!");
		} else {
			if (dataAniv[0].matches("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d{2,2}")) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					sdf.setLenient(false);
					Date dataIni= sdf.parse(dataAniv[0]);
					contato.setDataAniversario(dataIni);
				} catch(ParseException e) {
					this.mensagensErro.add("Data inválida para a data de aniversário!");
				}
			} else {
				this.mensagensErro.add("Formato inválido para a data de aniversário (use dd/mm/aaaa)!");
			}
		}


		if (idOperadora != null && idOperadora.length != 0 && !idOperadora[0].isEmpty()) {
			OperadoraDAO opDao = new OperadoraDAO(PersistenceUtil.getCurrentEntityManager());
			operadora = opDao.find(Integer.parseInt(idOperadora[0]));
		}

		if (operadora != null) {
			contato.setOperadora(operadora);
		} else {
			this.mensagensErro.add("É necessário selecionar uma operadora");
		}

		return this.mensagensErro.isEmpty();
	}
}
