package galeriaimagenes;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
@SuppressWarnings("serial")

/**
 *
 * @author Luis Barca
 */
public class AreaBotones extends JPanel implements ActionListener {
    // Atributos
    // Declaración de los JButton que nos permiten interactuar con el programa
    private final JButton abrir;
    private final JButton izquierda;
    private final JButton derecha;
    private final JButton fin;
    // File y JFileChooser que nos permiten agregar la librería de imágenes de 
    // nuestro PC
    private final JFileChooser seleccion;
    private File fichero;
    // Variable String donde almacenamos la ruta de la imágen
    private String ruta;
    // Array de Strings que usaremos para guardar los nombres de las imágenes
    String[] imagenes;
    // Declaración de paneles que modificaremos
    private PanelIconos panelIconos;
    private PanelImagen panelImagen;
    private PanelTexto panelTexto;
    // Declaración de una variable booleana que nos permitirá o no interactuar 
    // con el Panel de Iconos
    private boolean iconosMarcables;
    // Declaración de dos variables enteras las cuales contienen la coordenada
    // x e y respectivamente de la última casilla que es una imágen y no una
    // imágen vacía
    private int ultimaImagenX;
    private int ultimaImagenY;
    // Declaración de una matriz de Strings que contendrá los Strings de las
    // imágenes cargadas por el usuario
    private String[][] matrizStrings;
    
    /**
     * Constructor
     */
    public AreaBotones() {
        // Instanciamos el array bisimensional
        this.matrizStrings = new String[7][4];
        // Instanciamos los botones
        this.abrir = new JButton("Abrir librería imágenes");
        this.izquierda = new JButton("<");
        this.derecha = new JButton(">");
        this.fin = new JButton("Finalizar");

        // Configuramos el panel
        this.setLayout(new FlowLayout());
        crearBotones();
        this.setBackground(Color.LIGHT_GRAY);
        // Instanciamos y configuramos el JFileChooser
        seleccion = new JFileChooser();
        seleccion.setAcceptAllFileFilterUsed(false);
        seleccion.addChoosableFileFilter(new FileNameExtensionFilter("JPG, PNG, GIF", "jpg", "png", "gif"));
        // Establecemos la interacción con el Panel de Iconos en false
        iconosMarcables = false;

        this.setVisible(true);
    }

    /**
     * Método que lleva a cabo la creación de los botones
     */
    private void crearBotones() {
        // Asignamos un color a los botones
        abrir.setBackground(Color.GRAY);
        izquierda.setBackground(Color.GRAY);
        derecha.setBackground(Color.GRAY);
        fin.setBackground(Color.GRAY);

        // Agregamos los botones al panel AreaBotones
        this.add(abrir);
        this.add(izquierda);
        this.add(derecha);
        this.add(fin);

        // Añadimos los ActionListener correspondientes a los botones
        abrir.addActionListener(this);
        izquierda.addActionListener(this);
        derecha.addActionListener(this);
        fin.addActionListener(this);
    }

    /**
     * Método que permite la interacción con los botones y efectua las 
     * correspondientes acciones dependiendo de cual sea el botón pulsado
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Si se pulsa el botón "Abrir librería imágenes"
            if(e.getSource() == abrir) {
                if (seleccion.showDialog(null, abrir.getName()) == JFileChooser.APPROVE_OPTION) {
                    fichero = seleccion.getSelectedFile();
                    if (fichero.canRead()) {
                        // Nos aseguramos de que es una imágen
                        if (esImagen(fichero.getName())) {
                            cargarImagenes();
                            // Marcamos y obtenemos la imágen de la primera casilla
                            ImageIcon img = panelIconos.seleccionarPrimeraCasilla();
                            // Actualizamos la casilla correspondiente
                            panelImagen.actualizarImagen(img);
                        }
                    }
                }
                //Modificamos el texto del Panel de Texto
                panelTexto.setTexto("Selecciona el icono a visualizar con el ratón o con los botones '<' y '>'");
                // Permitimos la interacción con el Panel de Iconos
                iconosMarcables = true;
            
            // Si se pulsa el botón "<" efectua el movimiento a la izquierda 
            //en cualquier condición
            }else if(e.getSource() == izquierda) {
                if(iconosMarcables) {
                    // Creamos dos variables enteras para facilitar el entendimiento 
                    // del código
                    int fila = panelIconos.getUltimaY();
                    int columna = panelIconos.getUltimaX();

                    // Si estamos en la columna 0, nos movemos a la fila anterior
                    // y a la última columna de dicha fila
                    if(fila != 0 && columna == 0) {
                        fila--;
                        columna = 3;
                    // En caso de ser la última imágen del panelIconos, se 
                    // vuelve a la primera casilla
                    }else if(fila == 0 && columna == 0) {
                        fila = ultimaImagenY;
                        columna = ultimaImagenX;
                    }else {
                        columna--;
                    }
                    
                    panelIconos.bordearCasilla(fila, columna);
                    ImageIcon img = new ImageIcon(ruta + "/" + matrizStrings[fila][columna]);
                    panelImagen.actualizarImagen(img);
                }
            
            // Si se pulsa el botón ">" efectua el movimiento a la izquierda 
            // en cualquier condición
            } else if (e.getSource() == derecha) {
                if(iconosMarcables) {
                    // Creamos dos variables enteras para facilitar el entendimiento 
                    // del código
                    int fila = panelIconos.getUltimaY();
                    int columna = panelIconos.getUltimaX();
                    
                    // Si cumple la condición y la siguiente imágen no es vacia
                    // se mueve una casilla a la derecha
                    if(columna != 3) {
                        if(!isVacia(fila, columna + 1)) {
                            columna++;
                        }
                    // Si cumple la condición y no es vacía la siguiente casilla,
                    // pasa a la primera casilla de la siguiente fila
                    }else if(fila != 6 && columna == 3) {
                        if(!isVacia(fila + 1, 0)) {
                            columna = 0;
                            fila++;
                        // Si es la última imágen cargada, pasa a la primera
                        }else if(fila == ultimaImagenY && columna == ultimaImagenX) {
                            fila = 0;
                            columna = 0;
                        }
                    // Si es la última imágen    
                    }else if(fila == 6 && columna == 3) {
                        fila = 0;
                        columna = 0;
                    }
                    
                    panelIconos.bordearCasilla(fila, columna);
                    ImageIcon img = new ImageIcon(ruta + "/" + matrizStrings[fila][columna]);
                    panelImagen.actualizarImagen(img);
                }
                
            // Si se pulsa el botón "Finalizar" efectua la finalización del programa
            } else if (e.getSource() == fin) {
                System.exit(0);
            }
        }catch (HeadlessException error) {
            System.out.println("ERROR: " + error.getMessage());
        }
    }

    /**
     * Método que muestra un menu para permitir al usuario cargar las imágenes
     * que desee
     */
    public void cargarImagenes() {
        try {
            imagenes = fichero.getParentFile().list();
            ruta = fichero.getParent();
            int contador = 0;
            int fila = 0;
            int columna = 0;
            
            // Rellenamos el atributo matrizStrings de los nombres de las imágenes 
            // a visualizar
            matrizStrings();
            
            // Rellenamos el array de casillas con la librería de imágenes
            // seleccionada por el usuario
            while((contador < 28) && contador < imagenes.length && esImagen(imagenes[contador])) {
                if(columna == 4) {
                    columna = 0;
                    fila++;
                }
                panelIconos.actualizarCasillas(ruta + "/" + imagenes[contador], fila, columna);
                contador++;
                columna++;
            }
           
            ultimaImagenY = fila;
            ultimaImagenX = columna - 1;
            
            // Rellenamos de imagenes vacias en el caso de no cargar 28 imágenes
            if(imagenes.length < 28) {
                while(contador < 28) {
                    if(columna == 4) {
                        columna = 0;
                        fila++;
                    }
                    panelIconos.actualizarCasillas("C:/Users/luisb/IdeaProjects/TallerEvaluable2/imagenes/imagenVacia.jpg", fila, columna);
                    contador++;
                    columna++;
                }
            }
        } catch(Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
    
    /**
     * Método que rellena la matrizStrings de los Strings de todas las imágenes
     * cargadas por el usuario
     */
    public void matrizStrings() {
        int contador = 0;
         
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                if(contador < imagenes.length) {
                    matrizStrings[i][j] = imagenes[contador];
                    contador++;
                }else {
                    matrizStrings[i][j] = "imagenVacia.jpg";
                }
            }
        }
    }

    /**
     * Método que permite modificar el atributo panelIconos mediante un panelIconos 
     * pasado por parámetro
     * @param pi - PanelIconos
     */
    public void setPanelIconos(PanelIconos pi) {
        this.panelIconos = pi;
    }

    /**
     * Método que permite modificar el atributo areaTexto mediante un panelTexto 
     * pasado por parámetro
     * @param pt - PanelTexto
     */
    public void setPanelTexto(PanelTexto pt) {
        this.panelTexto = pt;
    }

    /**
     * Método que permite modificar el atributo panelImagen mediante un panelImagen 
     * pasado por parámetro
     * @param pi - PanelImagen
     */
    public void setPanelImagen(PanelImagen pi) {
        this.panelImagen = pi;
    }

    /**
     * Método que verifica si la ruta de la imagen pasada por parámetro es una
     * imágen o no
     * @param direccionImagen - String de la ruta de la imágen
     * @return
     */
    public boolean esImagen(String direccionImagen) {
        return (direccionImagen.lastIndexOf(".jpg") > 0 || direccionImagen.lastIndexOf(".png") > 0 || direccionImagen.lastIndexOf(".gif") > 0);
    }

    /**
     * Método que devuelve el valor del atributo iconosMarcables (true o false)
     * @return
     */
    public boolean isIconosMarcables() {
        return iconosMarcables;
    }
   
    /**
     * Método que verifica si una imágen de una casilla (con la fila y la columna 
     * pasadas por parámetro) es una imagenVacia.jpg
     * @param fila - Fila de la casilla
     * @param columna - Columna de la casilla
     * @return 
     */
    public boolean isVacia(int fila, int columna) {
        return matrizStrings[fila][columna].equals("imagenVacia.jpg");
    }
}
