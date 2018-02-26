package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import entities.LoteEntity;
import entities.UbicacionEntity;
import hbt.HibernateUtil;
import negocio.Lote;
import negocio.Ubicacion;

public class UbicacionDAO {
	
	
	private static UbicacionDAO instance;
	SessionFactory sf;
	
	private UbicacionDAO(){
		sf = HibernateUtil.getSessionFactory();
	}

	public static UbicacionDAO getInstace(){
		if(instance == null)
			instance = new UbicacionDAO();
		return instance;
	}

//	public Ubicacion toNegocio(UbicacionEntity ubiE) {
//		Ubicacion ubi = new Ubicacion();
//		ubi.setNroUbicacion(ubiE.getNroUbicacion());
//		ubi.setCodigoUbicacion(ubiE.getCodigoUbicacion());
//		ubi.setCapacidadMax(ubiE.getCapacidadMax());
//		ubi.setCantLibre(ubiE.getCantLibre());
//		ubi.setLote(LoteDAO.getInstace().toNegocio(ubiE.getLote()));
//		return ubi;
//	}
	
	@SuppressWarnings("unchecked")
	public List<UbicacionEntity> getUbicacionesByCodigoLote(int codigoLote){
//		
//		
//		Set<SucursalEntity> sucursales =new HashSet<SucursalEntity>( session.createQuery("from SucursalEntity").list());
//		session.close();
//		return sucursales;  
		
		Session s = sf.openSession();
		s = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Por acà pasò");
		
		List<UbicacionEntity> auxE = new ArrayList<UbicacionEntity>();
		auxE = (List<UbicacionEntity>)s.createQuery("FROM UbicacionEntity where codLote = :codiLote")	
			.setParameter("codiLote", codigoLote).list();
		s.close();
		System.out.println("Por acà pasò");
		System.out.println(auxE.get(0).getCantOcupada());
		//To Ubicacion Negocio:
		
//		List<Ubicacion> lista = new ArrayList<Ubicacion>();
//		for(UbicacionEntity ubicacionE : auxE)
//		{
//			Ubicacion ubic = new Ubicacion();
//			ubic.setNroUbicacion(ubicacionE.getNroUbicacion());
//			ubic.setCantOcupada(ubicacionE.getCantOcupada());
//			ubic.setCapacidadMax(ubicacionE.getCapacidadMax());
//			ubic.setCodigoUbicacion(ubicacionE.getCodigoUbicacion());
//			lista.add(ubic);
//		}
//		return lista;
		return auxE;
	}

	public void update(UbicacionEntity ubicacionEntity) {
		
		Session session = null;

		session = sf.openSession();
		session.beginTransaction();
		session.update(ubicacionEntity);
		session.getTransaction().commit();
//		ubicacionEntity.getItemsPedido().size();
		if(session != null && session.isOpen()) {
            session.close();
		}
		
	}
	
}