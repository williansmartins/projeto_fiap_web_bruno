package br.com.fiap.web.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.web.model.ClienteEntity;
import br.com.fiap.web.model.Trecho;

public class TrechoDaoImpl extends JpaGenericDao<Trecho> implements ITrechoDao{
	
	private EntityManager entityManager;
	List<ClienteEntity> lista;
	
	public TrechoDaoImpl() {
		entityManager = getEntityManager();
	}
	
}

