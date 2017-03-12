package br.edu.ifpb.pweb.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.pweb.model.Agenda;
import br.edu.ifpb.pweb.model.Contato;

@ManagedBean(name = "contatoBean")
@SessionScoped
public class ContatoBean {
	private Contato contato = new Contato();
	private boolean editando;

	public void setNome(String nome) {
		this.contato.setNome(nome);
	}

	public void setEmail(String email) {
		this.contato.setEmail(email);
	}

	public void setTelefone(String telefone) {
		this.contato.setTelefone(telefone);
	}

	public String getNome() {
		return this.contato.getNome();
	}

	public String getEmail() {
		return this.contato.getEmail();
	}

	public String getTelefone() {
		return this.contato.getTelefone();
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public String salvar() {
		this.setEditando(false);
		this.contato = new Contato();
		return "/index?redirect-facet=true";
	}

	public String editar(Contato contato) {
		this.contato = contato;
		this.setEditando(true);
		return "/contatoFormulario?redirect-facet=true";
	}

	public String cadastrar() {
		Agenda agenda = Agenda.getInstancia();
		agenda.addContato(this.contato);
		this.contato = new Contato();

		return "/index?redirect-facet=true";
	}

}
