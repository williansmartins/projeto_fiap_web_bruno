package br.com.fiap.web.teste;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.fiap.web.dao.ClienteDaoImpl;
import br.com.fiap.web.dao.JpaGenericDao;
import br.com.fiap.web.model.Cliente;

public class ClienteTest {
	JpaGenericDao<Cliente> dao = new ClienteDaoImpl();

	@Test
	public void inserirClientes() {
		Cliente obj = new Cliente();

		for (int i = 0; i < 10; i++) {
			obj = new Cliente("Nome " + i, "login" + i, "senha" + i);
			dao.insert(obj);
		}

		List<Cliente> lista = dao.findAll();
		Assert.assertTrue(lista.size() > 9);

	}

	@Test
	public void inserirClientesAdmin() {
		Cliente obj = new Cliente( "Senhor Administrador", "admin", "admin");
		dao.insert(obj);

		Cliente obj2 = new Cliente();
		obj2 = dao.findById(obj.getId());
		Assert.assertTrue( obj.getId() == obj2.getId() );

	}

	@Test
	public void ExcluirTodos() {

		for (Cliente obj : dao.findAll()) {
			dao.delete(obj.getId());
		}

		List<Cliente> lista = dao.findAll();
		Assert.assertTrue(lista.size() == 0);

	}
	
	@Test
	public void inserirObjeto() {
		Cliente obj = new Cliente("Willians Martins", "login1", "senha1");
		
		dao.insert(obj);
		Cliente obj2 = dao.findById(obj.getId());
		
		Assert.assertEquals(obj.getId(), obj2.getId());
		dao.delete(obj2.getId());
	}
	
	@Test
	public void excluir() {
		Cliente pedidoMockado = new Cliente("Nayara Martins", "login2", "senha2");
		dao.insert(pedidoMockado);
		dao.delete(pedidoMockado.getId());
		Cliente pedidoBanco = dao.findById(pedidoMockado.getId());
		
		Assert.assertNull(pedidoBanco);
	}
	
	@Test
	public void listar() {
		Cliente pedidoMockado1 = new Cliente("nome1", "login1", "senha1");
		Cliente pedidoMockado2 = new Cliente("nome2", "login2", "senha2");
		dao.insert(pedidoMockado1);
		dao.insert(pedidoMockado2);
		List<Cliente> lista = dao.findAll();
		
		Assert.assertTrue( lista.size() >1 );
		
		dao.delete(pedidoMockado1.getId());
		dao.delete(pedidoMockado2.getId());
	}
	

}
