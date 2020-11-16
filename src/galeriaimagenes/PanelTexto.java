package galeriaimagenes;

import javax.swing.*;
@SuppressWarnings("serial")

/**
 *
 * @author Luis Barca
 */
public class PanelTexto extends JPanel {
    // Atributos
    // Variable de tipo JTextField para contener el texto
    private final JTextField areaTexto;
    private String texto;

    /**
     * Constructor
     */
    public PanelTexto() {
        // Instanciamos y asignamos un texto al areaTexto
        areaTexto = new JTextField();
        texto = "Selecciona librería de imágenes";
        areaTexto.setText(texto);
        // De esta manera el texto no puede ser editado por el usuario
        areaTexto.setEditable(false);
        // Alineamos el texto al centro
        areaTexto.setHorizontalAlignment(JTextField.CENTER);
        this.add(areaTexto);

        this.setVisible(true);
    }

    /**
     * Método que modifica el texto del Panel de Texto con un string pasado por 
     * parámetro
     * @param s - String
     */
    public void setTexto(String s) {
        this.texto = s;
        areaTexto.setText(texto);
    }
}
