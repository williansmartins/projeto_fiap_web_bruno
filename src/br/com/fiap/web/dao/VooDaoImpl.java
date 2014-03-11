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

	public List<Voo> findVoo(Trecho trecho, Date data, String hora) {
		
		Date dataAux = null;
		List<Voo> lista = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			entityManager = getEntityManager();
			TypedQuery<Voo> query = entityManager
					.createQuery(
							"select v from voo v where v.trecho.origem = :origem and v.trecho.destino = :destino and v.data = :data and v.hora = :hora",
							Voo.class);
			query.setParameter("origem", trecho.getOrigem());
			query.setParameter("destino", trecho.getDestino());
			query.setParameter("hora", "'" + hora + "'");
			
			dataAux = sdf.parse(sdf.format(data));
			query.setParameter("data", dataAux);
			lista = query.getResultList();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
		}
		
		return lista;
	}
}
