package br.edu.ifpb.memoriam.facade;

import java.util.List;

public class Resultado {
	private Object entitade;
	private boolean erro;
	private List<String> mensagensErro;
	private List<String> mensagensSucesso;
	
	public Resultado() {
		
	}
	
	public List<String> getMensagensSucesso() {
		return this.mensagensSucesso;
	}

	public void setMensagensSucesso(List<String> mensagensSucesso) {
		this.mensagensSucesso = mensagensSucesso;
	}
	
	public List<String> getMensagensErro() {
		return this.mensagensErro;
	}
	
	public void setMensagensErro(List<String> msgs) {
		this.mensagensErro = msgs;
	}

	public boolean isErro() {
		return this.erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}
	
	public Object getEntidade() {
		return this.entitade;
	}
	
	public void setEntidade(Object entidade) {
		this.entitade = entidade;
	}
}
