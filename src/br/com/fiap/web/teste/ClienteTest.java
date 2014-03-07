package br.com.fiap.web.teste;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.fiap.web.dao.ClienteDaoImpl;
import br.com.fiap.web.dao.JpaGenericDao;
import br.com.fiap.web.model.ClienteEntity;

public class ClienteTest {
	JpaGenericDao<ClienteEntity> dao = new ClienteDaoImpl();
	
	@Test
	public void inserirObjeto() {
		ClienteEntity obj = new ClienteEntity("Willians Martins", "login1", "senha1");
		
		dao.insert(obj);
		ClienteEntity obj2 = dao.findById(obj.getId());
		
		Assert.assertEquals(obj.getId(), obj2.getId());
		dao.delete(obj2.getId());
	}
	
	@Test
	public void excluir() {
		ClienteEntity pedidoMockado = new ClienteEntity("Nayara Martins", "login2", "senha2");
		dao.insert(pedidoMockado);
		dao.delete(pedidoMockado.getId());
		ClienteEntity pedidoBanco = dao.findById(pedidoMockado.getId());
		
		Assert.assertNull(pedidoBanco);
	}
	
	@Test
	public void listar() {
		ClienteEntity pedidoMockado1 = new ClienteEntity("nome1", "login1", "senha1");
		ClienteEntity pedidoMockado2 = new ClienteEntity("nome2", "login2", "senha2");
		dao.insert(pedidoMockado1);
		dao.insert(pedidoMockado2);
		List<ClienteEntity> lista = dao.findAll();
		
		Assert.assertTrue( lista.size() >1 );
		
		dao.delete(pedidoMockado1.getId());
		dao.delete(pedidoMockado2.getId());
	}
	

}
