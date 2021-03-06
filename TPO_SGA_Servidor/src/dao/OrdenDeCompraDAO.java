package dao;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import entities.ItemOrdenDeCompraEntity;
import entities.OrdenDeCompraEntity;
import entities.PedidoEntity;
import hbt.HibernateUtil;
import negocio.ItemOrdenDeCompra;
import negocio.OrdenDeCompra;
import negocio.Pedido;

public class OrdenDeCompraDAO {

	private static OrdenDeCompraDAO instancia;
	private static SessionFactory sf;
	
	private OrdenDeCompraDAO() {}

	public static OrdenDeCompraDAO getInstancia() {
		if(instancia == null) {
			instancia = new OrdenDeCompraDAO();
			sf = HibernateUtil.getSessionFactory();
		}
		return instancia;
	}
	
	private OrdenDeCompra toNegocio(OrdenDeCompraEntity odcE) {
		OrdenDeCompra ordenDeCompra = new OrdenDeCompra();
		
		ordenDeCompra.setNroOrdenDeCompra(odcE.getNroOrdenDeCompra());
		ordenDeCompra.setNroProveedor(odcE.getNroProveedor());
		
		//estado - Check
		ordenDeCompra.setEstado(odcE.getEstado());
		
		if (odcE.getItemsOC() != null) {
			
			for(ItemOrdenDeCompraEntity itemAux : odcE.getItemsOC()) {
				ItemOrdenDeCompra iodc = new ItemOrdenDeCompra();
				iodc.setArticulo(ArticuloDAO.getInstancia().toNegocio(itemAux.getArticulo()));
				iodc.setCantidad(itemAux.getCantidad());
				iodc.setNroItemOrdenDeCompra(itemAux.getNroItemOrdenDeCompra());
				ordenDeCompra.agregarItemOC(iodc);
			}	
		}
		
		return ordenDeCompra;
	}

	public OrdenDeCompraEntity findOCompraByNumero(int numeroOrdenDeCompra) {
		
			
			Session session = null;
			
	       		session = HibernateUtil.getSessionFactory().openSession();
				OrdenDeCompraEntity OCE = (OrdenDeCompraEntity) session.load(OrdenDeCompraEntity.class, numeroOrdenDeCompra);
				
		
		return OCE;
	}
}
