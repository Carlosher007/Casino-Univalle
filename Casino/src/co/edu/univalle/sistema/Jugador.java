package co.edu.univalle.sistema;

/**
 *
 * @author
 *
 * Carlos Andres Hernandez Agudelo
 *
 * Brian Sanchez
 */
public class Jugador {

    protected String nombre;
    protected boolean debeLanzar;
    protected boolean esPersona;
    protected int tiempoDeJuego;
    protected int sumaResultado;
    protected int lanzamientosHechos;
    protected int lanzamientosGanados;
    protected int rondasGanadas;
    protected int puntajeLanzamientoActual;

    public Jugador(String nombre) {
        super();
        this.nombre = nombre;
        this.debeLanzar = false;
        this.esPersona = true;
        this.tiempoDeJuego = 0;
        this.sumaResultado = 0;
        this.lanzamientosHechos = 0;
        this.lanzamientosGanados = 0;
        this.rondasGanadas = 0;
        this.puntajeLanzamientoActual = 0;
    }

    public Jugador() {
        super();
        this.nombre = "";
        this.debeLanzar = false;
        this.esPersona = true;
        this.tiempoDeJuego = 0;
        this.lanzamientosHechos = 0;
        this.sumaResultado = 0;
        this.lanzamientosGanados = 0;
        this.rondasGanadas = 0;
        this.puntajeLanzamientoActual = 0;
    }

    public int getLanzamientosHechos() {
        return lanzamientosHechos;
    }

    public void setLanzamientosHechos(int lanzamientosHechos) {
        this.lanzamientosHechos = lanzamientosHechos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDebeLanzar() {
        return debeLanzar;
    }

    public void setDebeLanzar(boolean debeLanzar) {
        this.debeLanzar = debeLanzar;
    }

    public boolean isEsPersona() {
        return esPersona;
    }

    public void setEsPersona(boolean esPersona) {
        this.esPersona = esPersona;
    }

    public int getTiempoDeJuego() {
        return tiempoDeJuego;
    }

    public void setTiempoDeJuego(int tiempoDeJuego) {
        this.tiempoDeJuego = tiempoDeJuego;
    }

    public int getSumaResultado() {
        return sumaResultado;
    }

    public void setSumaResultado(int sumaResultado) {
        this.sumaResultado = sumaResultado;
    }

    public int getLanzamientosGanados() {
        return lanzamientosGanados;
    }

    public void setLanzamientosGanados(int lanzamientosGanados) {
        this.lanzamientosGanados = lanzamientosGanados;
    }

    public int getRondasGanadas() {
        return rondasGanadas;
    }

    public void setRondasGanadas(int rondasGanadas) {
        this.rondasGanadas = rondasGanadas;
    }

    public int getPuntajeLanzamientoActual() {
        return puntajeLanzamientoActual;
    }

    public void setPuntajeLanzamientoActual(int puntajeLanzamientoActual) {
        this.puntajeLanzamientoActual = puntajeLanzamientoActual;
    }

    public void generarNombreAleatorio() {
    }

}
