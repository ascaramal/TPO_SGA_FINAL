package test;

import businessDelegate.BusinessDelegate;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.ItemPedidoDTO;
import dto.PedidoDTO;
import exceptions.ArticuloException;
import exceptions.ClienteException;
import exceptions.DAOException;
import exceptions.PedidoException;
import exceptions.SistemaException;

public class AltaPedido {

	public static void main(String[] args) {

			
				try {
					generarPedido();
				} catch (PedidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			 
	}
	
	public static void generarPedido() throws PedidoException, DAOException    {
//		ClienteDTO cliente = new ClienteDTO();
//		cliente.setNroCliente(2);
//		ClienteDTO clienteVuelta = new ClienteDTO();
//		try {
//			clienteVuelta = BusinessDelegate.getInstance().findCliente(cliente.getNroCliente());
//			//System.out.println(clienteVuelta.toString());
//		} catch (ClienteException e1) {
//			e1.printStackTrace();
//		} catch (SistemaException e1) {
//			e1.printStackTrace();
//		}

//		if (clienteVuelta != null) {
			PedidoDTO pedidoDTO = new PedidoDTO();
			ClienteDTO clienteDTO = new ClienteDTO();
			ArticuloDTO articuloDTO1 = new ArticuloDTO();
			clienteDTO.setNroCliente(1);
//			PedidoDTO pedidoDTO2 = new Ped PedidoDTO(nroPedido,  estadoPedido, fechaGeneracion, fechaDespacho, itemsPedido, ordenesDeTrabajo, total)
			ItemPedidoDTO iPedido1 = new ItemPedidoDTO();
			ItemPedidoDTO iPedido2 = new ItemPedidoDTO();
			ItemPedidoDTO iPedido3 = new ItemPedidoDTO();

			pedidoDTO.setCliente(clienteDTO);
			articuloDTO1.setCodArticulo(1);

			//				iPedido1.setArticulo(BusinessDelegate.getInstance().findArticulo(1));
							iPedido1.setArticulo(articuloDTO1);
							iPedido1.setCantidad(100);
							pedidoDTO.agregarItemPedido(iPedido1);

//			//				iPedido2.setArticulo(BusinessDelegate.getInstance().findArticulo(2));
//							iPedido2.getArticulo().setCodArticulo(2);
//							iPedido2.setCantidad(100);
//							pedidoDTO.agregarItemPedido(iPedido2);
//
//			//				iPedido3.setArticulo(BusinessDelegate.getInstance().findArticulo(3));
//							iPedido3.getArticulo().setCodArticulo(3);
//							iPedido3.setCantidad(100);
//							pedidoDTO.agregarItemPedido(iPedido3);
			
			//Se debe calcular el total
			pedidoDTO.setTotal(100.0f); 

			//System.out.println(pedidoDTO.toString());
			try {
				try {
					BusinessDelegate.getInstance().nuevoPedido(pedidoDTO);
				} catch (ClienteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//BusinessDelegate.getInstance().guardarPedido(pedidoDTO);
			} catch (SistemaException e) {
				e.printStackTrace();
			}

//		} else {
//			System.out.println("Cliente Inexistente");
//		}
	}

}
