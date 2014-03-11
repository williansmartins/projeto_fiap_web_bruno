package br.com.fiap.web.dao;

import java.util.List;

import br.com.fiap.web.model.Cliente;

public interface IClienteDao extends Dao<Cliente>{
	public List<Cliente> findEspecific(Cliente c);
}