package br.edu.ifpb.pweb.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import br.edu.ifpb.pweb.turmas.dao.PersistenceUtil;
import br.edu.ifpb.pweb.turmas.dao.TurmaDAO;
import br.edu.ifpb.pweb.turmas.model.Turma;

@ManagedBean(name = "turmasBean")
@RequestScoped
public class TurmasBean {

	private TurmaDAO dao = new TurmaDAO(PersistenceUtil.getCurrentEntityManager());
	private List<Turma> turmas = dao.findAll();
	private Map<Long, Boolean> editavel = new HashMap<Long, Boolean>();
	private Turma turma = new Turma();

	public List<Turma> getTurmas() {
		return this.turmas;
	}

	public Map<Long, Boolean> getEditavel() {
		System.out.println(this.editavel);
		return this.editavel;
	}

	public void listar(ActionEvent e) {
		this.turmas = this.dao.findAll();

		for (Turma turma : this.turmas) {
			editavel.put(turma.getId(), false);
		}
	}

	public void salvar(Turma turma) {
		dao.beginTransaction();
		dao.update(turma);
		dao.commit();
		this.editavel.put(turma.getId(), false);
		System.out.println(turma.getNome());
	}

	public void excluir(Turma turma) {
		dao.beginTransaction();
		dao.delete(turma);
		dao.commit();
	}

	public String cadastrar() {
		TurmaDAO dao = new TurmaDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(this.turma);
		dao.commit();
		return "turmas?faces-redirect=true";
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
