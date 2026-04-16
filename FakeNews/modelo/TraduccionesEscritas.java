package fakeNews.modelo;

public class TraduccionesEscritas {
	
	private String nombreDiario;
	private String nombreNota;
	private String cantidadDeVisualizaciones;
	public String getNombreDiario() {
		return nombreDiario;
	}
	public void setNombreDiario(String nombreDiario) {
		this.nombreDiario = nombreDiario;
	}
	public String getNombreNota() {
		return nombreNota;
	}
	public void setNombreNota(String nombreNota) {
		this.nombreNota = nombreNota;
	}
	public void setCantidadDevisualizaciones(String cantidadDeVisualizaciones) {
		this.cantidadDeVisualizaciones = cantidadDeVisualizaciones;
	}
	public String getCantidadDeVisualizaciones() {
		return cantidadDeVisualizaciones;
	}
	
}
