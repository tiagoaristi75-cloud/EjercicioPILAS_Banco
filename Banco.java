import java.util.LinkedList;
import java.util.Queue;


public class Banco {

    private Queue<Cliente> ColaPreferencial;
    private Queue<Cliente> ColaNormal;
    private Queue<Cliente> HistorialAtendidos;
    private Queue<Cliente> HistorialCancelados;
    private Cliente ClienteAtencion;
    private int ContadorTurnos;


    public Banco() {

    this.ColaPreferencial = new LinkedList<>();
    this.ColaNormal = new LinkedList<>();
    this.HistorialAtendidos = new LinkedList<>();
    this.HistorialCancelados = new LinkedList<>();
    this.ClienteAtencion = null;
    this.ContadorTurnos = 0;
    }


    public String RegistrarCliente(String identificacion, String nombre, String tipoTramite, int edad, boolean atencionEspecial) {
        ContadorTurnos = ContadorTurnos + 1;
        Cliente nuevoCliente = new Cliente(identificacion, nombre, tipoTramite, edad, atencionEspecial, ContadorTurnos);
        
        if (atencionEspecial) {
            ColaPreferencial.offer(nuevoCliente);
        } else {
            ColaNormal.offer(nuevoCliente);
        }
        return "Cliente registrado con exito, su turno es: " + ContadorTurnos;
    }

    public String ConsultarClientesEnEspera() {
        if(ColaPreferencial.isEmpty() && ColaNormal.isEmpty()) {
            return "No hay clientes en espera.";
        }
        StringBuilder texto = new StringBuilder();
        texto.append("----- Clientes con atencion preferencial -----\n");
        for (Cliente cliente : ColaPreferencial) {
            texto.append(cliente.mostrarInformacion()).append("\n");
        }
        texto.append("----- Clientes con atencion normal -----\n");
        for (Cliente cliente : ColaNormal) {
            texto.append(cliente.mostrarInformacion()).append("\n");
        }
        return texto.toString();
    }

    public String LlamarSiguienteCliente() {
        if(ClienteAtencion != null) {
            return "Todavia hay un cliente en atencion (Turno " + ClienteAtencion.getNumeroTurno() + "). Debe marcarlo como atendido o cancelado antes de llamar al siguiente cliente.";
        }
        if (!ColaPreferencial.isEmpty()) {
            ClienteAtencion = ColaPreferencial.poll();
        } else if (!ColaNormal.isEmpty()) {
            ClienteAtencion = ColaNormal.poll();
        } else {
            return "No hay clientes en espera.";
        }
        return "Llamar al cliente: " + ClienteAtencion.mostrarInformacion();
    }

    public String MarcarClienteAtendido() {
        if (ClienteAtencion == null) {
            return "No hay clientes en atencion.";
        }
        String mensaje = ClienteAtencion.MarcarAtendido();
        HistorialAtendidos.offer(ClienteAtencion);
        ClienteAtencion = null;
        return mensaje;
    }

    public String CambiarAPreferencial(String identificacion) {
        Queue<Cliente> colaTemporal = new LinkedList<>();
        Cliente clienteEncontrado = null;

        while (!ColaNormal.isEmpty()) {
            Cliente actual = ColaNormal.poll();
            if(clienteEncontrado == null && actual.getIdentificacion().equals(identificacion)) {
                clienteEncontrado = actual;
            } else {
                colaTemporal.offer(actual);
            }

        }
        ColaNormal = colaTemporal;

        if (clienteEncontrado == null) {
            return "No se encontro a ningun cliente en espera normal con esa identificacion." ;
        }

        String mensaje = clienteEncontrado.CambiarAPreferencial();
        ColaPreferencial.offer(clienteEncontrado);
        return mensaje;
    }

    public String cancelarTurno(String identificacion) {
        Queue<Cliente> colaTermporal = new LinkedList<>();
        Cliente ClienteEncontrado = null;

        while (!ColaPreferencial.isEmpty()) {
            Cliente Actual = ColaPreferencial.poll();
            if (ClienteEncontrado == null && Actual.getIdentificacion().equals(identificacion)) {
                ClienteEncontrado = Actual;
            } else {
                colaTermporal.offer(Actual);
            }
        }
        ColaPreferencial = colaTermporal;
        if (ClienteEncontrado == null) {
            colaTermporal = new LinkedList<>();
            while (!ColaNormal.isEmpty()) {
                Cliente Actual = ColaNormal.poll();
                if (ClienteEncontrado == null && Actual.getIdentificacion().equals(identificacion)) {
                    ClienteEncontrado = Actual;
                } else {
                    colaTermporal.offer(Actual);
                }
            }
            ColaNormal = colaTermporal;
        }
        if (ClienteEncontrado != null) {
            HistorialCancelados.offer(ClienteEncontrado);
            return "El turno " + ClienteEncontrado.getNumeroTurno() + " de " + ClienteEncontrado.getNombre() + " fue cancelado.";
        }
        for (Cliente atendido : HistorialAtendidos) {
            if (atendido.getIdentificacion().equals(identificacion)) {
                return "No se puede cancelar: El cliente ya fue atendido.";
            }
        }
        return "No se encontro ningun turno activo con esa identificacion.";
    }


    public String buscarClientePorIdentificacion(String identificacion) {
        for (Cliente cliente : ColaPreferencial) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                return "Cliente en espera (preferencial): " + cliente.mostrarInformacion();
            }
        }
        for (Cliente cliente : ColaNormal) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                return "Cliente en espera (normal): " + cliente.mostrarInformacion();
            }
        }
        if (ClienteAtencion != null && ClienteAtencion.getIdentificacion().equals(identificacion)) {
            return "Cliente siendo atendido ahora: " + ClienteAtencion.mostrarInformacion();
        }
        for (Cliente cliente : HistorialAtendidos) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                return "Cliente ya atendido: " + cliente.mostrarInformacion();
            }
        }
        for (Cliente cliente : HistorialCancelados) {
            if (cliente.getIdentificacion().equals(identificacion)) {
                return "Turno cancelado: " + cliente.mostrarInformacion();
            }
        }
        return "No se encontro ningun cliente con esa identificacion.";
    }

    public boolean existeIdentificacion(String identificacion) {
        String resultado = buscarClientePorIdentificacion(identificacion);
        return !resultado.equals("No se encontro ningun cliente con esa identificacion.");
    }

    public String ConsultarCantidadEnEspera() {
        int total = ColaPreferencial.size() + ColaNormal.size();
        return "Personas esperando en total: " + total;
    }


    public String ConsultarConteoPorTipo() {
        return "Preferenciales en espera: " + ColaPreferencial.size()
                + " | Normales en espera: " + ColaNormal.size();
    }
}