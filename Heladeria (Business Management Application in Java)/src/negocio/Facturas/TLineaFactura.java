package negocio.Facturas;

public class TLineaFactura {
    private int idLinea;
    private int idFactura;
    private int idProducto;
    private int cantidad;
    private float precio;

    public TLineaFactura(int idLinea, int idFactura, int idProducto, int cantidad, float precio) {
        this.idLinea = idLinea;
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    
    public TLineaFactura() {}

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public float getPrecio() {
    	return precio;
    }
    
    public void setPrecio(float precio) {
    	this.precio = precio; 
    }
    
    @Override
    public String toString() {
    	 StringBuilder sb = new StringBuilder();
    	 sb.append("idLinea: ").append(idLinea).append("\n");
         sb.append("idFactura: ").append(idFactura).append("\n");
         sb.append("idProducto: ").append(idProducto).append("\n");
         sb.append("cantidad: ").append(cantidad).append("\n");
         sb.append("precio: ").append(precio).append("\n");
         
         return sb.toString();
    }
}
