package Modelo.Gestor;


import java.io.*;
import java.util.*;

public class Gestor implements Serializable {


    Map<String,Tarea> tareas = new HashMap<>();
    private static final long serialVersionUID = 8799656478674716638L;
    public long codigo = 0;

    public String codigo(){
        return String.valueOf(++codigo);
    }


    public boolean anyadirTarea(String titulo, String descripcion, Tarea.Prioridad prioridad, boolean completado){
        if(existe(titulo,descripcion)){ return false;}
        String cod=codigo();
        tareas.put(cod,new Tarea(titulo,descripcion,prioridad,completado,cod));
        return true;
    }


    public boolean eliminarTarea(String clave){
        if(!tareas.containsKey(clave)) return false;
        tareas.remove(clave);
        return true;
    }

    public boolean actualizaTarea(String titulo, String descripcio, Tarea.Prioridad prioridad, boolean completado,String clave){
        if(!tareas.containsKey(clave)) return false;
        tareas.replace(clave,new Tarea(titulo,descripcio,prioridad,completado,clave));
        return true;
    }

    public boolean existe(String titulo,String descripcion){
        for(Tarea tarea:tareas.values())
            if(tarea.getDescripcion().equals(descripcion) && tarea.getTitulo().equals(titulo))
                return true;
        return false;
    }

    
    public Tarea devuelveTarea(String clave){
        return tareas.get(clave);
    }

    public Map<String, Tarea> getTareas() {
        return tareas;
    }

}
