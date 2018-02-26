package dao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import dto.ArticuloDTO;
import dto.LoteDTO;
import dto.MovimientoDTO;
import dto.UbicacionDTO;
import entities.ArticuloEntity;
import entities.LoteEntity;
import entities.MovimientoEntity;
import entities.UbicacionEntity;
import exceptions.ArticuloException;
import exceptions.DAOException;
import hbt.HibernateUtil;
import negocio.Articulo;
import negocio.Lote;
import negocio.Movimiento;
import negocio.Ubicacion;


public class ArticuloDAO {

	private static ArticuloDAO instancia;
	private static SessionFactory sf;
	
	private ArticuloDAO() {
		sf = HibernateUtil.getSessionFactory();
	}

	public static ArticuloDAO getInstancia() {		
		if(instancia == null) {
			instancia = new ArticuloDAO();
		}
		return instancia;
	}

	public Articulo findArticulo(int nroArticulo) throws DAOException, ArticuloException {
		Articulo art = null;
		try {
			Session s = sf.openSession();
			s.beginTransaction();
			ArticuloEntity articuloE = (ArticuloEntity) s.createQuery("from ArticuloEntity where codArticulo = :nroArticulo")
					.setParameter("nroArticulo", nroArticulo).uniqueResult();
			s.getTransaction().commit();
			s.close();	
			if(articuloE != null) { 
				art = this.toNegocio(articuloE);
				return art;
			} 
		} catch (Exception e) {
			System.out.println("Error ArticuloDAO.findArticulo");
		}
		
		return null;
	}
	
	@SuppressWarnings("null")
	public Articulo findArticuloByCodigo(Integer codArticulo) throws ArticuloException {

		Articulo articulo = null;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();

			ArticuloEntity articuloEntity = (ArticuloEntity) session.load(ArticuloEntity.class, codArticulo);

			articulo.setCantAComprar(articuloEntity.getCantAComprar());
			articulo.setCantFuturoDisponible(articuloEntity.getCantFuturoDisponible());
			articulo.setCantReal(articuloEntity.getCantReal());
			articulo.setCantReservada(articuloEntity.getCantReservada());
			articulo.setCodArticulo(articuloEntity.getCodArticulo());
			articulo.setCodigoBarras(articuloEntity.getCodigoBarras());
			articulo.setDescripcion(articuloEntity.getDescripcion());
			articulo.setMarca(articuloEntity.getMarca());
			articulo.setPrecio(articuloEntity.getPrecio());
			articulo.setPresentacion(articuloEntity.getPresentacion());
			articulo.setTamano(articuloEntity.getTamano());
			articulo.setTipo(articuloEntity.getTipo());
			articulo.setUnidad(articuloEntity.getUnidad());
			// articulo.setLotes(LoteDAO.getInstace().getLotesByCodigoArticulo(codArticulo));
			// articulo.setMovimiento(MovimientoDAO.getInstace().getMovimientosByCodigoArticulo(codArticulo));

			for (LoteEntity loteEntity : articuloEntity.getLotes()) {

				Lote lote = new Lote();
				// lote.setCodLote(LoteDAO.getInstace().getLotesByCodigoArticulo(loteEntity.getCodLote()));
				lote.setCodLote(loteEntity.getCodLote());
				lote.setFechaVtoLote(loteEntity.getFechaVtoLote());

				for (UbicacionEntity ubicacionEntity : loteEntity.getUbicaciones()) {

					Ubicacion ubicacion = new Ubicacion();
					ubicacion.setCantOcupada(ubicacionEntity.getCantOcupada());
					ubicacion.setCapacidadMax(ubicacionEntity.getCapacidadMax());
					ubicacion.setCodigoUbicacion(ubicacionEntity.getCodigoUbicacion());
					ubicacion.setNroUbicacion(ubicacionEntity.getNroUbicacion());
					lote.getUbicaciones().add(ubicacion);
				}

				articulo.getLotes().add(lote);
			}

			for (MovimientoEntity movimientoEntity : articuloEntity.getMovimientos()) {

				Movimiento movimiento = new Movimiento();
				movimiento.setCant(movimientoEntity.getCant());
				movimiento.setEmpleado(movimientoEntity.getEmpleado());
				movimiento.setMotivoAjuste(movimientoEntity.getMotivoAjuste());
				movimiento.setNroMovimiento(movimientoEntity.getNroMovimiento());
				movimiento.setNroOrdenDeCompra(movimientoEntity.getNroOrdenDeCompra());
				movimiento.setNroPedido(movimientoEntity.getNroPedido());
				movimiento.setTipoMovimiento(movimientoEntity.getTipoMovimiento());

				for (LoteEntity loteEntity : articuloEntity.getLotes()) {

					Lote lote = new Lote();
					lote.setCodLote(loteEntity.getCodLote());
					lote.setFechaVtoLote(loteEntity.getFechaVtoLote());

					for (UbicacionEntity ubicacionEntity : loteEntity.getUbicaciones()) {

						Ubicacion ubicacion = new Ubicacion();
						ubicacion.setCantOcupada(ubicacionEntity.getCantOcupada());
						ubicacion.setCapacidadMax(ubicacionEntity.getCapacidadMax());
						ubicacion.setCodigoUbicacion(ubicacionEntity.getCodigoUbicacion());
						ubicacion.setNroUbicacion(ubicacionEntity.getNroUbicacion());
						lote.getUbicaciones().add(ubicacion);
					}

					movimiento.getLotes().add(lote);
				}

				articulo.getMovimientos().add(movimiento);
			}
		}

		catch (Exception e) {
			throw new ArticuloException("<articulo no encontrado en Articulo DAo");

		} finally {

			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return articulo;
	}
	
	//---------------------------------------------------------

	public ArticuloEntity findArticuloByCodigoE(Integer codArticulo) {

		//ArticuloEntity articuloE = null;
		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().openSession();

			ArticuloEntity articuloEntity = (ArticuloEntity) session.load(ArticuloEntity.class, codArticulo);
			return articuloEntity;
		} catch (Exception e) {
			
		}
		return null;
	}

/*			articuloE.setCantAComprar(articuloEntity.getCantAComprar());
//			articuloE.setCantFuturoDisponible(articuloEntity.getCantFuturoDisponible());
//			articuloE.setCantReal(articuloEntity.getCantReal());
//			articuloE.setCantReservada(articuloEntity.getCantReservada());
//			articuloE.setCodArticulo(articuloEntity.getCodArticulo());
//			articuloE.setCodigoBarras(articuloEntity.getCodigoBarras());
//			articuloE.setDescripcion(articuloEntity.getDescripcion());
//			articuloE.setMarca(articuloEntity.getMarca());
//			articuloE.setPrecio(articuloEntity.getPrecio());
//			articuloE.setPresentacion(articuloEntity.getPresentacion());
//			articuloE.setTamano(articuloEntity.getTamano());
//			articuloE.setTipo(articuloEntity.getTipo());
//			articuloE.setUnidad(articuloEntity.getUnidad());
//			//articulo.setLotes(LoteDAO.getInstace().getLotesByCodigoArticulo(codArticulo));
//			//articulo.setMovimiento(MovimientoDAO.getInstace().getMovimientosByCodigoArticulo(codArticulo));
//
//			for (LoteEntity loteEntity : articuloEntity.getLotes()) {
//
//				Lote lote = new Lote();
//				// lote.setCodLote(LoteDAO.getInstace().getLotesByCodigoArticulo(loteEntity.getCodLote()));
//				lote.setCodLote(loteEntity.getCodLote());
//				lote.setFechaVtoLote(loteEntity.getFechaVtoLote());
//
//				for (UbicacionEntity ubicacionEntity : loteEntity.getUbicaciones()) {
//
//					Ubicacion ubicacion = new Ubicacion();
//					ubicacion.setCantOcupada(ubicacionEntity.getCantOcupada());
//					ubicacion.setCapacidadMax(ubicacionEntity.getCapacidadMax());
//					ubicacion.setCodigoUbicacion(ubicacionEntity.getCodigoUbicacion());
//					ubicacion.setNroUbicacion(ubicacionEntity.getNroUbicacion());
//					lote.getUbicaciones().add(ubicacion);
//				}
//
//				articulo.getLotes().add(lote);
//			}
//
//			for (MovimientoEntity movimientoEntity : articuloEntity.getMovimientos()) {
//
//				Movimiento movimiento = new Movimiento();
//				movimiento.setCant(movimientoEntity.getCant());
//				movimiento.setEmpleado(movimientoEntity.getEmpleado());
//				movimiento.setMotivoAjuste(movimientoEntity.getMotivoAjuste());
//				movimiento.setNroMovimiento(movimientoEntity.getNroMovimiento());
//				movimiento.setNroOrdenDeCompra(movimientoEntity.getNroOrdenDeCompra());
//				movimiento.setNroPedido(movimientoEntity.getNroPedido());
//				movimiento.setTipoMovimiento(movimientoEntity.getTipoMovimiento());
//
//				for (LoteEntity loteEntity : articuloEntity.getLotes()) {
//
//					Lote lote = new Lote();
//					lote.setCodLote(loteEntity.getCodLote());
//					lote.setFechaVtoLote(loteEntity.getFechaVtoLote());
//
//					for (UbicacionEntity ubicacionEntity : loteEntity.getUbicaciones()) {
//
//						Ubicacion ubicacion = new Ubicacion();
//						ubicacion.setCantOcupada(ubicacionEntity.getCantOcupada());
//						ubicacion.setCapacidadMax(ubicacionEntity.getCapacidadMax());
//						ubicacion.setCodigoUbicacion(ubicacionEntity.getCodigoUbicacion());
//						ubicacion.setNroUbicacion(ubicacionEntity.getNroUbicacion());
//						lote.getUbicaciones().add(ubicacion);
//					}
//
//					movimiento.getLotes().add(lote);
//				}
//
//				articulo.getMovimientos().add(movimiento);
//			}
//		}
*/


	
	//-------------------------------------------
	
	public Articulo toNegocio2(ArticuloEntity articulo) {
		Articulo res = new Articulo();
		res.setCodArticulo(articulo.getCodArticulo());
		res.setCodigoBarras(articulo.getCodigoBarras());
		res.setMarca(articulo.getMarca());
		res.setDescripcion(articulo.getDescripcion());
		res.setPresentacion(articulo.getPresentacion());
		res.setTamano(articulo.getTamano());
		res.setUnidad(articulo.getUnidad());
		res.setCantAComprar(articulo.getCantAComprar());
		res.setTipo(articulo.getTipo());
		res.setPrecio(articulo.getPrecio());
		res.setCantReal(articulo.getCantReal());
		res.setCantFuturoDisponible(articulo.getCantFuturoDisponible());
		res.setCantReservada(articulo.getCantReservada());

//	
//		if (articulo.getLotes() !=null){
//		
//			for(LoteEntity loteAux : articulo.getLotes()) {
//				Lote lote = new Lote();
//				lote.setCodLote(loteAux.getCodLote());
//				lote.setFechaVtoLote(loteAux.getFechaVtoLote());
//				
//				for (UbicacionEntity ubiAux : lote.getUbicaciones()) {
//					Ubicacion ubicacion = new Ubicacion();
//					ubicacion.setCantOcupada(ubiAux.getCantOcupada());
//					ubicacion.set
//				}
//
//			}
//		}
//		if (articulo.getLotes() != null) {
//			for(LoteEntity loteAux : articulo.getLotes()) {
//				Lote lote = new Lote();
//				lote.setCodLote(loteAux.getCodLote());
//				lote.setMovimiento(lote.getMovimiento());
//				//lote
//			}
//		}
		return res;
	}
		
	public Articulo toNegocio(ArticuloEntity articuloE) {
		Articulo res = new Articulo();
		res.setCodArticulo(articuloE.getCodArticulo());
		res.setCodigoBarras(articuloE.getCodigoBarras());
		res.setMarca(articuloE.getMarca());
		res.setTipo(articuloE.getTipo());
		res.setDescripcion(articuloE.getDescripcion());
		res.setPresentacion(articuloE.getPresentacion());
		res.setTamano(articuloE.getTamano());
		res.setUnidad(articuloE.getUnidad());
		res.setCantAComprar(articuloE.getCantAComprar());
		res.setCantReservada(articuloE.getCantReservada());
		res.setPrecio(articuloE.getPrecio());
		res.setCantFuturoDisponible(articuloE.getCantFuturoDisponible());
		res.setCantReal(articuloE.getCantReal());
			
		for(LoteEntity loteAux : articuloE.getLotes()) {
			Lote lote = new Lote();
			lote.setCodLote(loteAux.getCodLote());
			lote.setFechaVtoLote(loteAux.getFechaVtoLote());
			
			for (UbicacionEntity ubiAux : loteAux.getUbicaciones()) {
				Ubicacion ubicacion = new Ubicacion();
				ubicacion.setCantOcupada(ubiAux.getCantOcupada());
				ubicacion.setCapacidadMax(ubiAux.getCapacidadMax());
				ubicacion.setCodigoUbicacion(ubiAux.getCodigoUbicacion());
				ubicacion.setNroUbicacion(ubiAux.getNroUbicacion());
				lote.getUbicaciones().add(ubicacion);
			}
			res.getLotes().add(lote);
		}
		//No cargamos todos los estados 
		for(MovimientoEntity movAux : articuloE.getMovimientos()) {
			Movimiento movimiento = new Movimiento();
			movimiento.setCant(movAux.getCant());
			res.getMovimientos().add(movimiento);
			
		}
		return res;
	
	}

	
	public ArticuloEntity toEntity(Articulo articulo) {
		ArticuloEntity res = new ArticuloEntity();
		res.setCodArticulo(articulo.getCodArticulo());
		res.setDescripcion(articulo.getDescripcion());
		res.setPrecio(articulo.getPrecio());
		res.setCantReal(articulo.getCantReal());
		res.setCantFuturoDisponible(articulo.getCantFuturoDisponible());
		res.setCantReservada(articulo.getCantReservada());
	
		return res;
	}



	
}
