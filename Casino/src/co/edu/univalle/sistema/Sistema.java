package co.edu.univalle.sistema;

import java.util.ArrayList;

/**
    Laboratorio N.1: primer miniproyecto.
    Archivo: Sistema.java
    Autores (Grupo 01 POE):
      -Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
      -Carlos Andrés Hernandez Agudelo <carlos.hernandez.agudelo@correounivalle.edu.co>
    Fecha creación: 07-05-2022
    Fecha última modificación: 10-02-2022
    Docente:
      -Luis Romo <luis.romo@correounivalle.edu.co>
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
    private boolean enJuego;
    private int numeroLanzamientosCopia;

    public int getNumeroLanzamientosCopia() {
        return numeroLanzamientosCopia;
    }

    public void setNumeroLanzamientosCopia(int numeroLanzamientosCopia) {
        this.numeroLanzamientosCopia = numeroLanzamientosCopia;
    }

    public Sistema() {
        this.modoDeJuego = "";
        this.numeroLanzamientosRonda = 0;
        this.numeroRondas = 1;
        this.lanzamientosEmpatados = 0;
        this.numeroLanzamientosRonda = 0;
        this.enJuego = false;
        this.tiempoDeJuego = 0;
        this.numeroLanzamientosCopia = 0;
    }

    public boolean isEnJuego() {
        return enJuego;
    }

    public void setEnJuego(boolean enJuego) {
        this.enJuego = enJuego;
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

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
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
