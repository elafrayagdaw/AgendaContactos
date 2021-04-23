package agenda.modelo;
/**
 * 
 * @author Iñaki Tiraplegui, Unai Pérez, Enrique Lafraya.
 *
 */
public abstract class Contacto{
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;

	public Contacto(String nombre, String apellidos, String telefono,
			String email) {
		this.nombre = nombre.toUpperCase();
		this.apellidos = apellidos.toUpperCase();
		this.telefono = telefono;
		this.email = email.toLowerCase();
	}

	/**
	 * Método accesor para el nombre
	 * @return Devuelve un String con el nombre del contacto
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Método mutador para el nombre
	 * @param Devuelve el nombre en formato String
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método accesor para los apellidos del contacto
	 * @return Devuelve un String con el apellido
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Método mutador para los apellidos del contacto
	 * @param Devuelve el apellido en formato String
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Método accesor para el teléfono del contacto
	 * @return Devuelve el teléfono del contacto en formato String
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Método mutador para el teléfono del contacto
	 * @param Deuvleve el teléfono del contacto en formato String
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Método accesor para el email del contacto
	 * @return Devuelve el email del contacto en formato String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Método mutador para el email del contacto
	 * @param Devuelve el email del contacto en formato String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return email.hashCode();

	}
	
	/**
	 * Método para sacar la primera letra de los apellidos del contacto
	 * @return Devuelve la primera letra del apellido
	 */
	public char getPrimeraLetra() {
		
		return apellidos.charAt(0);
	}
	
	/**
	 * Método para saber si dos objetos son iguales
	 * @param Introduces un objeto
	 * @return Devuelve True si dos objetos son iguales, false en caso contrario.
	 */
	public boolean equals(Object c) {
		if(!(this instanceof Contacto)) {
			return false;
		}
		Contacto c1 = (Contacto) c;
		return this.email.equalsIgnoreCase(c1.email) && this.apellidos.equalsIgnoreCase(c1.apellidos) && this.nombre.equalsIgnoreCase(c1.nombre);
	}
	
	/**
	 * Método para saber el orden entre objetos
	 * @param Introduces como parámetro un Contacto
	 * @return Devuelve un int indicando si es mayor, menor o igual.
	 */
	public int compareTo(Contacto c1) {
		if(this.apellidos.compareToIgnoreCase(c1.getApellidos()) < 0) {
			return -1;
		}
		if(this.apellidos.compareToIgnoreCase(c1.getApellidos()) > 0) {
			return 1;
		}
		if(this.apellidos.compareToIgnoreCase(c1.getApellidos()) == 0) {
				if(this.nombre.compareToIgnoreCase(c1.getNombre()) < 0) {
					return -1;
				}
				if(this.nombre.compareToIgnoreCase(c1.getNombre()) > 0) {
					return 1;
				}
		}
		return 0;
	}
	public abstract String crearFirma();

	/**
	 * Método que saca la representación textual de un Contacto
	 * @return Devuelve la representación textual del contacto
	 */
	@Override
	public String toString(){
		String mensaje = "";
		if(this instanceof Personal) {
			mensaje =  apellidos.toUpperCase() + ", " + nombre.toUpperCase() + " (PERSONAL)\n" + "Tfno: " + telefono + " | " + "email: " + email + "\n";
		}
		if(this instanceof Profesional) {
			mensaje =  apellidos.toUpperCase() + ", " + nombre.toUpperCase() + " (PROFESIONAL)\n" + "Tfno: " + telefono + " | " + "email: " + email + "\n";
		}
		return mensaje;
	}
	
}