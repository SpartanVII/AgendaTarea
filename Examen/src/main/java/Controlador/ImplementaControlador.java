package Controlador;

import Modelo.CambioModelo;
import Modelo.Gestor.Tarea;
import Vista.InterrogaVista;

public class ImplementaControlador implements Controlador {

    private InterrogaVista vista;
    private CambioModelo modelo;

    public ImplementaControlador() {}

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }

    @Override
    public void anyadirTarea(Tarea.Prioridad prioridad, boolean completado){
        modelo.anyadirTarea(vista.getTitulo(),vista.getDescrip(),prioridad,completado);
    }

    @Override
    public void eliminarTarea(){
        modelo.eliminarrTarea(vista.getClave());
    }

    @Override
    public void actualizarTarea( Tarea.Prioridad prioridad, boolean completado){
        modelo.actualizaTarea(vista.getTitulo(),vista.getDescrip(),prioridad,completado,vista.getClave());
    }

}
