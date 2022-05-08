package co.edu.univalle.vistaCasino;

import co.edu.univalle.sistema.Juego;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author Carlos Andres Hernandez Agudelo , Brian Sanchez
 */
public class VentanaCasino extends JFrame {

    //Declaracion de variables                    
    private Juego juegoCasino = new Juego();
    Random aleatorio = new Random();

    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lbldado1;
    private javax.swing.JLabel lbldado2;
    private javax.swing.JButton btnLanzar;
    private javax.swing.JTextField txtResultado;
    private Container contenedorPpal;


    /**
     * Creamos una ventana para casino
     */
    public VentanaCasino() {
        inicializarVentana();
    }

    private void inicializarVentana() {

        inicializarComponentes();

        setLocationRelativeTo(null);
        setTitle("Ser ludopata es mi pasion"); //Título del JFrame
        setSize(600, 500); //Dimensiones del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar al salir
        setVisible(true); //Mostrar JFrame
    }


    public void inicializarComponentes() {

        EventosInternos gestorEventos = new EventosInternos();

        lblTitulo = new javax.swing.JLabel();
        btnLanzar = new javax.swing.JButton();
        lbldado1 = new javax.swing.JLabel();
        lbldado2 = new javax.swing.JLabel();
        txtResultado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Perpetua Titling MT", 0, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CASINO UNVIALLE");

        btnLanzar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btnLanzar.setText("Tira los dados");
        btnLanzar.setActionCommand("BTNLANZAR");
        btnLanzar.addActionListener(gestorEventos);

        lbldado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado2.png"))); // NOI18N

        lbldado2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado4.png"))); // NOI18N

        txtResultado.setEditable(false);
        txtResultado.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtResultado.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        contenedorPpal = getContentPane();        
        contenedorPpal.setLayout(new GridLayout(4, 2));
        
        contenedorPpal.add(lblTitulo);
        contenedorPpal.add(lbldado1);
        contenedorPpal.add(lbldado2);
        contenedorPpal.add(txtResultado);
        contenedorPpal.add(btnLanzar);

    }

    class EventosInternos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "BTNLANZAR":
                    //Generar el tiro de los dados, usando numeros generados aleatoriamente
                    int dado1 = aleatorio.nextInt(7);
                    int dado2 = aleatorio.nextInt(7);

                    //Generamos los iconos a partir de imagenes
                    Icon uno = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado1.png"));
                    Icon dos = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado2.png"));
                    Icon tres = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado3.png"));
                    Icon cuatro = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado4.png"));
                    Icon cinco = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado5.png"));
                    Icon seis = new javax.swing.ImageIcon(getClass().getResource("/imagenes/dado6.png"));

                    //Mostramos los dados al usuario
                    switch (dado1) {
                        case 1 -> lbldado1.setIcon(uno);
                        case 2 -> lbldado1.setIcon(dos);
                        case 3 -> lbldado1.setIcon(tres);
                        case 4 -> lbldado1.setIcon(cuatro);
                        case 5 -> lbldado1.setIcon(cinco);
                        case 6 -> lbldado1.setIcon(seis);
                        default -> {
                    }
                    }
                    
                    switch (dado2) {
                        case 1 -> lbldado2.setIcon(uno);
                        case 2 -> lbldado2.setIcon(dos);
                        case 3 -> lbldado2.setIcon(tres);
                        case 4 -> lbldado2.setIcon(cuatro);
                        case 5 -> lbldado2.setIcon(cinco);
                        case 6 -> lbldado2.setIcon(seis);
                        default -> {
                    }
                    }
                    
                    //Mostramos por texto el resultado del lanzamiento
                    int resultado = dado1+dado2;
                    txtResultado.setText(resultado+"");

                    break;

                default:
                    JOptionPane.showMessageDialog(null, "a");
                    break;
            }
        }

    }
}
