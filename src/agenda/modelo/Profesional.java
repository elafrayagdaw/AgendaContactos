package agenda.modelo;
/**
 * 
 * @author Unai Pérez, Iñaki Tiraplegui, Enrique Lafraya.
 *
 */
public class Profesional extends Contacto implements Comparable<Contacto>{

	private String empresa;
	public Profesional(String nombre, String apellidos, String telefono, String email,String empresa) {
		super(nombre, apellidos, telefono, email);
		String capitalizado = "";
		String[] partes = empresa.split(" ");
		for(int i = 0; i < partes.length; i++) {
			if(partes[i].compareTo(" ") > 0) {
				capitalizado += partes[i].substring(0,1).toUpperCase() + partes[i].substring(1, partes[i].length()) + " ";
			}
		}
	
		this.empresa = capitalizado;
	}
	
	/**
	 * Metodo accesor que devuelve el nombre de la empresa
	 * @return Devuelve un String con el nombre de la empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Método mutador para cambiar el nombre de la empresa
	 * @param Se introduce como parámetro el nuevo nombre de la empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Metodo que crea un numero aleatorio entre el 1 y el 4 y dependiendo cual toque, elegirá una u otra opción.
	 */
	@Override
	public String crearFirma() {
		String firma;
		int firmaAl = (int)(Math.random()*4+1);
		switch(firmaAl) {
		case 1:
			firma = "Atentamente";
			break;
		case 2:
			firma = "Saludos";
			break;
		case 3:
			firma = "Saludos cordiales";
			break;
		case 4:
			firma = "Mis mejores deseos";
			break;
		default:
			firma = "";
		}
		return firma;
	}

	/**
	 * Representación textual de la clase Profesional 
	 */
	@Override
	public String toString() {
		return super.toString() + "Empresa: " + empresa + "\n" + crearFirma() + "\n";
	}
	
	public int compareTo(Profesional p1, Profesional p2) {
		if(p1.getApellidos().compareTo(p2.getApellidos()) < 1) {
			return -1;
		}
		if(p1.getApellidos().compareTo(p2.getApellidos()) > 1) {
			return 1;
		}
		return 0;
	}

}