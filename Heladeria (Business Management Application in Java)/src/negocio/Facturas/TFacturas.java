package negocio.Facturas;

import java.util.List;

public class TFacturas {
	private int idFactura;
    private int idCliente;
    private String idVendedor;
    private String fecha;
    private float importeTotal; //precio total
    private boolean activo;
    private List<TLineaFactura> lineas;
    private String direccion; 

    public TFacturas(int idFactura, int idCliente, String idVendedor, String fecha, float importeTotal, boolean activo, String direccion, List<TLineaFactura> lineas) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.fecha = fecha;
        this.importeTotal = importeTotal;
        this.activo = activo;//Siempre se va a mantener true porque no admitimos devoluciones, aun así indicado por si acaso
        this.direccion = direccion; 
        this.lineas=lineas;//Las lineas se inicializan al estado del carrito al cerrar la factura
    }

    public TFacturas(int idCliente, String idVendedor, String fecha, float importeTotal, boolean activo, String direccion, List<TLineaFactura> lineas) { //constructor con contador
    	this.idFactura=0;
    	this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.fecha = fecha;
        this.importeTotal = importeTotal;
        this.activo = activo;//Siempre se va a mantener true porque no admitimos devoluciones, aun así indicado por si acaso
        this.direccion = direccion; 
        this.lineas=lineas;//Las lineas se inicializan al estado del carrito al cerrar la factura
    }
    
    public TFacturas () {} //constructor sin parámetros
    
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }
    
    public String getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getImporte() {
        return importeTotal;
    }

    public void setImporte(float importe) {
        this.importeTotal = importe;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<TLineaFactura> getLineas() {
        return lineas;
    }

    public void setLineas(List<TLineaFactura> lineas) {
        this.lineas = lineas;
    }
    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura ID: ").append(idFactura).append("\n");
        sb.append("Cliente ID: ").append(idCliente).append("\n");
        sb.append("Vendedor ID: ").append(idVendedor).append("\n");
        sb.append("Fecha: ").append(fecha).append("\n");
        sb.append("Importe Total: ").append(importeTotal).append("\n");
        sb.append("Activo: ").append(activo).append("\n");
        sb.append("Dirección: ").append(direccion).append("\n");
        sb.append("Líneas de factura: ").append("\n");

        for (TLineaFactura linea : lineas) {
            sb.append(linea.toString()).append("\n");
        }

        return sb.toString();
    }

}

