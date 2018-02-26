package controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ArticuloDAO;
import dao.ClienteDAO;
import dao.PedidoDAO;
import dao.UbicacionDAO;
import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.PedidoDTO;
import enumerations.EstadoPedido;
import exceptions.ArticuloException;
import exceptions.ClienteException;
import exceptions.DAOException;
import exceptions.PedidoException;
import negocio.Articulo;
import negocio.Pedido;
import negocio.Ubicacion;

public class ControladorDespacho {

	private static ControladorDespacho instancia;

	public static ControladorDespacho getInstancia() {
		if (instancia == null)
			instancia = new ControladorDespacho();
		return instancia;
	}

	public ClienteDTO findCliente(int nroCliente) throws ClienteException {
		ClienteDTO cliente = new ClienteDTO();
		try {
			cliente = ClienteDAO.getInstancia().findCliente(nroCliente).toDTO();
		} catch (DAOException e) {
			throw new ClienteException("No se encontro el cliente");
		}

		return cliente;
	}


	public ArticuloDTO findArticuloByCodigoE(int nroArticulo) throws ArticuloException {
		ArticuloDTO articulo = new ArticuloDTO();
		try {
			articulo = ArticuloDAO.getInstancia().findArticulo(nroArticulo).toDTO();
		} catch (DAOException e) {
			throw new ArticuloException("No se encontro el articulo");
		}
		return articulo;
	}
	
	public PedidoDTO findPedido(int nroPedido) throws PedidoException {
		PedidoDTO pedido = new PedidoDTO();
		try {
			pedido = PedidoDAO.getInstancia().findPedido(nroPedido).toDTO();
			//pedido = PedidoDAO.getInstancia().findPedido(nroPedido).toDTOAprobarPedido();
		} catch (DAOException e) {
			throw new PedidoException("No se encontro el pedido");
		}
		return pedido;
	}

	public void altaPedido(PedidoDTO pedidoDTO) throws PedidoException, DAOException, ClienteException {
		// PedidoDAO dao = PedidoDAO.getInstancia();

		Pedido pedido = new Pedido();
		try {
			pedido = PedidoDAO.getInstancia().toNegocio(pedidoDTO);
		} catch (ArticuloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Desde Controlador: " + pedido.toString());
		//PedidoDAO.getInstancia().altaPedido(pedido);

		// for(ItemPedidoDTO itemPedidoDTO : pedidoDTO.getItemsPedido()) {
		// ItemPedido itemP = new ItemPedido();
		// itemP.setCantidad(itemPedidoDTO.getCantidad());
		// pedido.getItemsPedido().add(itemP);
		// }

		// PedidoDAO.getInstancia().altaPedido(pedido); --> funciona

		// Pedido pe = dao.toNegocio(pedido);
		// List<Articulo> articulosFaltantes = new ArrayList<Articulo>();

		 //Se verifica el limite de credito
		 if(pedido.controlarLimiteCredito() == false) {
			 pedido.setEstadoPedido(EstadoPedido.Rechazado);
		 } else {
			 pedido.setEstadoPedido(EstadoPedido.Nuevo);
		 }
		 //System.out.println(pedido.toString());
		 //Seteo la fecha de hoy
		 pedido.setFechaGeneracion(new Date());
		// //Se modifica el saldo del cliente
		// Cliente cli = pe.getCliente();
		// cli.setSaldo(cli.getSaldo() - pe.getTotalPedido());
		// ClienteDAO.getInstancia().guardarCliente(ClienteDAO.getInstancia().toEntity(cli));
		// //Se verifica la existencia de stock del pedido
		// if(articulosFaltantes.size() != 0) {
		// ControladorCompra.getInstancia().generarOrdenDeCompra(pe.controlarStockPedido());
		// }else {
		// pe.setEstadoPedido(EstadoPedido.Completado);
		// }
		 PedidoDAO.getInstancia().altaPedido(pedido);
//		 PedidoDAO.getInstancia().guardarPedido( pedido);
	}

	public List<PedidoDTO> getPedidosNuevos(int nroCliente) {
		List<PedidoDTO> resultado = new ArrayList<PedidoDTO>();
		List<Pedido> pedidos = PedidoDAO.getInstancia().getPedidosNuevos(nroCliente);
		for (Pedido pedido : pedidos)
			resultado.add(pedido.toDTO());
		return resultado;
	}

	public void aprobarPedido(int nroPedido) throws ArticuloException {
		
			Pedido pedido = new Pedido();
			Articulo articulo = new Articulo();
//			List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
//			ubicaciones.get(0).setCantOcupada(333);
//			System.out.println(ubicaciones.get(0).getCantOcupada());
//			ubicaciones = UbicacionDAO.getInstace().getUbicacionesByCodigoLote(1);
//			System.out.println(ubicaciones.get(0).getCodigoUbicacion());
//			articulo = ArticuloDAO.getInstancia().findArticuloByCodigo(1);
//			System.out.println(articulo.getDescripcion());
//			System.out.println(articulo.getLotes().get(0).getCodLote());
//			System.out.println(articulo.getLotes().get(0).getUbicaciones().get(0).getCapacidadMax());
			try {
				pedido = PedidoDAO.getInstancia().findPedido(nroPedido);
				pedido.setEstadoPedido(EstadoPedido.Pendiente);
				float auxSaldoActual = pedido.getCliente().getSaldo();
				pedido.getCliente().setSaldo(auxSaldoActual - pedido.getTotal());
				PedidoDAO.getInstancia().actualizarEstadoPedido(pedido);
			} catch (DAOException e) {;
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PedidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(pedido.getItemsPedido().get(0).getArticulo().getDescripcion());
//			System.out.println(pedido.getCliente().getCuit());
			
	
		
		
	}



	
	
}
