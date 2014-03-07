package br.com.fiap.web.controller.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Serializable, Filter
{

    private static final long serialVersionUID = 1L;

    @Override
    public void doFilter( ServletRequest request, ServletResponse response,
	    FilterChain chain ) throws IOException, ServletException
    {

	HttpServletRequest req = (HttpServletRequest) request;
	HttpSession session = req.getSession();
	String chave = (String) session.getAttribute( "autenticado_chave" );
	boolean isLoginPage = req.getServletPath().lastIndexOf( "login.xhtml" ) > -1;

	if ( !isLoginPage && ( chave == null || chave != "ok" ) )
	{
	    HttpServletResponse res = (HttpServletResponse) response;
	    res.sendRedirect( "../login.xhtml" );
	} else
	{
	    chain.doFilter( request, response );
	}

    }

    @Override
    public void init( FilterConfig filterConfig ) throws ServletException
    {}

    @Override
    public void destroy( )
    {}

}