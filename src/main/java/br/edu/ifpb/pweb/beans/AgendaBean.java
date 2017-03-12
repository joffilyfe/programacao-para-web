package br.edu.ifpb.pweb.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.pweb.model.Agenda;
import br.edu.ifpb.pweb.model.Contato;

@ManagedBean(name = "agendaBean", eager = true)
@SessionScoped
public class AgendaBean {
	private Agenda agenda;

	@PostConstruct
	private void init() {
		this.agenda = Agenda.getInstancia();
		Contato novo = new Contato();
		novo.setNome("Joffily");
		novo.setEmail("contato@joffily.me");
		novo.setTelefone("300-000");
		this.agenda.addContato(novo);
	}

	public List<Contato> getContatos() {
		return this.agenda.getContatos();
	}

	public void setContatos(List<Contato> contatos) {
		this.agenda.setContatos(contatos);
	}

	public String remover(Contato contato) {
		this.agenda.removerContato(contato);
		return "/index?redirect-facet=true";
	}
}
