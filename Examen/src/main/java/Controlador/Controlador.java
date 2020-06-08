package Controlador;

import Modelo.Gestor.Tarea;

public interface Controlador {
     void anyadirTarea( Tarea.Prioridad prioridad, boolean completado);
     void actualizarTarea(Tarea.Prioridad prioridad, boolean completado);
     void eliminarTarea();
}
