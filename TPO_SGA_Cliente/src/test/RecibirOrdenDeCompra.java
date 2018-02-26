package test;

import java.util.ArrayList;
import java.util.List;

import businessDelegate.BusinessDelegate;
import dto.OrdenDeCompraDTO;
import dto.OrdenDeTrabajoDTO;
import dto.RecepcionCompraDTO;
import exceptions.OCException;
import exceptions.SistemaException;

public class RecibirOrdenDeCompra {

	public static void main(String[] args) {
		
		OrdenDeCompraDTO ordenDeCompraDTO = new OrdenDeCompraDTO();
		List<RecepcionCompraDTO> lista = new ArrayList<RecepcionCompraDTO>();
		List<OrdenDeTrabajoDTO> ots = new ArrayList<OrdenDeTrabajoDTO>();
		
		try {
			//ordenDeCompraDTO = BusinessDelegate.getInstance().findOC(ordenDeCompraDTO.getNroOrdenDeCompra());
			//ordenDeCompraDTO = BusinessDelegate.getInstance().findOC(1);
	
			
			lista = BusinessDelegate.getInstance().recibirOC(1);			
			lista.toString();
			
			//acepto la orden de compra, le paso la lista de recepcion compra, y el codigo de la OC aceptada
			ots = BusinessDelegate.getInstance().aceptarRecepcionCompra(lista, 1);
			
	
		} catch (SistemaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
