package br.edu.ifpb.pweb.turmas.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.pweb.turmas.model.Aluno;

@ManagedBean(name = "alunosBean")
@RequestScoped
public class AlunosBean {

	List<Aluno> alunos;

	public void setTurma() {

	}

	public void getAlunos() {

	}

}
