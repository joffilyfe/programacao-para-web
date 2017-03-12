package br.edu.ifpb.pweb.model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
	List<Contato> contatos;
	public static Agenda instancia;

	private Agenda() {
		this.contatos = new ArrayList<Contato>();
	}

	public static Agenda getInstancia() {
		if (instancia == null) {
			instancia = new Agenda();
		}

		return instancia;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void addContato(Contato c) {
		this.contatos.add(c);
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public void removerContato(Contato contato) {
		if (!this.contatos.isEmpty() && this.contatos.contains(contato)) {
			this.contatos.remove(contato);
		}
	}

}
