package br.com.fiap.web.dao;

import java.util.List;

import br.com.fiap.web.model.Voo;

public interface IVooDao extends Dao<Voo>{
	public List<Voo> findEspecific(Voo c);
}