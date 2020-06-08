package Vista.clasesPersonalizadas;

import Modelo.InterrogaModelo;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class escuchadorVentana implements WindowListener {


    private InterrogaModelo modelo;

    public escuchadorVentana(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent e){
        try {
            System.out.println("Guardando...");
            modelo.serializar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
