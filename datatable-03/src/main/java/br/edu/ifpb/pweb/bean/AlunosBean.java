package br.edu.ifpb.pweb.bean;

import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.edu.ifpb.pweb.turmas.dao.AlunoDAO;
import br.edu.ifpb.pweb.turmas.dao.PersistenceUtil;
import br.edu.ifpb.pweb.turmas.dao.TurmaDAO;
import br.edu.ifpb.pweb.turmas.model.Aluno;
import br.edu.ifpb.pweb.turmas.model.Turma;

@ManagedBean(name = "alunosBean")
@SessionScoped
public class AlunosBean {
	private Aluno aluno = new Aluno();
	private Set<Aluno> alunos;
	private AlunoDAO dao = new AlunoDAO(PersistenceUtil.getCurrentEntityManager());
	private TurmaDAO turmaDAO = new TurmaDAO(PersistenceUtil.getEntityManager());
	private Turma turma;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Turma getTurma() {
		return this.turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	// Action
	public String home() {
		return "turmas?faces-redirect=true";
	}

	// Action
	public String mostrarAlunos(Turma turma) {
		this.alunos = turma.getAlunos();
		this.turma = turma;
		return "alunos?faces-redirect=true";
	}

	// Action
	public String cadastrarView(Turma turma) {
		this.aluno = new Aluno();
		System.out.println("Tela de cadastro de alunos, turma:" + this.turma);
		return "alunoCadastrar?faces-redirect=true";
	}

	// Action cadastro
	public String cadastrar() {
		this.aluno.setTurma(this.turma);
		dao.beginTransaction();
		dao.insert(this.aluno);
		dao.commit();

		turmaDAO.beginTransaction();
		this.turma.addAluno(this.aluno);
		turmaDAO.update(this.turma);
		turmaDAO.commit();

		System.out.println(this.aluno);
		System.out.println(this.aluno.getTurma());
		return "alunos?faces-redirect=true";
	}

}
