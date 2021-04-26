package agenda.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import agenda.modelo.AgendaContactos;
import agenda.modelo.Contacto;
import agenda.modelo.Personal;
import agenda.modelo.Profesional;
import agenda.modelo.Relacion;

/**
 * Utilidades para cargar la agenda
 */
public class AgendaIO {
	/**
	 * Método que al meter como @param una agenda, importa los datos predefinidos de
	 * esta.
	 * 
	 */
	public static int importar(AgendaContactos agenda, String ruta) {
		int contador = 0;
		int suma = 0;
		BufferedReader entrada = null;
		try {
			entrada = new BufferedReader(new FileReader(ruta));
			String linea = entrada.readLine();
			while (linea != null) {
				agenda.añadirContacto(parsearLinea(linea));
				linea = entrada.readLine();
			}
		} catch (IOException e) {
			suma += 1;
			System.out.println("Error al leer " + ruta);
		} catch (NullPointerException e) {
			System.out.println("Campo es " + e.getMessage());
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (NullPointerException e) {
					suma += 1;
					System.out.println(e.getMessage());
				} catch (IOException e) {
					suma += 1;
					System.out.println(e.getMessage());
				}
			}
		}
		System.out.println(suma);
		return suma;
	}

	/**
	 * Método que al meter como @param una linea con este
	 * estilo(tipo,nombre,apellidos,telefono,email, empresa o fecha de nacimiento
	 * dependiendo del tipo de Contacto,relacion si la hay) te la convierte en un
	 * Contacto.
	 * 
	 */
	private static Contacto parsearLinea(String linea) {
		Contacto con = null;
		Profesional prof;
		Personal per;

		String[] separacion = linea.split(",");
		for (int i = 0; i < separacion.length; i++) {
			separacion[i] = separacion[i].trim();
		}
		String nombre;
		String apellidos;
		String telefono;
		String email;
		int numClase = Integer.parseInt(separacion[0]);
		Relacion relac = Relacion.HIJO;
		String atributoNoComun1;

		try {
			if (numClase == 1) {
				nombre = separacion[1];
				apellidos = separacion[2];
				telefono = separacion[3];
				email = separacion[4];
				atributoNoComun1 = separacion[5];
				prof = new Profesional(nombre, apellidos, telefono, email, atributoNoComun1);
				con = (Contacto) prof;
			}
		} catch (NullPointerException e) {
			System.out.println("Hay campos nulos en el contacto");
		} catch (NumberFormatException e) {
			System.out.println("Error en el formato numérico de un campo del contacto");
		} catch (Exception e) {
			System.out.println("Error con un campo del contacto");
		} 

		try {
			if (numClase == 2) {
				nombre = separacion[1];
				apellidos = separacion[2];
				telefono = separacion[3];
				email = separacion[4];
				atributoNoComun1 = separacion[5];

				separacion[6].toUpperCase();
				switch (separacion[6].toUpperCase()) {
				case "PADRE":
					relac = relac.PADRE;
					break;
				case "MADRE":
					relac = relac.MADRE;
					break;
				case "AMIGOS":
					relac = relac.AMIGOS;
					break;
				case "HIJO":
					relac = relac.HIJO;
					break;
				case "HIJA":
					relac = relac.HIJA;
					break;
				case "PAREJA":
					relac = relac.PAREJA;
					break;
				}

				per = new Personal(nombre, apellidos, telefono, email, atributoNoComun1, relac);
				con = (Contacto) per;
			}
		} catch (NullPointerException e) {
			System.out.println("Hay campos nulos en el contacto");
		} catch (NumberFormatException e) {
			System.out.println("Error en el formato numérico de un campo del contacto");
		} catch (Exception e) {
			System.out.println("Error con un campo del contacto");
		} 
		return con;
	}
}
