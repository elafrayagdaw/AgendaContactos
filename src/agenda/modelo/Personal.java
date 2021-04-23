package agenda.modelo;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 
 * @author Unai Pérez, Iñaki Tiraplegui, Enrique Lafraya.
 *
 */

public class Personal extends Contacto implements Comparable<Contacto>{
LocalDate fecha;
private static String firma;
Relacion relacion;
	public Personal(String nombre, String apellidos, String telefono,String email,String fecha,Relacion rel) {
		super(nombre,apellidos,telefono,email);
		this.fecha = LocalDate.parse(fecha,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		relacion = rel;
	}
	/**
	 * Metodo accesor que devuelve el nombre de la empresa
	 * @return Devuelve un LocalDate con la fecha de nacimiento
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	/**
	 * Método mutador para cambiar la fecha de nacimiento
	 * @param Se introduce como parámetro la nueva fecha de nacimiento
	 */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	/**
	 * Metodo accesor que devuelve la relacion
	 * @return Devuelve una Realcion con la relacion del contacto Personal
	 */
	public Relacion getRelacion() {
		return relacion;
	}
	/**
	 * Método mutador para cambiar la relacion
	 * @param Se introduce como parámetro la nueva relacion
	 */
	public void setRelacion(Relacion relacion) {
		this.relacion = relacion;
	}
	/**
	 * Método que nos saca la firma al final de cada contacto
	 * @return Devuelve un string con la firma
	 */
	public String crearFirma() {
		firma = "Un abrazo!";
		return firma;
	}
	/**
	 * Método para saber si es cumpleaños de alguien comparando la fecha de nacimiento con la actual.
	 * @return Devuelve un booleano si es o no su cumpleaños
	 */
	public boolean esCumpleaños() {
		LocalDate hoy = LocalDate.now();
		boolean resultado = false;
		if(hoy.equals(fecha)) {
			resultado = true;
		}
		return resultado;
	}
	/**
	 * Método para sacar la fecha en el formato que se pide ("dd/MM/yyyy")
	 * @return Devuelve un String con la fecha formateada
	 */
	private String formatearFecha() {
		String formateada = "";
		String mes = "";
		switch(fecha.getMonthValue()) {
		case 1: mes = "ener.";
			break;
		case 2: mes = "febr.";
			break;
		case 3: mes = "mar.";
			break;
		case 4: mes = "abr.";
			break;
		case 5: mes = "may.";
			break;
		case 6: mes = "jun";
			break;
		case 7: mes = "jul.";
			break;
		case 8: mes = "agost.";
			break;
		case 9: mes = "sept.";
			break;
		case 10: mes = "oct.";
			break;
		case 11: mes = "nov.";
			break;
		case 12: mes = "dic.";
			break;
		}
		String dia = "" + fecha.getDayOfMonth();
		String año = "" + fecha.getYear();
		formateada = dia + " " + mes + " " + año;
		return formateada;
	}
	/**
	 * Representación textual de la clase Personal
	 */
	public String toString() {
		String resul = "";
		resul = super.toString() + "Fecha nacimiento: " +  formatearFecha() + "\n" +"Relacion: " + relacion + "\n\n" + crearFirma()+"\n";
		return resul;
	}
	
	public int compareTo(Personal p1, Personal p2) {
		if(p1.getFecha().isBefore(p2.getFecha())) {
			return -1;
		}
		if(p1.getFecha().isAfter(p2.getFecha())) {
			return 1;
		}
		return 0;
	}
}
