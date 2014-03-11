package br.com.fiap.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.web.model.Reserva;

public class ReservaDaoImpl extends JpaGenericDao<Reserva> implements IReservaDao {
	private EntityManager entityManager;
	public List<Reserva> findReservaByClienteId(Integer id) {
		try {
			entityManager = getEntityManager();
			TypedQuery<Reserva> query = entityManager.createQuery("select r from reserva r where r.cliente.id = :id", Reserva.class); 
			query.setParameter("id", id);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
}
