package entities;	

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Remitos")
public class RemitoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nroRemito")
	private int numero;
	
	private int prefijo;
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="nroCliente")
	private ClienteEntity cliente;
	
	@OneToMany
	@JoinColumn(name="nroRemito")
	private List<ItemRemitoEntity> items;
	

	
	//Constructor
	public RemitoEntity() {
		this.items = new ArrayList<ItemRemitoEntity>();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(int prefijo) {
		this.prefijo = prefijo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public List<ItemRemitoEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemRemitoEntity> items) {
		this.items = items;
	}
	
	
}
