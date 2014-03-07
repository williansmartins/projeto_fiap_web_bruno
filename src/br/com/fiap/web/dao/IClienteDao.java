package br.com.fiap.web.dao;

import java.util.List;

import br.com.fiap.web.model.ClienteEntity;

public interface IClienteDao extends Dao<ClienteEntity>{
	public List<ClienteEntity> findEspecific(ClienteEntity c);
}