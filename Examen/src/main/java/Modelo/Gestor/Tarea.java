package Modelo.Gestor;

import java.io.Serializable;

public class Tarea implements Serializable {

    private String Titulo;
    private String Descripcion;
    private Prioridad Prioridad;
    private boolean Completada;
    private String Codigo;

    public enum Prioridad {Alta,Normal,Baja}


    public Tarea(String titulo, String descripcion, Prioridad prioridad, boolean completada,String codigo) {
        Titulo = titulo;
        Descripcion = descripcion;
        Prioridad = prioridad;
        Completada = completada;
        Codigo=codigo;
    }


    public String getTitulo() {
        return Titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getPrioridad() {
        return Prioridad.toString();
    }

    public String getCodigo() {
        return Codigo;
    }

    public boolean isCompletada() {
        return Completada;
    }
}
