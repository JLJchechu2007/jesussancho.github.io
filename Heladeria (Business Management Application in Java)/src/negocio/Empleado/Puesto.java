package negocio.Empleado;

public enum Puesto {
	DIRECTIVO,VENDEDOR;
	
	public String toString() {
		if (this.equals(DIRECTIVO)) return "directivo";
		else return "vendedor";
	}
}

