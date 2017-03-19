package br.edu.ifpb.pweb.turmas.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ActionEvent;

import br.pweb.turmas.dao.AlunoDAO;
import br.pweb.turmas.dao.PersistenceUtil;
import br.pweb.turmas.dao.TurmaDAO;
import br.pweb.turmas.model.Aluno;
import br.pweb.turmas.model.Turma;

@ManagedBean
@ViewScoped
public class TurmasBean {
	private List<Turma> turmas;
	private Turma turma;
	private Aluno aluno;
	private Flash flash;
	private long turmaId;

	@PostConstruct
	public void init() {
		this.flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		this.turma = new Turma();
		this.aluno = new Aluno();

		if (this.flash.containsKey("turma")) {
			// this.turma = (Turma) flash.get("turma");
		}
	}

	public String cadastrar() {
		TurmaDAO dao = new TurmaDAO();
		dao.beginTransaction();
		dao.insert(this.turma);
		dao.commit();
		return "turmas?faces-redirect=true";
	}

	public void selecionar() {
		TurmaDAO dao = new TurmaDAO(PersistenceUtil.getCurrentEntityManager());
		this.turma = dao.find(this.turmaId);
	}

	public String matricular() {
		AlunoDAO adao = new AlunoDAO();
		TurmaDAO tdao = new TurmaDAO();
		this.aluno.setTurma(this.turma);
		this.turma.addAluno(this.aluno);

		adao.beginTransaction();
		adao.insert(this.aluno);
		adao.commit();

		tdao.beginTransaction();
		tdao.update(this.turma);
		tdao.commit();

		return String.format("turma?id=%d&faces-redirect=true", this.turma.getId());

	}

	public String deletar(Turma turma) {
		TurmaDAO tdao = new TurmaDAO(PersistenceUtil.getCurrentEntityManager());
		tdao.beginTransaction();
		tdao.delete(turma);
		tdao.commit();

		this.turmas.remove(turma);
		System.out.println("DELETANDO");
		return "turmas?faces-redirect=true";
	}

	/*
	 * getters and setters
	 */
	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public void listarTurmas() {
		TurmaDAO tDao = new TurmaDAO(PersistenceUtil.getCurrentEntityManager());
		this.turmas = tDao.findAll();
	}

	public void atualizarTurmas(ActionEvent e) {
		this.listarTurmas();
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public long getTurmaId() {
		return turmaId;
	}

	public void setTurmaId(long turmaId) {
		this.turmaId = turmaId;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
