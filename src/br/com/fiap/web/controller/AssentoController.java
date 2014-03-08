package br.com.fiap.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.fiap.web.dao.AssentoDaoImpl;
import br.com.fiap.web.dao.IAssentoDao;
import br.com.fiap.web.dao.IVooDao;
import br.com.fiap.web.dao.VooDaoImpl;
import br.com.fiap.web.model.Assento;
import br.com.fiap.web.model.Voo;
import br.com.fiap.web.utils.Redirecionador;

@ManagedBean(name = "assento_controller")
@ViewScoped
public class AssentoController {
	private IVooDao daoDeVoos = new VooDaoImpl();
	private IAssentoDao daoDeAssentos = new AssentoDaoImpl();

	private List<Voo> listaDeVoos;
	private List<Assento> listaDeAssentos = new ArrayList<Assento>();
	private Integer idDoVoo = new Integer(0);

	@PostConstruct
	public void init() {
		listagem();
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

	public String gerenciar() {
		if (idDoVoo > 0) {
			listaDeAssentos = daoDeAssentos.findByIdVoo(idDoVoo);
			if (listaDeAssentos == null || listaDeAssentos.size() == 0) {
				for (int cont = 0; cont <= 5; cont++) {
					listaDeAssentos.add(new Assento(String.valueOf(cont)));
				}
			}
		}
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.setAttribute("listaDeAssentos", listaDeAssentos);
		session.setAttribute("idDoVoo", idDoVoo);
		return "gerenciar_assentos.xhtml?faces-redirect=true";
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
}
