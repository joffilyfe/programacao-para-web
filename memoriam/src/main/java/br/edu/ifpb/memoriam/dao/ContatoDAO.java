package br.edu.ifpb.memoriam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.edu.ifpb.memoriam.entity.Contato;
import br.edu.ifpb.memoriam.entity.Usuario;

public class ContatoDAO extends GenericDAO<Contato, Integer> {

	public ContatoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public ContatoDAO(EntityManager em) {
		super(em);
	}

	public List<Contato> findAllFromUser(Usuario usuario) {
		Query q = this.getEntityManager().createQuery("SELECT c FROM Contato c WHERE c.usuario = :user");
		q.setParameter("user", usuario);

		if (q.getResultList().isEmpty()) {
			return null;
		}

		@SuppressWarnings("unchecked")
		List<Contato> l = q.getResultList();

		return l;
	}


}
