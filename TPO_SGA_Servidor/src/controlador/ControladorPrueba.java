package controlador;

import dao.PedidoDAO;
import entities.ItemPedidoEntity;
import entities.PedidoEntity;
import enumerations.EstadoPedido;

public class ControladorPrueba {

	public static void main(String[] args) {

//		List<UbicacionEntity> ubicaciones = new ArrayList<UbicacionEntity>();
//		UbicacionEntity ubicacion = new UbicacionEntity();
//		ubicaciones.add(ubicacion);
//		ubicaciones.get(0).setCantOcupada(888);
//		System.out.println(ubicaciones.get(0).getCantOcupada());
//		ubicaciones = UbicacionDAO.getInstace().getUbicacionesByCodigoLote(1);
//		
//		System.out.println(ubicaciones.toString()); 
//		
//		List<LoteEntity> lotes = new ArrayList<LoteEntity>();
//		lotes = LoteDAO.getInstace().getLotesByCodigoArticulo(1);
//		System.out.println(lotes.toString());
		
//		ArticuloEntity articulo = new ArticuloEntity();
//		articulo = ArticuloDAO.getInstancia().findArticuloByCodigo(1);
//		System.out.println(articulo.toString());
//		
//		ItemPedidoEntity item = new ItemPedidoEntity();
//		item = ItemPedidoDAO.getInstancia().findItemPedido(1);
//		System.out.println(item.toString());
//		
		
		
       
		PedidoEntity pedE = new PedidoEntity();
		pedE = PedidoDAO.getInstancia().findPedidoByNumero(1);
		System.out.println(pedE.toString());
		
		
		 PedidoDAO.getInstancia().updateEstado(EstadoPedido.Facturado,pedE.getNroPedido());
		 
//		List<UbicacionEntity> ubicaciones = new ArrayList<UbicacionEntity>();
//		UbicacionEntity ubicacion = new UbicacionEntity();
//		ubicaciones.add(ubicacion);
//		ubicaciones.get(0).setCantOcupada(888);
//		System.out.println(ubicaciones.get(0).getCantOcupada());
//		ubicaciones = UbicacionDAO.getInstace().getUbicacionesByCodigoLote(1);
//		System.out.println(ubicaciones.toString()); 
//		ubicaciones.get(0).setCantOcupada(44);
//		 
//		UbicacionDAO.getInstace().update(ubicaciones.get(0));
//		System.out.println(ubicaciones.toString()); 
		
		
//		PedidoDAO.getInstancia().guardarPedido(pedE);
//		pedido = PedidoDAO.getInstancia().findPedidoByNumero(1);
//		System.out.println(pedE.toString());
//		
//		pedido.getCliente().setDireccion("AAAAAAAAA");
//		
//		pedido.guardar();
//		
//		PedidoDAO.getInstancia().update(pedido);
//		System.out.println(pedido.toString());
		
//		PedidoEntity pedido2 = new PedidoEntity();
//		
//		ItemPedidoEntity item = new ItemPedidoEntity();
//		pedido2.setTotal(1254);
//		item.setCantidad(200);
//		pedido2.getItemsPedido().add(item);
//		PedidoDAO.getInstancia().guardarPedido( pedido2);
		
//		OrdenDeCompraEntity ocompra = new OrdenDeCompraEntity();
//		ocompra= OrdenDeCompraDAO.getInstancia().findOCompraByNumero(1);
//		System.out.println(ocompra.toString());
//		ocompra= OrdenDeCompraDAO.getInstancia().findOCompraByNumero(1);
//		System.out.println(ocompra.toString());
		
		
		
		
		
		
		
		
		
		
		

	} 

}
