/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cifrarficherografico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Aaron
 */
public class CifrarFicheroGrafico extends JFrame {

    public JButton cifrar, descifrar;
    public JTextField num, texto;
    public JLabel contenido;
    public JComboBox combo;
    //public JTextField num, texto;
    public int aux = 0;
    public int numAux = 0;

    //pruebas
    JFileChooser seleccionar;
    JTextArea textArea;

    static String cifrado(String cadena, int cifrado) {

        //25 caracteres
        char[] letras = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String prueba = "";
        String cadenaNueva = cadena.replace(" ", "+");
        char espacio = '+';
        char punto = '.';
        char coma = ',';

        for (int z = 0; z < cadenaNueva.length(); z++) {

            char variable = cadenaNueva.charAt(z);

            if (variable == espacio) {
                prueba += " ";
            } else if (variable == punto) {
                prueba += ".";
            } else if (variable == coma) {
                prueba += ",";
            }

            for (int y = 0; y < letras.length; y++) {

                /*if (variable == espacio) {
                    prueba += "+";
                } else */
                if (variable == letras[y]) {

                    int nuevoValor = y + cifrado;

                    if (nuevoValor > 25) {
                        nuevoValor = (-26 + y) + cifrado;
                        if (nuevoValor > 25) {
                            int cambio = nuevoValor - 25;
                            prueba += letras[cambio];
                        } else {
                            prueba += letras[nuevoValor];
                        }
                        //prueba += letras[nuevoValor];
                    } else {
                        prueba += letras[nuevoValor];
                    }
                }
            }
        }

        return prueba;
    }

    static String descifrado(String cadena, int cifrado) {

        char[] letras = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String prueba = "";
        String cadenaNueva = cadena.replace(" ", "+");
        //System.out.println(cadenaNueva);
        char espacio = '+';
        char punto = '.';
        char coma = ',';
        //char c = cadena.charAt(0);

        for (int z = 0; z < cadenaNueva.length(); z++) {

            char variable = cadenaNueva.charAt(z);

            if (variable == espacio) {
                prueba += " ";
            } else if (variable == punto) {
                prueba += ".";
            } else if (variable == coma) {
                prueba += ",";
            }

            for (int y = 0; y < letras.length; y++) {

                if (variable == letras[y]) {

                    int nuevoValor = y - cifrado;
//System.out.println(nuevoValor);
                    if (nuevoValor < 0) {
//             System.out.println(nuevoValor);           
                        nuevoValor = (26 + y) - cifrado;

                        if (nuevoValor < 0) {
                            int cambio = nuevoValor + 25;
                            prueba += letras[cambio];
                        } else {
                            prueba += letras[nuevoValor];
                        }
                        //prueba += letras[nuevoValor];
                    } else {
                        prueba += letras[nuevoValor];
                    }
                }
            }
        }

        return prueba;
    }

    public CifrarFicheroGrafico() {
        crearVentana();
        botones();
        texto();
        logica();
        this.setVisible(true);
    }

    //crear la ventana
    public void crearVentana() {
        //this.setSize(800, 800);
        this.setSize(400, 400);
        //pone titulo a la ventana
        setTitle("Cifrado Cesar");
        //nos hace el cierre de la ventana 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 400));
        this.setResizable(false);
        this.getContentPane().setBackground(Color.GRAY);
        //quita el layout
        this.setLayout(null);
        this.setBounds(500, 75, 400, 400);

    }

    public void botones() {
        cifrar = new JButton("Cifrar");
        cifrar.setForeground(Color.blue);
        cifrar.setForeground(Color.black);
        cifrar.setBounds(200, 150, 75, 25);
        this.add(cifrar);

        descifrar = new JButton("Descifrar");
        descifrar.setForeground(Color.blue);
        descifrar.setForeground(Color.black);
        descifrar.setBounds(100, 150, 90, 25);
        this.add(descifrar);

    }

    public void texto() {
        num = new JTextField();
        texto = new JTextField();
        contenido = new JLabel();
        num.setBounds(150, 110, 50, 25);
        texto.setBounds(90, 180, 200, 25);
        contenido.setBounds(90, 210, 150, 25);
        this.add(num);
        this.add(texto);
        this.add(contenido);

    }

    public void logica() {
        cifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cifrar = texto.getText();
                String numero = num.getText();
                //System.out.println(cifrar);
                String ruta = texto.getText();

                if (cifrar.length() == 0 || numero.length() == 0) {
                    String error = "faltan datos a introducir";
                    contenido.setText(error);

                } else {
                    int aCifrar = Integer.parseInt(numero);
                    numAux = aCifrar;
                    //String mostrarCambio = cifrado(cifrar, aCifrar);
                    //contenido.setText(mostrarCambio);
                    aux = 0;
                    File archivo = null;
                    FileReader fr = null;
                    BufferedReader br = null;
                    String nuevoContenido = "";
                    String pasaRuta = ruta;
                    System.out.println(pasaRuta);
                    try {
                        // Apertura del fichero y creacion de BufferedReader para poder
                        // hacer una lectura comoda (disponer del metodo readLine()).
                        archivo = new File(pasaRuta);
                        fr = new FileReader(archivo);
                        br = new BufferedReader(fr);

                        // Lectura del fichero
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            //System.out.println(linea);
                            nuevoContenido += cifrado(linea, aCifrar) + "\n";
                            //System.out.println(nuevoContenido);
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    } finally {
                        // En el finally cerramos el fichero, para asegurarnos
                        // que se cierra tanto si todo va bien como si salta 
                        // una excepcion.
                        try {
                            if (null != fr) {
                                fr.close();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                    System.out.println(nuevoContenido);

                    FileWriter fichero = null;
                    PrintWriter pw = null;
                    try {
                        fichero = new FileWriter(pasaRuta);
                        pw = new PrintWriter(fichero);
                        pw.println(nuevoContenido);

                    } catch (Exception e4) {
                        e4.printStackTrace();
                    } finally {
                        try {
                            // Nuevamente aprovechamos el finally para 
                            // asegurarnos que se cierra el fichero.
                            if (null != fichero) {
                                fichero.close();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                String dato = "fichero cifrado";
                contenido.setText(dato);
            }

        });

        descifrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cifrar = texto.getText();
                String numero = num.getText();
                //System.out.println(cifrar);
                String ruta = texto.getText();
                int aCifrar = Integer.parseInt(numero);
                if (cifrar.length() == 0 || numero.length() == 0) {
                    String error = "faltan datos a introducir";
                    contenido.setText(error);

                } else {
                    //int aCifrar = Integer.parseInt(numero);
                    //String mostrarCambio = cifrado(cifrar, aCifrar);
                    //contenido.setText(mostrarCambio);
                    aux = 0;
                    File archivo = null;
                    FileReader fr = null;
                    BufferedReader br = null;
                    String nuevoContenido = "";
                    String pasaRuta = ruta;
                    System.out.println(pasaRuta);
                    try {
                        // Apertura del fichero y creacion de BufferedReader para poder
                        // hacer una lectura comoda (disponer del metodo readLine()).
                        archivo = new File(pasaRuta);
                        fr = new FileReader(archivo);
                        br = new BufferedReader(fr);

                        // Lectura del fichero
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            //System.out.println(linea);
                            nuevoContenido += descifrado(linea, aCifrar) + "\n";
                            //System.out.println(nuevoContenido);
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    } finally {
                        // En el finally cerramos el fichero, para asegurarnos
                        // que se cierra tanto si todo va bien como si salta 
                        // una excepcion.
                        try {
                            if (null != fr) {
                                fr.close();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }

                    System.out.println(nuevoContenido);

                    FileWriter fichero = null;
                    PrintWriter pw = null;
                    try {
                        fichero = new FileWriter(pasaRuta);
                        pw = new PrintWriter(fichero);
                        pw.println(nuevoContenido);

                    } catch (Exception e4) {
                        e4.printStackTrace();
                    } finally {
                        try {
                            // Nuevamente aprovechamos el finally para 
                            // asegurarnos que se cierra el fichero.
                            if (null != fichero) {
                                fichero.close();
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                String dato = "fichero descifrado";
                contenido.setText(dato);
            }

        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CifrarFicheroGrafico juego = new CifrarFicheroGrafico();

    }

}
