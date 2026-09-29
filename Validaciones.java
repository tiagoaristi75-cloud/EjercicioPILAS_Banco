import java.util.Scanner;

public class Validaciones {

public static String leerTextoNoVacio(Scanner lector, String mensaje) {
        String texto = "";
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            texto = lector.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Este dato no puede quedar vacio, intente de nuevo.");
            } else {
                valido = true;
            }
        }
        return texto;
    }
    public static String leerIdentificacionDisponible(Banco banco, Scanner lector) {
        String identificacion = "";
        boolean disponible = false;
        while (!disponible) {
            identificacion = leerTextoNoVacio(lector, "Identificacion: ");
            if (banco.existeIdentificacion(identificacion)) {
                System.out.println("Ya existe un cliente registrado con esa identificacion, use otra.");
            } else {
                disponible = true;
            }
        }
        return identificacion;
    }
    public static int leerEdadValida(Scanner lector) {
        int edad = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print("Edad: ");
            String texto = lector.nextLine().trim();
            try {
                edad = Integer.parseInt(texto);
                if (edad < 0 || edad > 120) {
                    System.out.println("La edad debe estar entre 0 y 120, intente de nuevo.");
                } else {
                    valido = true;
                }
            } catch (NumberFormatException error) {
                System.out.println("Eso no es un numero valido, escriba solo digitos.");
            }
        }
        return edad;
    }
    public static boolean leerRespuestaSiNo(Scanner lector, String mensaje) {
        boolean respuesta = false;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String texto = lector.nextLine().trim().toLowerCase();
            if (texto.equals("si")) {
                respuesta = true;
                valido = true;
            } else if (texto.equals("no")) {
                respuesta = false;
                valido = true;
            } else {
                System.out.println("Responda unicamente 'si' o 'no'.");
            }
        }
        return respuesta;
    }
}
