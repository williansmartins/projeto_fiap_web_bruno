package br.com.fiap.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.web.dao.JpaGenericDao;
import br.com.fiap.web.dao.ReservaDaoImpl;
import br.com.fiap.web.model.Reserva;
import br.com.fiap.web.model.Trecho;
import br.com.fiap.web.utils.Redirecionador;

@ManagedBean(name = "reserva_controller")
@SessionScoped
public class ReservaController
{

    private Reserva entity;
    List<Reserva> lista;
    private JpaGenericDao<Reserva> dao = new ReservaDaoImpl();
    Integer id;

    public ReservaController()
    {
		lista = new ArrayList<Reserva>();
		//LISTA FAKE
		lista = dao.findAll();
		lista.addAll( lista );
		lista.get(0).setTrecho(new Trecho("São Paulo", "Rio de Janeiro"));
		entity = new Reserva();
		id = new Integer(0);
    }
    
    public void confirmar(  )
    {
    	System.out.println("confirmando: " + id);
    }
    
    public void alterarAssento(  )
    {
    	System.out.println("alterando o assento: " + id);
    	new Redirecionador().redirecionar("trocar_assento.xhtml");
    }

    //GETTERS AND SETTERS
    public Reserva getEntity( )
    {
    	return entity;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEntity( Reserva entity )
    {
    	this.entity = entity;
    }

    public List<Reserva> getLista( )
    {
    	return lista;
    }

    public void setLista( List<Reserva> lista )
    {
    	this.lista = lista;
    }
}
