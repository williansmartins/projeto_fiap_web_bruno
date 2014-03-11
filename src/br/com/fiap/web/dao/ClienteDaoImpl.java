package br.com.fiap.web.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.fiap.web.model.Cliente;

public class ClienteDaoImpl extends JpaGenericDao<Cliente> implements IClienteDao{
	
	private EntityManager entityManager;
	List<Cliente> lista;
	
	public ClienteDaoImpl() {
		entityManager = getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findEspecific(Cliente cliente) {
		entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		
		String jpql = "SELECT c FROM cliente c WHERE c.login = :login AND c.senha = :senha";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter("login", cliente.getLogin());
		query.setParameter("senha", cliente.getSenha());
		
		lista = (List<Cliente>)query.getResultList();
		
		entityManager.flush();
		
		entityManager.close();
		if(lista.size() > 0){
			return lista;
		}else{
			return new ArrayList<Cliente>();
		}
	}
}

