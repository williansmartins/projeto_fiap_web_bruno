package br.com.fiap.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import br.com.fiap.web.dao.AssentoDaoImpl;
import br.com.fiap.web.dao.IAssentoDao;
import br.com.fiap.web.dao.IReservaDao;
import br.com.fiap.web.dao.JpaGenericDao;
import br.com.fiap.web.dao.ReservaDaoImpl;
import br.com.fiap.web.dao.TrechoDaoImpl;
import br.com.fiap.web.dao.VooDaoImpl;
import br.com.fiap.web.model.Assento;
import br.com.fiap.web.model.Cliente;
import br.com.fiap.web.model.Reserva;
import br.com.fiap.web.model.Trecho;
import br.com.fiap.web.model.Voo;
import br.com.fiap.web.utils.Redirecionador;

@ManagedBean(name = "voo_controller")
@javax.faces.bean.RequestScoped
public class VooController {
	private VooDaoImpl dao = new VooDaoImpl();
	private JpaGenericDao<Trecho> daoTrecho = new TrechoDaoImpl();
	private IReservaDao daoReserva = new ReservaDaoImpl();
	private IAssentoDao daoAssento = new AssentoDaoImpl();
	private Voo entity;
	List<Voo> lista;
	List<Trecho> listaDeTrechos;
	public List<SelectItem> listaDeTrechos2;
	Integer selectItem = new Integer(0);
	Trecho trecho;
	Date dataIda;
	Date dataVolta;
	List<Assento> listaDeAssentosFake;

	public VooController() {
		entity = new Voo();
		trecho = new Trecho();
		listagem();
		listaDeTrechos = daoTrecho.findAll();
	}

	private HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public String buscarAssentos() {
		List<Assento> listaAssento = daoAssento.findByIdVoo(entity.getId());
		HttpSession session = getSession();
		session.setAttribute("listaAssento", listaAssento);
		return "reservar_assentos.xhtml";
	}
	
	public String save() {
		Trecho trecho = new Trecho(selectItem);
		entity.setTrecho(trecho);
		if (entity.getHora() != null) {
			entity.setHora("'" + entity.getHora() + "'");
		}
		if (entity.getId() == 0) {
			dao.insert(entity);
		} else {
			dao.update(entity);
		}
		entity = new Voo();
		lista = dao.findAll();
		new Redirecionador().redirecionar("lista_voos.xhtml");
		return "";
	}

	public String listagem() {
		entity = new Voo();
		lista = dao.findAll();
		// new Redirecionador().redirecionar( "lista_voos.xhtml" );
		return "";
	}

	public String remove() {
		dao.delete(entity.getId());
		listagem();
		return "";
	}

	public String incAlt() {
		entity = dao.findById(entity.getId());
		new Redirecionador().redirecionar("inserir_voo.xhtml");
		return "";
	}

	public String prepareInsert() {
		entity = new Voo();
		System.out.println("vamos inserir?");
		new Redirecionador().redirecionar("inserir_voo.xhtml");
		return "";
	}

	public String buscarVoos() {
		List<Voo> listaVooIda = null;
		lista = new ArrayList<Voo>();

		listaVooIda = dao.findVoo(trecho, dataIda, this.entity.getHora());
		if (listaVooIda != null) {
			lista.addAll(listaVooIda);
		}

		new Redirecionador().redirecionar("resultado_busca_voos_simples.xhtml");
		return "resultado_busca_voos_simples.xhtml";
	}

	private Assento getAssentoFromSession(int id) {
		List<Assento> assentos = (List<Assento>) getSession().getAttribute("listaAssento");
		for (Assento a : assentos) {
			if (a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	public String reservar(int id) {
	    	Assento assento = getAssentoFromSession(id);
	    	assento.setReservado(true);
	    	
		this.trecho = daoTrecho.findById(this.trecho.getId());
		Reserva reserva = new Reserva(Math.random() + "_LOC", ((Cliente)getSession().getAttribute("cliente")),
				this.trecho, assento);
		
		assento.setReserva(reserva);
		
		if (assento.getClasse().equalsIgnoreCase("executivo")) {
    		reserva.setValor(new Float(assento.getVoo().getPreco() * 1.2));
    	}	
		
		daoReserva.insert(reserva);
		daoAssento.update(assento);
		return "reserva_sucesso.xhtml";
	}

	// GETTERS AND SETTERS
	public List<SelectItem> getListaDeTrechos2() {
		if (listaDeTrechos2 == null) {
			listaDeTrechos2 = new ArrayList<SelectItem>();
		}
		List<Trecho> trechos = daoTrecho.findAll();
		listaDeTrechos2.clear();

		for (Trecho item : trechos) {
			listaDeTrechos2.add(new SelectItem(item.getId(), item.getOrigem()
					+ " --> " + item.getDestino()));
		}

		return listaDeTrechos2;
	}

	public void setListaDeTrechos2(List<SelectItem> itens) {
		this.listaDeTrechos2 = itens;
	}

	public List<Trecho> getListaDeTrechos() {
		return listaDeTrechos;
	}

	public void setListaDeTrechos(List<Trecho> listaDeTrechos) {
		this.listaDeTrechos = listaDeTrechos;
	}

	public Trecho getTrecho() {
		return trecho;
	}

	public void setTrecho(Trecho trecho) {
		this.trecho = trecho;
	}

	public Voo getEntity() {
		return entity;
	}

	public void setEntity(Voo entity) {
		this.entity = entity;
	}

	public List<Voo> getLista() {
		return lista;
	}

	public Integer getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(Integer selectItem) {
		this.selectItem = selectItem;
	}

	public void setLista(List<Voo> lista) {
		this.lista = lista;
	}

	public Date getDataIda() {
		return dataIda;
	}

	public void setDataIda(Date dataIda) {
		this.dataIda = dataIda;
	}

	public Date getDataVolta() {
		return dataVolta;
	}

	public void setDataVolta(Date dataVolta) {
		this.dataVolta = dataVolta;
	}

	public List<Assento> getListaDeAssentosFake( )
	{
	    return listaDeAssentosFake;
	}

	public void setListaDeAssentosFake( List<Assento> listaDeAssentosFake )
	{
	    this.listaDeAssentosFake = listaDeAssentosFake;
	}

}
