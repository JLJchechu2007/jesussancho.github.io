package presentacion.Controlador;

public class Eventos {
	
	//General
	public static final int MAIN_WINDOW=0;
    
	
    // Facturas
    public static final int ABRIR_VENTA = 100;
    public static final int AÑADIR_PRODUCTO = 101;
    public static final int RES_AÑADIR_PRODUCTO_OK = 102;
    public static final int RES_AÑADIR_PRODUCTO_KO = 103;
    public static final int CERRAR_VENTA = 104;
    public static final int RES_CERRAR_VENTA_OK = 105;
    public static final int RES_CERRAR_VENTA_KO = 106;
    public static final int BUSCAR_FACTURA = 107;
    public static final int RES_BUSCAR_FACTURA_OK = 108;
    public static final int RES_BUSCAR_FACTURA_KO = 109;
    public static final int MOSTRAR_FACTURAS = 110;
    public static final int RES_MOSTRAR_FACTURAS_OK = 111;
    public static final int RES_MOSTRAR_FACTURAS_KO = 112;
    public static final int MOSTRAR_FACTURAS_POR_CLIENTE = 113;
    public static final int RES_MOSTRAR_FACTURAS_POR_CLIENTE_OK = 114;
    public static final int RES_MOSTRAR_FACTURAS_POR_CLIENTE_KO = 115;

    
    //Empleados
    public static final int ALTA_EMPLEADO = 600;
    public static final int BAJA_EMPLEADO = 601;
    public static final int MODIFICAR_EMPLEADO = 602;
    public static final int MOSTRAR_EMPLEADO = 603;
    public static final int BUSCAR_EMPLEADO = 604;
    public static final int RES_ALTA_EMPLEADO_OK = 605;
    public static final int RES_BAJA_EMPLEADO_OK = 606;
    public static final int RES_MODIFICAR_EMPLEADO_OK = 607;
    public static final int RES_BUSCAR_EMPLEADO_OK = 608;
    public static final int RES_MOSTRAR_EMPLEADOS_OK = 609;
    public static final int RES_ALTA_EMPLEADO_KO = 610;
    public static final int RES_BAJA_EMPLEADO_KO = 611;
    public static final int RES_MODIFICAR_EMPLEADO_KO = 612;
    public static final int RES_BUSCAR_EMPLEADO_KO = 613;
    public static final int RES_MOSTRAR_EMPLEADOS_KO = 614;
    public static final int MODIFICAR_EMPLEADO_FINAL = 615;
    public static final int RES_MODIFICAR_EMPLEADO_FINAL_OK=616;
    public static final int RES_MODIFICAR_EMPLEADO_FINAL_KO=617;

    
  
    //Producto
    public static final int NUEVO_PRODUCTO=210;
    public static final int BUSCAR_PRODUCTO=211;
    public static final int ELIMINAR_PRODUCTO=212;
    public static final int MODIFICAR_PRODUCTO=213;
    public static final int MODIFICAR_PRODUCTO_FINAL=215;
    public static final int MOSTRAR_PRODUCTOS=214;
    public static final int MOSTRAR_PRODUCTOS_POR_MARCA=218;
    public static final int RES_NUEVO_PRODUCTO_OK = 200;
    public static final int RES_ELIMINAR_PRODUCTO_OK = 201;
    public static final int RES_MODIFICAR_PRODUCTO_OK = 202;
    public static final int RES_MOSTRAR_PRODUCTOS_OK= 203;
    public static final int RES_BUSCAR_PRODUCTO_OK = 204;
    public static final int RES_NUEVO_PRODUCTO_KO = 205;
    public static final int RES_ELIMINAR_PRODUCTO_KO = 206;
    public static final int RES_MODIFICAR_PRODUCTO_KO = 207;
    public static final int RES_MOSTRAR_PRODUCTOS_KO= 208;
    public static final int RES_BUSCAR_PRODUCTO_KO = 209;
    public static final int RES_MOSTRAR_PRODUCTOS_POR_MARCA_OK=219;
    public static final int RES_MOSTRAR_PRODUCTOS_POR_MARCA_KO = 220;
    public static final int RES_MODIFICAR_PRODUCTO_FINAL_OK = 216;
    public static final int RES_MODIFICAR_PRODUCTO_FINAL_KO = 217;
    
  //Marcas
    public static final int AÑADIR_MARCA = 500;
    public static final int ELIMINAR_MARCA = 501;
    public static final int MODIFICAR_MARCA = 502;
    public static final int MODIFICAR_ATRIBUTOS_MARCA = 503;
    public static final int BUSCAR_MARCA = 504;
    public static final int MOSTRAR_MARCAS = 505;
    public static final int RES_AÑADIR_MARCA_OK= 506;
    public static final int RES_AÑADIR_MARCA_KO= 507;
    public static final int RES_ELIMINAR_MARCA_OK= 508;
    public static final int RES_ELIMINAR_MARCA_KO= 509;
    public static final int RES_MODIFICAR_MARCA_OK= 510;
    public static final int RES_MODIFICAR_MARCA_KO= 511;
    public static final int RES_BUSCAR_MARCA_OK= 512;
    public static final int RES_BUSCAR_MARCA_KO= 513;
    public static final int RES_MODIFICAR_ATRIBUTOS_MARCA_OK= 514;
    public static final int RES_MODIFICAR_ATRIBUTOS_MARCA_KO= 515;
    public static final int RES_MOSTRAR_MARCAS_OK= 516;
    public static final int RES_MOSTRAR_MARCAS_KO= 517;
    
    
    
  //Proveedor
    public static final int NUEVO_PROVEEDOR=401;
    public static final int BUSCAR_PROVEEDOR=402;
    public static final int ELIMINAR_PROVEEDOR=403;
    public static final int MODIFICAR_PROVEEDOR=404;
    public static final int MOSTRAR_PROVEEDOR=405;
    public static final int VINCULACIONES = 406;
    public static final int RES_NUEVO_PROVEEDOR_OK = 407;
    public static final int RES_ELIMINAR_PROVEEDOR_OK = 408;
    public static final int RES_MODIFICAR_PROVEEDOR_OK = 409;
    public static final int RES_MOSTRAR_PROVEEDOR_OK= 410;
    public static final int RES_BUSCAR_PROVEEDOR_OK = 411;
    public static final int RES_NUEVO_PROVEEDOR_KO = 412;
    public static final int RES_ELIMINAR_PROVEEDOR_KO = 413;
    public static final int RES_MODIFICAR_PROVEEDOR_KO = 414;
    public static final int RES_MOSTRAR_PROVEEDOR_KO= 415;
    public static final int RES_BUSCAR_PROVEEDOR_KO = 416;
    public static final int RES_VINCULACIONES_PROVEEDOR_OK= 417;
    public static final int RES_VINCULACIONES_PROVEEDOR_KO = 418;
    public static final int MODIFICAR_PROVEEDOR1 = 419; 
    public static final int RES_MODIFICAR_PROVEEDOR1_KO = 420;
    
    
    //Clientes
    //Opens the initial window for clientes
    public static final int WINDOW_CLIENTES = 700;
     
	public static final int ALTA_CLIENTE = 710;
	public static final int BAJA_CLIENTE = 711;
	public static final int BUSCAR_CLIENTE = 712;
	public static final int LISTAR_CLIENTE = 713;
	public static final int MODIFICAR_CLIENTE_INITIAL = 714;
	public static final int MODIFICAR_CLIENTE_FINAL = 715;
	
	public static final int EXITO_ALTA_CLIENTE = 720;
	public static final int EXITO_BAJA_CLIENTE = 721;
	public static final int EXITO_BUSCAR_CLIENTE = 722;
	public static final int EXITO_LISTAR_CLIENTE = 723;
	public static final int EXITO_MODIFICAR_CLIENTE = 724;
	
	public static final int SIN_EXITO_ALTA_CLIENTE = 730;
	public static final int SIN_EXITO_BAJA_CLIENTE = 731;
	public static final int SIN_EXITO_BUSCAR_CLIENTE = 732;
	public static final int SIN_EXITO_LISTAR_CLIENTE = 733;
	public static final int SIN_EXITO_MODIFICAR_CLIENTE = 734;	
    
}

