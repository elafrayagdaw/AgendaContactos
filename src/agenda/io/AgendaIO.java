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
				suma += contador;
				linea = entrada.readLine();
			}
		} catch (IOException e) {
			suma++;
			System.out.println("Error al leer " + ruta);
		} catch (NullPointerException e) {
			System.out.println("Documento vacío");
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (NullPointerException e) {
					suma++;
					System.out.println(e.getMessage());
				} catch (IOException e) {
					suma++;
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
		int suma = 0;
		int contador = 0;
		try {
			String[] separacion = linea.split(",");
			for (int i = 0; i < separacion.length; i++) {
				separacion[i] = separacion[i].trim();
			}

			String nombre = separacion[1];
			String apellidos = separacion[2];
			String telefono = separacion[3];
			String email = separacion[4];
			String tempo1 = separacion[5];
			int numero = Integer.parseInt(separacion[0]);
			Relacion relac = Relacion.HIJO;

			if (numero == 1) {
				Profesional tempo = new Profesional(nombre, apellidos, telefono, email, tempo1);
				Contacto c = (Contacto) tempo;
				return c;
			}
			if (numero == 2) {
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
				Contacto tempo = new Personal(nombre, apellidos, telefono, email, tempo1, relac);
				Contacto c = (Contacto) tempo;
				return c;
			}
		} catch (NullPointerException e) {
			contador++;
			suma += contador;
			System.out.println("Campo vacío");
		} catch (Exception e) {
			contador++;
			suma += contador;
			System.out.println("Error en el formato");
		}
		return null;
	}

}
