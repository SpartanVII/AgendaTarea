package Modelo.Tests;

import Modelo.Gestor.Gestor;
import Modelo.Gestor.Tarea;
import Modelo.Filtros.FiltraCompletado;
import Modelo.Filtros.FiltraPrioridad;
import Modelo.Filtros.Filtrar;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FiltrarTests {
    Filtrar filtro;
    Gestor gestor=new Gestor();

    @Test
    public void filtraCompletado(){

        Tarea tarea1 = new Tarea("lala","lala", Tarea.Prioridad.Alta,true,"1");
        Tarea tarea2 = new Tarea("lala","lala", Tarea.Prioridad.Normal,false,"2");
        Tarea tarea3 = new Tarea("lala","lala", Tarea.Prioridad.Baja,true,"3");

        gestor.getTareas().put("1",tarea1);
        gestor.getTareas().put("2",tarea2);
        gestor.getTareas().put("3",tarea3);


        filtro = new FiltraCompletado(true);
        assertTrue(filtro.filtra(gestor.getTareas()).containsKey("1"));
        assertFalse(filtro.filtra(gestor.getTareas()).containsKey("2"));
        assertTrue(filtro.filtra(gestor.getTareas()).containsKey("3"));


        filtro = new FiltraCompletado(false);
        assertFalse(filtro.filtra(gestor.getTareas()).containsKey("1"));
        assertTrue(filtro.filtra(gestor.getTareas()).containsKey("2"));
        assertFalse(filtro.filtra(gestor.getTareas()).containsKey("3"));
    }


    @Test
    public void filtraPrioridad(){

        Tarea tarea1 =new Tarea("lala","lala", Tarea.Prioridad.Alta,true,"1");
        Tarea tarea2 =new Tarea("lala","lala", Tarea.Prioridad.Normal,false,"2");
        Tarea tarea3 =new Tarea("lala","lala", Tarea.Prioridad.Baja,true,"3");

        gestor.getTareas().put("1",tarea1);
        gestor.getTareas().put("2",tarea2);
        gestor.getTareas().put("3",tarea3);


        filtro=new FiltraPrioridad(Tarea.Prioridad.Alta);
        assertTrue(filtro.filtra(gestor.getTareas()).containsKey("1"));
        assertFalse(filtro.filtra(gestor.getTareas()).containsKey("2"));
        assertFalse(filtro.filtra(gestor.getTareas()).containsKey("3"));


        filtro=new FiltraPrioridad(Tarea.Prioridad.Normal);
        assertFalse(filtro.filtra(gestor.getTareas()).containsKey("1"));
        assertTrue(filtro.filtra(gestor.getTareas()).containsKey("2"));
        assertFalse(filtro.filtra(gestor.getTareas()).containsKey("3"));


        filtro=new FiltraPrioridad(Tarea.Prioridad.Baja);
        assertFalse(filtro.filtra(gestor.getTareas()).containsKey("1"));
        assertFalse(filtro.filtra(gestor.getTareas()).containsKey("2"));
        assertTrue(filtro.filtra(gestor.getTareas()).containsKey("3"));
    }
}
