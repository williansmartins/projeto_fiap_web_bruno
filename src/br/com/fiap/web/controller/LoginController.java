package br.com.fiap.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fiap.web.dao.ClienteDaoImpl;
import br.com.fiap.web.dao.JpaGenericDao;
import br.com.fiap.web.model.Cliente;
import br.com.fiap.web.utils.Redirecionador;

@ManagedBean(name = "login_controller")
@SessionScoped
public class LoginController
{

    private Cliente entity;

    public LoginController()
    {
	lista = new ArrayList<Cliente>();
	entity = new Cliente();
    }

    private JpaGenericDao<Cliente> dao = new ClienteDaoImpl();
    List<Cliente> lista;

    public String login( )
    {
	List<Cliente> lista = dao.findEspecific( entity );
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession( true );

	if ( lista!=null && lista.size() > 0 )
	{
	    entity = lista.get( 0 );
	    session.setAttribute("cliente", entity);
	    session.setAttribute( "autenticado_chave", "ok" );
	    if( entity.getLogin().equalsIgnoreCase( "admin" )){
		new Redirecionador().redirecionar( "seguro/index.xhtml" );
	    }else{
		new Redirecionador().redirecionar( "seguro/index_simples.xhtml" );
	    }
	    return "";
	} else
	{
	    FacesContext.getCurrentInstance().addMessage(
		    "field_id",
		    new FacesMessage( FacesMessage.SEVERITY_ERROR,
			    "Usuário ou senha incorreta", "error message2" ) );
	}
	return "";
    }

    public Cliente getEntity( )
    {
	return entity;
    }

    public void setEntity( Cliente entity )
    {
	this.entity = entity;
    }

    public List<Cliente> getLista( )
    {
	return lista;
    }

    public void setLista( List<Cliente> lista )
    {
	this.lista = lista;
    }
}
