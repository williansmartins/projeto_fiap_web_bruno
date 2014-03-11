package br.com.fiap.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fiap.web.dao.ReservaDaoImpl;
import br.com.fiap.web.model.Cliente;
import br.com.fiap.web.model.Reserva;
import br.com.fiap.web.utils.Redirecionador;

@ManagedBean(name = "reserva_controller")
@RequestScoped
public class ReservaController
{

//    private Reserva entity;
    private List<Reserva> lista;
    private ReservaDaoImpl dao = new ReservaDaoImpl();
    Integer id;

    public ReservaController()
    {
		lista = new ArrayList<Reserva>();
		Integer id = ((Cliente) getSession().getAttribute("cliente")).getId();
		lista = dao.findReservaByClienteId(id);
    }
    
    private HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
    
    public void confirmar(  )
    {
    	Reserva reserva =  dao.findById(id);
    	reserva.setConfirmada(Boolean.TRUE);
    	dao.update(reserva);
    	System.out.println("confirmando: " + id);
    }
    
    public void alterarAssento(  )
    {
    	Reserva reserva =  dao.findById(id);
    	getSession().setAttribute("idDoVoo", reserva.getAssento().getVoo().getId());
    	getSession().setAttribute("idDoAssento", reserva.getAssento().getId());
    	getSession().setAttribute("reserva", reserva);
    	
    	new Redirecionador().redirecionar("trocar_assento.xhtml");
    }

    //GETTERS AND SETTERS
//    public Reserva getEntity( )
//    {
//    	return entity;
//    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public void setEntity( Reserva entity )
//    {
//    	this.entity = entity;
//    }

    public List<Reserva> getLista( )
    {
    	return lista;
    }

    public void setLista( List<Reserva> lista )
    {
    	this.lista = lista;
    }
}
