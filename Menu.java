import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        Banco banco = new Banco();
        String resultadoFinal = ejecutarMenu(banco, lector);
        System.out.println(resultadoFinal);
    }
    public static String ejecutarMenu(Banco banco, Scanner lector) {
        boolean continuar = true;
        while (continuar) {
            System.out.println(mostrarMenu());
            String opcion = lector.nextLine();
            String resultadoOpcion = procesarOpcion(opcion, banco, lector);
            System.out.println(resultadoOpcion);
            if (opcion.equals("0")) {
                continuar = false;
            }
        }
        return "Programa finalizado.";
    }
    public static String mostrarMenu() {
        return "\n--- Menu del banco ---\n"
                + "1. Registrar cliente\n"
                + "2. Consultar clientes en espera\n"
                + "3. Llamar siguiente cliente\n"
                + "4. Marcar cliente atendido\n"
                + "5. Cambiar cliente a preferencial\n"
                + "6. Cancelar turno\n"
                + "7. Buscar cliente por identificacion\n"
                + "8. Consultar cantidad en espera\n"
                + "9. Consultar cuantos hay por tipo\n"
                + "0. Salir\n"
                + "Elija una opcion:";
    }
    public static String procesarOpcion(String opcion, Banco banco, Scanner lector) {
        switch (opcion) {
            case "1":
                return registrarClienteDesdeTeclado(banco, lector);
            case "2":
                return banco.ConsultarClientesEnEspera();
            case "3":
                return banco.LlamarSiguienteCliente();
            case "4":
                return banco.MarcarClienteAtendido();
            case "5":
                System.out.print("Identificacion del cliente a cambiar a preferencial: ");
                String idCambiar = lector.nextLine();
                return banco.CambiarAPreferencial(idCambiar);
            case "6":
                System.out.print("Identificacion del turno a cancelar: ");
                String idCancelar = lector.nextLine();
                return banco.cancelarTurno(idCancelar);
            case "7":
                System.out.print("Identificacion a buscar: ");
                String idBuscar = lector.nextLine();
                return banco.buscarClientePorIdentificacion(idBuscar);
            case "8":
                return banco.ConsultarCantidadEnEspera();
            case "9":
                return banco.ConsultarConteoPorTipo();
            case "0":
                return "Saliendo del programa...";
            default:
                return "Opcion no valida, intente de nuevo.";
        }
    }
    public static String registrarClienteDesdeTeclado(Banco banco, Scanner lector) {
        System.out.print("Identificacion: ");
        String identificacion = lector.nextLine();
        System.out.print("Nombre: ");
        String nombre = lector.nextLine();
        System.out.print("Tipo de tramite: ");
        String tipoTramite = lector.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(lector.nextLine());
        System.out.print("Tiene atencion preferencial? (si/no): ");
        String respuesta = lector.nextLine();
        boolean atencionEspecial = respuesta.equalsIgnoreCase("si");
        return banco.RegistrarCliente(identificacion, nombre, tipoTramite, edad, atencionEspecial);
    }
}