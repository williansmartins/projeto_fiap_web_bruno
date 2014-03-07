package br.com.fiap.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fiap.web.utils.Redirecionador;

@ManagedBean(name = "geral_controller")
@SessionScoped
public class GeralController
{

    public String home( )
    {
	new Redirecionador().redirecionar( "index.xhtml" );
	return "";
    }

    public String home_simples( )
    {
	new Redirecionador().redirecionar( "index_simples.xhtml" );
	return "";
    }

}
