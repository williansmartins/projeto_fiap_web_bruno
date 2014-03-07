package br.com.fiap.web.dao;

import java.util.List;

import br.com.fiap.web.model.Assento;

public interface IAssentoDao extends Dao<Assento>{
	public List<Assento> findByIdVoo(Integer idDoVoo);
}