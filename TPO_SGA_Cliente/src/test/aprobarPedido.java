package test;

import businessDelegate.BusinessDelegate;
import dto.PedidoDTO;
import enumerations.EstadoPedido;
import exceptions.PedidoException;
import exceptions.SistemaException;

public class aprobarPedido {

	
	public static void main(String[] args) throws PedidoException {
	
		//El cliente administrador selecciona el n�mero de pedido a ser aprobado
		try {
			BusinessDelegate.getInstance().aprobarPedido(1);
		} catch (SistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		PedidoDTO pedidoDTO = new PedidoDTO();
//		
//		try {
//
//			
//			//Me traigo el pedido indicado
//			pedidoDTO = BusinessDelegate.getInstance().findPedido(1);
//			System.out.println(pedidoDTO.getNroPedido());
//			//System.out.println(pedidoDTO.getCliente().getRazonSocial());
//			//Seteo el estado Pendiente
//			pedidoDTO.setEstadoPedido(EstadoPedido.Pendiente);
//		} catch (SistemaException e1) {
//			e1.printStackTrace();
//		}
		
		
	}

}
