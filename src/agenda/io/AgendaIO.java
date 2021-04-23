package agenda.io;
import agenda.modelo.AgendaContactos;
import agenda.modelo.Contacto;
import agenda.modelo.Personal;
import agenda.modelo.Profesional;
import agenda.modelo.Relacion;

/**
 * Utilidades para cargar la agenda  
 */
public class AgendaIO {

    public static void importar(AgendaContactos agenda) {
        String[] tempo = obtenerLineasDatos();
        for(int i = 0; i < tempo.length; i++) {
            agenda.añadirContacto(parsearLinea(tempo[i]));
        }
    }

    private static Contacto parsearLinea(String linea) {

        String[] separacion = linea.split(",");
        for(int i = 0; i < separacion.length; i++) {
            separacion[i] = separacion[i].trim();
        }

        String nombre = separacion[1];
        String apellidos = separacion[2];
        String telefono = separacion[3];
        String email = separacion[4];
        String tempo1 = separacion[5];
        int numero = Integer.parseInt(separacion[0]);
        Relacion relac = Relacion.HIJO;


        if(numero == 1){
            Profesional tempo = new Profesional(nombre, apellidos, telefono, email, tempo1); 
            Contacto c = (Contacto)tempo;
            return c;
        }
        if(numero == 2) {
            separacion[6].toUpperCase();
            switch(separacion[6].toUpperCase()) {
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
            Contacto c = (Contacto)tempo;
            return c;
        }
        return null;
    }

	/**
	 * 
	 * @return un array de String con todas las líneas de información de todos
	 *         los contactos. 1 significa contacto profesional, 2 significa
	 *         contacto personal
	 */
	private static String[] obtenerLineasDatos() {
		return new String[] {
				"1, Isabel, Acosta Mendioroz, 678895433 , iacostamen@gmail.com , walden estrella ",
				"2, pedro , urruti tello , 616789654 , urrutitello@gmail.com , 09/03/2007, amigos",
				"1, Angel , Esteban Grande , 674544123 , aestebang@gmail.com , magma publicidad ",
				"2, elena , bueno ganuza , 6786547699 , ebuenogan@gmail.com , 17/03/2000, amigos",
				"2, amaia , romero sein , 642222343 , aromerosein@gmail.com , 09/03/2012, pareja",
				"2, Ignacio , Anto roth ,  688912799 , iantoroth@gmail.com , 11/11/1969 , padre",
				"1, Isabel , Acosta Marin , 678895433 , iacostamar@gmail.com , publicidad holdings ",
				"1 , roberto , casas maura , 666777888 , rocasasma@gmail.com , strato banca ",
				"1,juan maria, garcía oliva, 699898111, jmgarcioliva@gmail.com, conway motor ",
				"2, pedro , urruti tello , 616789654 , urrutitello@gmail.com , 17/03/2000, amigos",
				"1,marta, sanz iris, 622999876, msanzi@gmail.com, jump literatura ",
				"1,javier, porto luque, 691256777 , japorlu@gmail.com, gas natural ",
				"1,pablo, ponce larraoz, 689123456, pabloponce@gmail.com, la caixa",
				"1, javier, marin lancho, 666666666, jruizlanchoe@gmail.com, bbva",
				"1,juan maria, garcía oliva, 699898111, jmgarcioliva@gmail.com, conway motor ",
				"2, Berta , andia solano , 621123345 , bandiasol@gmail.com , 12/12/1999 , HIJA",
				"2, Ignacio , Anto roth , 688912799 , iantoroth@gmail.com , 11/11/1969 , padre",
				" 1, roberto , casas maura , 666777888 , rocasasma@gmail.com , strato banca ",
				" 2, daniel , martin martin , 678901234 , damrtinmartin@gmail.com , 15/07/1980, amigos",
				" 2, pablo , martin abradelo , 667788899 , martinabra@gmail.com , 31/01/2010, amigos",
				" 2, susana , santaolalla bilbao , 676767676 , ssantaolalla@gmail.com , 17/03/1998, amigos",
				" 2, adur , martin merino , 611112113 , adurmartinme@gmail.com , 14/02/2003 , amigos" };

	}

}

