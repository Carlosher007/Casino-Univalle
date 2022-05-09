package co.edu.univalle.sistema;

import java.util.ArrayList;

/**
 *
 * @author
 *
 * Carlos Andres Hernandez Agudelo
 *
 * Brian Sanchez
 */
public class Sistema {

    private final String posiblesModoDeJuego[] = {"Jugador VS Jugador", "Jugador VS Maquina"};
    private String modoDeJuego;
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private int numeroLanzamientosRonda;
    private int numeroRondas;
    private int tiempoDeJuego;
    private int lanzamientosEmpatados;
    private int numeroLanzamientoActual;

    public Sistema() {
        this.modoDeJuego = "";
        this.numeroLanzamientosRonda = 0;
        this.numeroRondas = 1;
        this.lanzamientosEmpatados = 0;
        this.numeroLanzamientosRonda = 0;
    }

    public int getLanzamientosEmpatados() {
        return lanzamientosEmpatados;
    }

    public void setLanzamientosEmpatados(int lanzamientosEmpatados) {
        this.lanzamientosEmpatados = lanzamientosEmpatados;
    }

    public int getNumeroLanzamientoActual() {
        return numeroLanzamientoActual;
    }

    public void setNumeroLanzamientoActual(int numeroLanzamientoActual) {
        this.numeroLanzamientoActual = numeroLanzamientoActual;
    }

    public void agregarJugador(String nombre) {
        Jugador jugador = new Jugador(nombre);
        jugadores.add(jugador);
    }

    public void agregarMaquina() {
        Maquina maquina = new Maquina();
        maquina.generarNombreAleatorio();
        jugadores.add(maquina);
    }

    public String[] obtenerPosibleModoDeJuego() {
        return posiblesModoDeJuego;
    }

    public String obtenerPosibleModoDeJuego(int indice) {
        return posiblesModoDeJuego[indice];
    }

    public String getModoDeJuego() {
        return modoDeJuego;
    }

    public void setModoDeJuego(String modoDeJuego) {
        this.modoDeJuego = modoDeJuego;
    }

    public int getTiempoDeJuego() {
        return tiempoDeJuego;
    }

    public void setTiempoDeJuego(int tiempoDeJuego) {
        this.tiempoDeJuego = tiempoDeJuego;
    }

    public int getNumeroLanzamientosRonda() {
        return numeroLanzamientosRonda;
    }

    public void setNumeroLanzamientosRonda(int numeroLanzamientosRonda) {
        this.numeroLanzamientosRonda = numeroLanzamientosRonda;
    }

    public int getNumeroRondas() {
        return numeroRondas;
    }

    public void setNumeroRondas(int numeroRondas) {
        this.numeroRondas = numeroRondas;
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public Jugador obtenerJugador(int indice) {
        return jugadores.get(indice);
    }

    public void eliminarJugadorPorIndice(int indice) {
        jugadores.remove(indice);
    }

    public void eliminarTodosLosJugadores() {
        jugadores.clear();
    }

}
