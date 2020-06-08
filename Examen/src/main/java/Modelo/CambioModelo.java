package Modelo;

import Modelo.Gestor.Tarea;

public interface CambioModelo {
    void actualizaTarea(String titulo, String descripcio, Tarea.Prioridad prioridad, boolean completado,String clave);
    void anyadirTarea(String titulo, String descripcio, Tarea.Prioridad prioridad, boolean completado);
    void eliminarrTarea(String titulo);
}
