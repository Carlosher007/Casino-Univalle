package co.edu.univalle.sistema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
    Laboratorio N.1: primer miniproyecto.
    Archivo: Maquina.java
    Autores (Grupo 01 POE):
      -Brayan Andrés Sánchez Lozano <brayan.andres.sanchez@correounivalle.edu.co>
      -Carlos Andrés Hernandez Agudelo <carlos.hernandez.agudelo@correounivalle.edu.co>
    Fecha creación: 07-05-2022
    Fecha última modificación: 10-02-2022
    Docente:
      -Luis Romo <luis.romo@correounivalle.edu.co>
*/

public class Maquina extends Jugador {

    ArrayList<String> posiblesNombres = new ArrayList<>(Arrays.asList("Aczino", "Chuty","Teorema","Gazir", "MetaLinguistica","VallesT", "Skone",
            "Jaze", "MuMaKSO"));
    Random aleatorio = new Random();

    public Maquina() {
        this.nombre = "";
        this.debeLanzar = false;
        this.esPersona = false;
        this.tiempoDeJuego = 0;
        this.sumaResultado = 0;
        this.lanzamientosGanados = 0;
        this.rondasGanadas = 0;
        this.puntajeLanzamientoActual = 0;
    }
    
    @Override
    public void generarNombreAleatorio() {
        int indice = (aleatorio.nextInt(posiblesNombres.size() - 1));
        this.nombre = posiblesNombres.get(indice);
    }
}
