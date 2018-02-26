package dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import entities.ItemPedidoEntity;
import exceptions.DAOException;
import hbt.HibernateUtil;
import negocio.ItemPedido;

public class ItemPedidoDAO {
	private static ItemPedidoDAO instancia;
	private static SessionFactory sf;
	
	private ItemPedidoDAO() {}

	public static ItemPedidoDAO getInstancia() {
		if(instancia == null) {
			instancia = new ItemPedidoDAO();
			sf = HibernateUtil.getSessionFactory();
		}
		return instancia;
	}
	
	
	public ItemPedidoEntity findItemPedido (int nroPedido)  {
		ItemPedido ip = null; 
//		try {
			Session s = sf.openSession();
			s.beginTransaction();
			@SuppressWarnings("unchecked")
			ItemPedidoEntity ipE = (ItemPedidoEntity) s.createQuery("from ItemPedidoEntity  where nroItemPedido = :itemPedido")
					.setParameter("itemPedido", nroPedido).uniqueResult(); 
			s.getTransaction().commit(); 
			s.close();	
//			sip = this.toNegocio(ipE);
			return ipE;
	}
//		} catch (Exception e) {
//			System.out.println(e);
//			System.out.println("Error ItemPedidoDAO.findItemPedido");
////		}
//		return null;
//	}

//	private ItemPedido toNegocio(ItemPedidoEntity ipE) {
//		ItemPedido ip = new ItemPedido();
//		ip.setCantidad(ipE.getCantidad());
//		ip.setNroItemPedido(ipE.getNroItemPedido());
//		ip.setArticulo(ArticuloDAO.getInstancia().toNegocio(ipE.getArticulo()));
//		return ip;
//	}
}
