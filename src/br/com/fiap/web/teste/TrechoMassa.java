package br.com.fiap.web.teste;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.fiap.web.dao.JpaGenericDao;
import br.com.fiap.web.dao.TrechoDaoImpl;
import br.com.fiap.web.model.Trecho;

public class TrechoMassa {
	JpaGenericDao<Trecho> dao = new TrechoDaoImpl();
	
	@Test
	public void inserirTrechos() {
		Trecho obj = new Trecho();
		
		for (int i = 0; i < 10 ; i ++) {
			obj = new Trecho("são paulo:"+i, "bahia:"+i);
			dao.insert(obj);
		}
		
		List<Trecho> lista = dao.findAll();
		Assert.assertTrue("Adicionado 10 itens", lista.size() > 9 );
		
	}
	
	@Test
	public void ExcluirTodos() {

		for (Trecho obj : dao.findAll()) {
			dao.delete(obj.getId());
		}
		
		List<Trecho> lista = dao.findAll();
		Assert.assertTrue( lista.size() == 0 );
		
	}
	
	
}
