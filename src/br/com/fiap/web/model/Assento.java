package br.com.fiap.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "assento")
public class Assento implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    private String numero;
    private String classe;
    private boolean reservado;
    @OneToOne(fetch = FetchType.EAGER)
    private Reserva reserva;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "voo", referencedColumnName = "id")
    private Voo voo;

    public Assento()
    {
	super();
    }
    
    public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}



	public Assento( String numero )
    {
	super();
	this.numero = numero;
    }

    public Assento( int id, String numero, String classe, boolean reservado, Voo voo )
    {
	super();
	this.id = id;
	this.numero = numero;
	this.classe = classe;
	this.reservado = reservado;
	this.voo = voo;
    }

    public int getId( )
    {
	return id;
    }

    public void setId( int id )
    {
	this.id = id;
    }

    public String getDescricao( )
    {
	return descricao;
    }

    public void setDescricao( String descricao )
    {
	this.descricao = descricao;
    }

    public static long getSerialversionuid( )
    {
	return serialVersionUID;
    }

    public String getNumero( )
    {
	return numero;
    }

    public void setNumero( String numero )
    {
	this.numero = numero;
    }

    public String getClasse( )
    {
	return classe;
    }

    public void setClasse( String classe )
    {
	this.classe = classe;
    }

    public Voo getVoo( )
    {
	return voo;
    }

    public void setVoo( Voo voo )
    {
	this.voo = voo;
    }

    @Override
    public String toString( )
    {
	return "Assento [id=" + id + ", numero=" + numero
		+ ", classe=" + classe + "]";
    }

    public boolean isReservado( )
    {
	return reservado;
    }

    public void setReservado( boolean reservado )
    {
	this.reservado = reservado;
    }
}
