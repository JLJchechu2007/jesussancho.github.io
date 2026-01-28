package negocio.Producto;

public class TProducto {
private int id=-1;
private String tipoProducto="";
private float precioProducto=-1;
private int cantidad=-1;
private boolean alta=true;
private int idMarca=-1;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public float getPrecioProducto() {
	return precioProducto;
}
public void setPrecioProducto(float precioProducto) {
	this.precioProducto = precioProducto;
}
public String getTipoProducto() {
	return tipoProducto;
}
public void setTipoProducto(String tipoProducto) {
	this.tipoProducto = tipoProducto;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public boolean isAlta() {
	return alta;
}
public void setAlta(boolean alta) {
	this.alta = alta;
}
public int getIdMarca() {
	return idMarca;
}
public void setIdMarca(int idMarca) {
	this.idMarca = idMarca;
}

}
