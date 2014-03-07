package br.com.fiap.web.teste;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.fiap.web.dao.ClienteDaoImpl;
import br.com.fiap.web.dao.JpaGenericDao;
import br.com.fiap.web.model.ClienteEntity;

public class ClienteMassa {
	JpaGenericDao<ClienteEntity> dao = new ClienteDaoImpl();
	
	@Test
	public void inserirClientes() {
		ClienteEntity obj = new ClienteEntity();
		
		for (int i = 0; i < 10 ; i ++) {
			obj = new ClienteEntity("Nome "+i, "login"+i, "senha"+i);
			dao.insert(obj);
		}
		
		List<ClienteEntity> lista = dao.findAll();
		Assert.assertTrue( lista.size() > 9 );
		
	}
	
	@Test
	public void ExcluirTodos() {

		for (ClienteEntity obj : dao.findAll()) {
			dao.delete(obj.getId());
		}
		
		List<ClienteEntity> lista = dao.findAll();
		Assert.assertTrue( lista.size() == 0 );
		
	}
	
	
}
