package br.com.fiap.web.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="aviao")
public class Aviao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String codigo;
    private String descricao;
    @OneToMany(targetEntity=Assento.class)  
    @JoinColumn(referencedColumnName="id") 
    private List<Assento> assentos;
    
    public Aviao() {
	super();
    }

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    
    public String getCodigo() {
        return codigo;
    }

    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    public String getDescricao() {
        return descricao;
    }

    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
    public List<Assento> getAssentos() {
        return assentos;
    }

    
    public void setAssentos(List<Assento> assentos) {
        this.assentos = assentos;
    }

    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
