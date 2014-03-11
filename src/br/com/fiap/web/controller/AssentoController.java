package br.com.fiap.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import br.com.fiap.web.dao.AssentoDaoImpl;
import br.com.fiap.web.dao.IAssentoDao;
import br.com.fiap.web.dao.IReservaDao;
import br.com.fiap.web.dao.IVooDao;
import br.com.fiap.web.dao.ReservaDaoImpl;
import br.com.fiap.web.dao.VooDaoImpl;
import br.com.fiap.web.model.Assento;
import br.com.fiap.web.model.Reserva;
import br.com.fiap.web.model.Voo;
import br.com.fiap.web.utils.Redirecionador;

@ManagedBean(name = "assento_controller")
@ViewScoped
public class AssentoController {
	private IVooDao daoDeVoos = new VooDaoImpl();
	private IAssentoDao daoDeAssentos = new AssentoDaoImpl();
	private IReservaDao daoReserva = new ReservaDaoImpl();

	private List<Voo> listaDeVoos;
	private List<Assento> listaDeAssentos = new ArrayList<Assento>();
	private Integer idDoVoo = new Integer(0);
	private List<SelectItem> listaDeAssentosFake;
	private Assento entity = new Assento();
	private Integer id = new Integer(0);

	
	
	public AssentoController() {
		super();
		listaDeAssentosFake = new ArrayList<SelectItem>();
		
		if (listaDeAssentosFake == null) {
			listaDeAssentosFake = new ArrayList<SelectItem>();
		}
		
		Integer idDoVoo = ((Integer) getSession().getAttribute("idDoVoo"));
		Integer idDoAssento = ((Integer) getSession().getAttribute("idDoAssento"));
		List<Assento> lista = daoDeAssentos.findByIdVoo(idDoVoo);
		List<Assento> listaAssentosInvalidos = new ArrayList<Assento>();
		for (Assento a : lista) {
			if (a.isReservado() || a.getId() == idDoAssento) {
				listaAssentosInvalidos.add(a);
			}
		}
		lista.removeAll(listaAssentosInvalidos);
		
		for (Assento item : lista) {
			listaDeAssentosFake.add(new SelectItem(item.getId(), item.toString()) );
		}
	}

	@PostConstruct
	public void init() {
		listagem();
		entity = new Assento();
	}

	public Integer getIdDoVoo() {
		return idDoVoo;
	}

	public void setIdDoVoo(Integer idDoVoo) {
		this.idDoVoo = idDoVoo;
	}

	public String listagem() {
		listaDeVoos = daoDeVoos.findAll();
		return "lista_voos.xhtml";
	}

	private void liberarAssento() {
		Integer id = (Integer) getSession().getAttribute("idDoAssento");
		Assento assento = daoDeAssentos.findById(id);
		assento.setReservado(Boolean.FALSE);
		assento.setReserva(null);
		daoDeAssentos.update(assento);
		getSession().removeAttribute("idDoAssento");
	}
			
	
	public void salvarAssento(){
		//FIXME:WILLIANS, ANTES DE TROCAR DE ASSENTOS TEMOS QUE COLOCAR UMA MENSAGEM INFORMANDO QUE PODERÁ OCORRER A MULTA, ISSO EU NÃO CONSEGUI
		//FIXME:TBM NÃO PODEMOS DEIXAR O CARA CONFIRMA A RESERVA E DEPOIS TROCAR, ENTAO TEMOS QUE DESABILITAR OS BOTÕES APÓS O USUÁRIO CONFIRMAR 
		liberarAssento();
		Assento assento = daoDeAssentos.findById(this.id);
		assento.setReservado(Boolean.TRUE);
		Reserva reserva = (Reserva) getSession().getAttribute("reserva");
		Float preco = assento.getVoo().getPreco();
		//MUlTA DE 40% POR TROCAR DE ASSENTO
		reserva.setValor(new Float((reserva.getValor()*0.4) + preco));
		reserva.setAssento(assento);
		daoReserva.update(reserva);
		assento.setReserva(reserva);
		daoDeAssentos.update(assento);
		new Redirecionador().redirecionar("lista_reservas.xhtml");
	}
	
	public String gerenciar() {
		if (idDoVoo > 0) {
			listaDeAssentos = daoDeAssentos.findByIdVoo(idDoVoo);
			if (listaDeAssentos == null || listaDeAssentos.size() == 0) {
				for (int cont = 1; cont <= 5; cont++) {
					listaDeAssentos.add(new Assento(String.valueOf(cont)));
				}
			}
		}
		HttpSession session = getSession();
		session.setAttribute("listaDeAssentos", listaDeAssentos);
		session.setAttribute("idDoVoo", idDoVoo);
		return "gerenciar_assentos.xhtml?faces-redirect=true";
	}
	
	 private HttpSession getSession() {
			return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	 }

	public String save() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		this.idDoVoo = (Integer) session.getAttribute("idDoVoo");
		this.listaDeAssentos = (List<Assento>) session.getAttribute("listaDeAssentos");
		Voo voo = daoDeVoos.findById(this.idDoVoo);
		for (Assento entity : listaDeAssentos) {
			entity.setVoo(voo);
			if (entity.getId() == 0) {
				daoDeAssentos.insert(entity);
			} else {
				daoDeAssentos.update(entity);
			}
		}
		new Redirecionador().redirecionar("lista_voos.xhtml");
		return "";
	}

	public List<Assento> getListaDeAssentos() {
		return listaDeAssentos;
	}

	public void setListaDeAssentos(List<Assento> listaDeAssentos) {
		this.listaDeAssentos = listaDeAssentos;
	}

	public List<Voo> getListaDeVoos() {
		return listaDeVoos;
	}

	public void setListaDeVoos(List<Voo> lista) {
		this.listaDeVoos = lista;
	}

	public List<SelectItem> getListaDeAssentosFake() {
		return listaDeAssentosFake;
	}

	public void setListaDeAssentosFake(List<SelectItem> listaDeAssentosFake) {
		this.listaDeAssentosFake = listaDeAssentosFake;
	}

	public Assento getEntity() {
		return entity;
	}

	public void setEntity(Assento entity) {
		this.entity = entity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
