package entities;

import java.util.ArrayList;
import java.util.List;

import enumerations.EstadoOC;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="OrdenesCompra")
public class OrdenDeCompraEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nroOC")
	private int nroOrdenDeCompra;
	private int nroProveedor;
	private EstadoOC estado;
	
	//@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="nroOC")
	private List<ItemOrdenDeCompraEntity> itemsOC;
	
	
	//Constructor
	public OrdenDeCompraEntity() {
		this.itemsOC = new ArrayList<ItemOrdenDeCompraEntity>();
	}
	
	public int getNroOrdenDeCompra() {
		return nroOrdenDeCompra;
	}

	public void setNroOrdenDeCompra(int nroOrdenDeCompra) {
		this.nroOrdenDeCompra = nroOrdenDeCompra;
	}

	public int getNroProveedor() {
		return nroProveedor;
	}

	public void setNroProveedor(int nroProveedor) {
		this.nroProveedor = nroProveedor;
	}

	public EstadoOC getEstado() {
		return estado;
	}

	public void setEstado(EstadoOC estado) {
		this.estado = estado;
	}

	public List<ItemOrdenDeCompraEntity> getItemsOC() {
		return itemsOC;
	}

	public void setItemsOC(List<ItemOrdenDeCompraEntity> itemsOC) {
		this.itemsOC = itemsOC;
	}


	public void imprimirOrdenDeCompra() { 
		
	}

	@Override
	public String toString() {
		return "OrdenDeCompraEntity [nroOrdenDeCompra=" + nroOrdenDeCompra + ", nroProveedor=" + nroProveedor
				+ ", estado=" + estado + ", itemsOC=" + itemsOC + "]";
	}
	
	
}
