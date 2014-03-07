package br.com.fiap.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.web.model.Assento;

public class AssentoDaoImpl extends JpaGenericDao<Assento> implements IAssentoDao {
	private EntityManager entityManager;
	public List<Assento> findByIdVoo(Integer idDoVoo) {
		entityManager = getEntityManager();
		TypedQuery<Assento> query = entityManager.createQuery("select a from assento a where a.voo.id = :idDoVoo", Assento.class);
		query.setParameter("idDoVoo", idDoVoo);
		List<Assento> listaDeAssentos = query.getResultList();
		entityManager.close();
		return listaDeAssentos;
	}
}
