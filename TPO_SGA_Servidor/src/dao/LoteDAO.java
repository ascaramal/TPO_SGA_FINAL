package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import entities.LoteEntity;
import entities.PedidoEntity;
import entities.UbicacionEntity;
import hbt.HibernateUtil;
import negocio.Lote;
import negocio.Ubicacion;

//comentarieh
public class LoteDAO {

	private static LoteDAO instance;
	SessionFactory sf;
	
	private LoteDAO(){
		sf = HibernateUtil.getSessionFactory();
	}

	public static LoteDAO getInstace(){
		if(instance == null)
			instance = new LoteDAO();
		return instance;
	}

//	public Lote toNegocio(LoteEntity lotE) {
//		Lote lot = new Lote();
//		lot.setCodLote(lotE.getCodLote());
//		lot.setFechaVtoLote(lotE.getFechaVtoLote());
//		lot.setArticulo(ArticuloDAO.getInstancia().toNegocio(lotE.getArticulo()));
//
//		//LOTE NO CONOCE MOVIMIENTOS
//		
//		if (lotE.getUbicaciones() != null) {
//			
//			for(UbicacionEntity itemAux : lotE.getUbicaciones()) {
//				Ubicacion ubic = new Ubicacion();
//				ubic.setCantOcupada(itemAux.getCantOcupada());
//				ubic.setCapacidadMax(itemAux.getCapacidadMax());
//				ubic.setCodigoUbicacion(itemAux.getCodigoUbicacion());
//				ubic.setNroUbicacion(itemAux.getNroUbicacion());
//				ubic.setLote(LoteDAO.getInstace().toNegocio(itemAux.getLote()));
//				lot.agregarUbicaciones(ubic);
//			}	
//		}
//		
//		return lot;
//	}

	@SuppressWarnings("unchecked")
	public List<LoteEntity> getLotesByCodigoArticulo(int codArt) {

		try {
			Session s = sf.openSession();
			
			List<LoteEntity> auxE = new ArrayList<LoteEntity>();
//			auxE = (List<LoteEntity>)s.createQuery("FROM LoteEntity")	.list();
			auxE = (List<LoteEntity>)s.createQuery("FROM LoteEntity where codArticulo = :codiArt")
			.setParameter("codiArt", codArt).list();
//			auxE = (List<LoteEntity>)s.createQuery("FROM LoteEntity l where l.codArticulo = :codiArt")
//			.setParameter("codiArt", codArt).list();
//			Query query = session.createQuery("from Stock where stockCode = :code ");
//			query.setParameter("code", "7277");
//			List list = query.list();

			
			s.close();
			//To Lote Negocio:
//			List<Lote> lista = new ArrayList<Lote>();
			
//			for(LoteEntity loteE : auxE)
//			{
//				Lote lote = new Lote();
//				lote.setCodLote(loteE.getCodLote());
//				lote.setFechaVtoLote(loteE.getFechaVtoLote());
//				lote.setUbicaciones(UbicacionDAO.getInstace().getUbicacionesByCodigoLote(loteE.getCodLote())); 
//				lista.add(lote);
//			}
			
//			return lista;
			return auxE;
			
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error LoteDAO.getPedidos()");
		}
		return null;

	}
	
}
