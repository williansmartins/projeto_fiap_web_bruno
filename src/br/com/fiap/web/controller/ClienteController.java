package br.com.fiap.web.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.web.dao.ClienteDaoImpl;
import br.com.fiap.web.dao.JpaGenericDao;
import br.com.fiap.web.model.ClienteEntity;
import br.com.fiap.web.utils.Redirecionador;

@ManagedBean(name = "cliente_controller")
@SessionScoped
public class ClienteController
{

    private ClienteEntity entity;
    private JpaGenericDao<ClienteEntity> dao = new ClienteDaoImpl();
    List<ClienteEntity> lista;

    public ClienteController()
    {
	entity = new ClienteEntity();
	lista = dao.findAll();
    }

    public String listagem( )
    {
	entity = new ClienteEntity();
	lista = dao.findAll();
	new Redirecionador().redirecionar( "lista_clientes.xhtml" );
	return "";
    }

    public String save( )
    {
	if ( entity.getId() == null )
	    dao.insert( entity );
	dao.update( entity );
	entity = new ClienteEntity();
	return listagem();
    }

    public String saveSimples( )
    {
	dao.insert( entity );
	entity = new ClienteEntity();
	new Redirecionador().redirecionar( "novo-usuario-sucesso.xhtml" );
	return "";
    }

    public String remove( )
    {
	dao.delete( entity.getId() ); 
	return listagem();
    }

    public String incAlt( )
    {
	entity = dao.findById( entity.getId() );
	new Redirecionador().redirecionar( "inserir_cliente.xhtml" );
	return "";
    }

    public String prepareInsert( )
    {
	entity = new ClienteEntity();
	System.out.println( "insert" );
	new Redirecionador().redirecionar( "inserir_cliente.xhtml" );
	return "";
    }

    // GETTERS AND SETTERS

    public ClienteEntity getEntity( )
    {
	return entity;
    }

    public void setEntity( ClienteEntity entity )
    {
	this.entity = entity;
    }

    public List<ClienteEntity> getLista( )
    {
	return lista;
    }

    public void setLista( List<ClienteEntity> lista )
    {
	this.lista = lista;
    }
}
