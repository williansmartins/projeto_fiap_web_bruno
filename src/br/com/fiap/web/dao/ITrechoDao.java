package br.com.fiap.web.dao;

import java.util.List;

import br.com.fiap.web.model.Trecho;

public interface ITrechoDao extends Dao<Trecho>{
	public List<Trecho> findEspecific(Trecho c);
}