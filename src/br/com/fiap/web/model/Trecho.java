package br.com.fiap.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="trecho")
public class Trecho
    implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String origem;
    private String destino;
    
    public Trecho() {
    	super();
    }
    
	public Trecho(int id) {
		super();
		this.id = id;
	}

	public Trecho(String origem, String destino) {
		super();
		this.origem = origem;
		this.destino = destino;
	}

	public String getOrigem() {
    	return origem;
    }
    
    public void setOrigem(String origem) {
    	this.origem = origem;
    }
    
    public String getDestino() {
    	return destino;
    }
    
    public void setDestino(String destino) {
    	this.destino = destino;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
	public static long getSerialversionuid() {
        return serialVersionUID;
    }
	
	@Override
    public String toString( )
    {
	return "Trecho [id=" + id + ", origem=" + origem + ", destino=" + destino + "]";
    }
    
}
