package co.edu.univalle.vistaCasino;

import co.edu.univalle.sistema.Jugador;
import co.edu.univalle.sistema.Maquina;
import co.edu.univalle.sistema.Sistema;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Carlos Andres Hernandez Agudelo , Brian Sanchez
 */
public class VentanaModoDeJuego extends JFrame {

    //Declaracion de variables                    
    private Sistema juegoCasino = new Sistema();
    Random aleatorio = new Random();
    GridBagConstraints constraints = new GridBagConstraints();

    private JLabel lblTitulo;
    private JLabel lblMensaje;
    private JLabel lblImagenUs1;
    private JLabel lblImagenUs2;
    private JLabel lblMaquina;
    private JButton btnDosPersonas;
    private JButton btnUnaPersona;
    private Container contenedorPpal;

    private void setConstraints(int x, int y, int hx, int hy) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = hx;
        constraints.gridheight = hy;
    }

    /**
     * Creamos una ventana para casino
    * @param juegoCasino
     */
    public VentanaModoDeJuego(Sistema juegoCasino) {
        this.juegoCasino = juegoCasino;
        inicializarVentana();
    }

    private void inicializarVentana() {
        inicializarComponentes();
        setLocationRelativeTo(null);
        setTitle("Casino Univalle"); //Título del JFrame
        setSize(600, 500); //Dimensiones del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar al salir
        setVisible(true); //Mostrar JFrame
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
    }

    public void inicializarComponentes() {

        EventosInternos gestorEventos = new EventosInternos();

        contenedorPpal = getContentPane();
        contenedorPpal.setLayout(new GridBagLayout());

        lblTitulo = new JLabel();
        lblTitulo.setText("CASINO UNIVALLE");
        setConstraints(0, 1, 2, 3);
        contenedorPpal.add(lblTitulo, constraints);

        lblImagenUs1 = new JLabel();
        lblImagenUs1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png")));
        setConstraints(0, 1, 2, 3);
        contenedorPpal.add(lblImagenUs1, constraints);

        lblImagenUs2 = new JLabel();
        lblImagenUs2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user.png")));
        setConstraints(0, 1, 2, 3);
        contenedorPpal.add(lblImagenUs2, constraints);

        lblMaquina = new JLabel();
        lblMaquina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/machine.png")));
        setConstraints(0, 1, 2, 3);
        contenedorPpal.add(lblMaquina, constraints);

        btnDosPersonas = new JButton();
        btnDosPersonas.setText("Dos Jugadores");
        btnDosPersonas.setActionCommand("DOS_JUGADORES");
        setConstraints(0, 1, 2, 3);
        contenedorPpal.add(btnDosPersonas, constraints);

        btnUnaPersona = new JButton();
        btnUnaPersona.setText("Un Jugador");
        btnUnaPersona.setActionCommand("UN_JUGADOR");
        setConstraints(0, 1, 2, 3);
        contenedorPpal.add(btnUnaPersona    , constraints);
    }

    class EventosInternos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {

                default:
                    break;
            }
        }

    }
}
