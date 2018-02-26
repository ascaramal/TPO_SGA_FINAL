package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dto.ArticuloDTO;
import dto.ClienteDTO;
import dto.PedidoDTO;
import exceptions.ArticuloException;
import exceptions.ClienteException;
import exceptions.DAOException;
import exceptions.PedidoException;


public interface INegocio extends Remote {

	public List<ClienteDTO> getClientes() throws RemoteException;
	public ArticuloDTO findArticulo(int nroArticulo) throws RemoteException, ArticuloException;
	public ClienteDTO findCliente(int nroCLiente) throws RemoteException, ClienteException;
	public void nuevoPedido(PedidoDTO pedidoDTO) throws RemoteException, PedidoException, DAOException, ClienteException;
	public List<PedidoDTO> getPedidosNuevos(int nroCliente) throws RemoteException;
	public PedidoDTO findPedido(int nroPedido) throws RemoteException, PedidoException;
	public List<PedidoDTO> getPedidos() throws RemoteException;
	public void aprobarPedido(int nroPedido) throws RemoteException;

}
