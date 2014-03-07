package br.com.fiap.web.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.web.dao.JpaGenericDao;
import br.com.fiap.web.dao.TrechoDaoImpl;
import br.com.fiap.web.model.Trecho;
import br.com.fiap.web.utils.Redirecionador;

@ManagedBean(name="trecho_controller")
@SessionScoped
public class TrechoController {
	private JpaGenericDao<Trecho> dao = new TrechoDaoImpl();
	private Trecho entity;
	List<Trecho> lista;
	
	public TrechoController(){
		entity = new Trecho();
		listagem();
	}
	
	public String save(){
		if(entity.getId() == 0) {
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
		entity = new Trecho();
		lista = dao.findAll();
		new Redirecionador().redirecionar( "lista_trechos.xhtml" );
		return "";
	}
	public String listagem(){
		entity = new Trecho();
		lista = dao.findAll();
		new Redirecionador().redirecionar( "lista_trechos.xhtml" );
		return "";
	}
	
	public String remove(){
		dao.delete(entity.getId());
		listagem();
		return "";
	}	
	
	public String incAlt(){
		entity = dao.findById(entity.getId());
		new Redirecionador().redirecionar( "inserir_trecho.xhtml" );
		return "";
	}	
	
	public String prepareInsert(){
		entity = new Trecho();
		System.out.println("vamos inserir?");
		new Redirecionador().redirecionar( "inserir_trecho.xhtml" );
		return "";
	}

	public Trecho getEntity() {
		return entity;
	}

	public void setEntity(Trecho entity) {
		this.entity = entity;
	}

	public List<Trecho> getLista() {
		return lista;
	}

	public void setLista(List<Trecho> lista) {
		this.lista = lista;
	}
}
