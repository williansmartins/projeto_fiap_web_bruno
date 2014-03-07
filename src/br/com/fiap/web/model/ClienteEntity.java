package br.com.fiap.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "fiap_web_cliente")
public class ClienteEntity implements Serializable
{

    private static final long serialVersionUID = 83908783713350043L;

    @Id
    @GeneratedValue
    Integer id;
    String nome;
    String login;
    String senha;

    public ClienteEntity()
    {

    }

    public ClienteEntity( Integer id, String nome, String login, String senha )
    {
	super();
	this.id = id;
	this.nome = nome;
	this.login = login;
	this.senha = senha;
    }

    public ClienteEntity( String nome, String login, String senha )
    {
	this.nome = nome;
	this.login = login;
	this.senha = senha;
    }

    public Integer getId( )
    {
	return id;
    }

    public void setId( Integer id )
    {
	this.id = id;
    }

    public String getNome( )
    {
	return nome;
    }

    public void setNome( String nome )
    {
	this.nome = nome;
    }

    public String getLogin( )
    {
	return login;
    }

    public void setLogin( String login )
    {
	this.login = login;
    }

    public String getSenha( )
    {
	return senha;
    }

    public void setSenha( String senha )
    {
	this.senha = senha;
    }

    @Override
    public String toString( )
    {
	return "ClienteEntity [id=" + id + ", nome=" + nome + ", login="
		+ login + ", senha=" + senha + "]";
    }

}
