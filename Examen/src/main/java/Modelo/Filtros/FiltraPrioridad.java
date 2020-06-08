package Modelo.Filtros;

import Modelo.Gestor.Tarea;
import java.util.HashMap;
import java.util.Map;

public class FiltraPrioridad implements Filtrar {

    Tarea.Prioridad prioridad;

    public FiltraPrioridad(Tarea.Prioridad prioridad) {
        this.prioridad = prioridad;
    }


    @Override
    public Map<String,Tarea> filtra(Map<String,Tarea> mapa){
        Map<String,Tarea> res=new HashMap<>();
        for(Tarea tarea : mapa.values())
            if(tarea.getPrioridad().equals(prioridad.toString()))
                res.put(tarea.getCodigo(),tarea);

        return res;
    }
}
