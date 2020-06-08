package Modelo.Filtros;

import Modelo.Gestor.Tarea;
import java.util.HashMap;
import java.util.Map;

public class FiltraCompletado implements Filtrar {

    boolean completado;

    public FiltraCompletado(boolean completado) {

        this.completado = completado;
    }

    @Override
    public Map<String, Tarea> filtra(Map<String,Tarea> mapa){
        Map<String,Tarea> res=new HashMap<>();

        for(Tarea tarea : mapa.values())
            if(tarea.isCompletada()==completado)
                res.put(tarea.getCodigo(),tarea);

        return res;
    }
}
