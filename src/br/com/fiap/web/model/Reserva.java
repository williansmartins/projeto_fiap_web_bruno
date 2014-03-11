package br.com.fiap.web.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "reserva")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String loc;
	@OneToOne(fetch=FetchType.EAGER, mappedBy="reserva", optional = true)
	private Assento assento;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="trecho", referencedColumnName="id")
	private Trecho trecho;
	private Boolean confirmada;
    @OneToOne(targetEntity=Cliente.class)  
	private Cliente cliente;

    private float valor;
    
	public Reserva() {
		super();
	}

	public Reserva(String loc, Cliente cliente,
			Trecho trecho, Assento assento) {
		super();
		this.loc = loc;
		this.cliente = cliente;
		this.trecho = trecho;
		this.assento = assento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Trecho getTrecho() {
		return trecho;
	}

	public void setTrecho(Trecho trecho) {
		this.trecho = trecho;
	}

	public Assento getAssento() {
		return assento;
	}

	public void setAssento(Assento assento) {
		this.assento = assento;
	}

	public Boolean getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(Boolean confirmada) {
		this.confirmada = confirmada;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
