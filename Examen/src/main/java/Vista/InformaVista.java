package Vista;

import Modelo.Gestor.Tarea;
import java.util.Map;

public interface InformaVista {
    void faltanParametros();
    void yaExistia();
    void seleccioneTarifaA();
    void seleccioneTareaE();
    void noExistia();

    void actualizaTabla(Map<String, Tarea> mapa);
    void vaciaInfo();

}

