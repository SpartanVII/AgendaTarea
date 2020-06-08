package Modelo.Filtros;

import Modelo.Gestor.Tarea;

import java.util.Map;

public interface Filtrar {
    Map<String, Tarea> filtra(Map<String,Tarea> mapa);
}
