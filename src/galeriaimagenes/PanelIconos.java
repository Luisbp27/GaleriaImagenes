package galeriaimagenes;

import javax.swing.*;
import java.awt.*;
@SuppressWarnings("serial")

/**
 *
 * @author Luis Barca
 */
public class PanelIconos extends JPanel {
    // Atributos
    // Declaración de un array bidimensional de objetos Casilla
    private final Casilla[][] c;
    // Estas dos variables enteras nos servirán para saber en todo momento la 
    // posición anterior de la casilla que estemos tratando durante el programa
    private int ultimaX = 0;
    private int ultimaY = 0;
    // Objeto PanelTexto para modificarlo posteriormente
    private PanelTexto panelTexto;
    

    /**
     * Constructor
     */
    public PanelIconos() {
        // Instanciamos el array bidimensional de objetos Casilla
        c = new Casilla[7][4];
        // Establecemos un fondo y un layout
        this.setBackground(Color.BLACK);
        this.setLayout(new GridLayout(7, 4));
        colocarCasillas();

        this.setVisible(true);
    }

    /**
     * Método que efectua la colocación de las casillas en el panel
     */
    public void colocarCasillas() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                c[i][j] = new Casilla();
                c[i][j].setHorizontalAlignment(JLabel.CENTER);
                this.add(c[i][j]);
            }
        }
    }

    /**
     * Método que pasado un string y unas coordenadas x e y, actualiza la casilla
     * @param s - String con la ruta de la imágen
     * @param x - Coordenada X
     * @param y - Coordenada Y
     */
    public void actualizarCasillas(String s, int x, int y) {
        c[x][y].actualizarCasilla(s);
        this.add(c[x][y]);
    }

    /**
     * Método que pasadas unas coordenadas x e y, marca la casilla que se está
     * seleccionando en ese momento
     * @param x - Coordenada X
     * @param y - Coordenada Y
     * @return 
     */
    public ImageIcon marcar(int x, int y) {
        ImageIcon imagen = c[y][x].getImagen();
        
        if(imagen.getDescription().equals("C:/Users/luisb/IdeaProjects/TallerEvaluable2/imagenes/imagenVacia.jpg")) {
            c[ultimaY][ultimaX].setBorder(null);
            panelTexto.setTexto("Icono seleccionado no representa una imágen <Selecciona otro icono>");
        }else {
            bordearCasilla(y, x);
        }

        ultimaX = x;
        ultimaY = y;

        return imagen;
    }

    /**
     * Método que nos permite seleccionar la primera casilla cuando cargamos
     * la librería de imágenes
     * @return 
     */
    public ImageIcon seleccionarPrimeraCasilla() {
        bordearCasilla(0, 0);
        ImageIcon imagen = c[0][0].getImagen();

        return imagen;
    }

    /**
     * Método que pasadas fila y columna, bordea la correspondiente casilla
     * @param fila - Número de fila
     * @param columna - Número de columna
     */
    public void bordearCasilla(int fila, int columna) {
        c[ultimaY][ultimaX].setBorder(null);
        c[fila][columna].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
        
        ultimaY = fila;
        ultimaX = columna;
    }

    /**
     * Método que devuelve el valor del atributo ultimaX
     * @return 
     */
    public int getUltimaX() {
        return ultimaX;
    }

    /**
     * Método que devuelve el valor del atributo ultimaY
     * @return 
     */
    public int getUltimaY() {
        return ultimaY;
    }
    
    /**
     * Método que asigna el PanelTexto pasado por parametro al PanelTexto 
     * de la clase
     * @param pt 
     */
    public void setPanelTexto(PanelTexto pt) {
        panelTexto = pt;
    }
}
