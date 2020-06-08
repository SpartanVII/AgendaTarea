package Modelo;

import Modelo.Gestor.Gestor;
import Modelo.Gestor.Tarea;

import java.io.IOException;
import java.util.Map;

public interface InterrogaModelo {
    Object[][] matrizTabla(Map<String,Tarea> mapa);
    Tarea devuelveTarea(String clave);
    void serializar() throws IOException;
    Gestor getGestor();
}
