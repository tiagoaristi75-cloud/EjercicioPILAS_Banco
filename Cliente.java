public class Cliente {
    private String Identificacion;
    private String Nombre;
    private String TipoTramite;
    private int Edad;
    private boolean AtencionEspecial;
    private int NumeroTurno;
    private boolean Atendido;

    public Cliente(String identificacion, String nombre, String tipoTramite, int edad, boolean atencionEspecial, int NumeroTurno) {
        this.Identificacion = identificacion;
        this.Nombre = nombre;
        this.TipoTramite = tipoTramite;
        this.Edad = edad;
        this.AtencionEspecial = atencionEspecial;
        this.NumeroTurno = NumeroTurno;
        this.Atendido = false;
    }

    public String getIdentificacion() {
        return Identificacion;
    }
    public String getNombre() {
        return Nombre;
    }
    public String getTipoTramite() {
        return TipoTramite;
    }
    public int getEdad() {
        return Edad;
    }
    public boolean isAtencionEspecial() {
        return AtencionEspecial;
    }
    public int getNumeroTurno() {
        return NumeroTurno;
    }
    public boolean isAtendido() {
        return Atendido;
    }

    public String CambiarAPreferencial() {
        this.AtencionEspecial = true;
        return "El cliente " + this.Nombre + " ha sido cambiado a atención preferencial.";
    }

    public String MarcarAtendido() {
        this.Atendido = true;
        return "El cliente " + this.Nombre + " ha sido marcado como atendido.";
    }
    public String mostrarInformacion() {
        String tipo = AtencionEspecial ? "Preferencial" : "Normal";
        String estado = Atendido ? "Atendido." : "En espera.";
        return "Turno" + NumeroTurno
                + " | ID: " + Identificacion
                + " | Nombre: " + Nombre
                + " | Tipo de Trámite: " + TipoTramite
                + " | Edad: " + Edad
                + " | Atención: " + tipo
                + " | Estado: " + estado;
    }
}