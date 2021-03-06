package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Lotes")
public class LoteEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codLote;
	private Date fechaVtoLote;

	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name = "codLote")
	private List<UbicacionEntity> ubicaciones;

//	@ManyToOne
//	@JoinColumn(name = "codArticulo")
//	private ArticuloEntity articulo;

//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name="LotesMovimientos")
//	private List<MovimientoEntity> movimientos;


	// Constructor
	public LoteEntity() {
		this.ubicaciones = new ArrayList<UbicacionEntity>();
		//this.movimientos = new ArrayList<MovimientoEntity>();
	}

	public int getCodLote() {
		return codLote;
	}

	public void setCodLote(int codLote) {
		this.codLote = codLote;
	}

	public Date getFechaVtoLote() {
		return fechaVtoLote;
	}

	public void setFechaVtoLote(Date fechaVtoLote) {
		this.fechaVtoLote = fechaVtoLote;
	}

	public List<UbicacionEntity> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<UbicacionEntity> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	@Override
	public String toString() {
		return "LoteEntity [codLote=" + codLote + ", fechaVtoLote=" + fechaVtoLote + ", ubicaciones=" + ubicaciones
				+ "]";
	}

//	public ArticuloEntity getArticulo() {
//		return articulo;
//	}
//
//	public void setArticulo(ArticuloEntity articulo) {
//		this.articulo = articulo;
//	}
//
//	public List<MovimientoEntity> getMovimientos() {
//		return movimientos;
//	}
//
//	public void setMovimientos(List<MovimientoEntity> movimientos) {
//		this.movimientos = movimientos;
//	}

	
	
	

}
