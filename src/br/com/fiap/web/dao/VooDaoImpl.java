package br.com.fiap.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.web.model.Trecho;
import br.com.fiap.web.model.Voo;

public class VooDaoImpl extends JpaGenericDao<Voo> implements IVooDao {
	private EntityManager entityManager;

	public List<Voo> findVoo(Trecho trecho, Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		entityManager = getEntityManager();
		TypedQuery<Voo> query = entityManager
				.createQuery(
						"select v from voo v where v.trecho.origem = :origem and v.trecho.destino = :destino and v.data = :data",
						Voo.class);
		query.setParameter("origem", trecho.getOrigem());
		query.setParameter("destino", trecho.getDestino());
		
		Date dataAux = null;
		try {
			dataAux = sdf.parse(sdf.format(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.setParameter("data", dataAux);
		
		List<Voo> lista = query.getResultList();
		entityManager.close();
		return lista;
	}
}
