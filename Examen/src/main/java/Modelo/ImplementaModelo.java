package Modelo;

import Modelo.Gestor.Gestor;
import Modelo.Gestor.Tarea;
import Vista.InformaVista;
import java.io.*;
import java.util.Map;

public class ImplementaModelo implements  InterrogaModelo,CambioModelo{

    private Gestor gestor;
    private InformaVista vista;


    public ImplementaModelo(Gestor gestor) {
        this.gestor = gestor;
    }


    @Override
    public void anyadirTarea(String titulo, String descripcio, Tarea.Prioridad prioridad, boolean completado){
        if(titulo.isEmpty()){ vista.faltanParametros(); return;}
        if(!gestor.anyadirTarea(titulo,descripcio,prioridad,completado)) vista.yaExistia();
        vista.actualizaTabla(gestor.getTareas());
    }

    @Override
    public void actualizaTarea(String titulo, String descripcio, Tarea.Prioridad prioridad, boolean completado,String clave){
        if(clave.isEmpty()) vista.seleccioneTarifaA();
        if(!gestor.actualizaTarea(titulo,descripcio,prioridad,completado,clave)) vista.noExistia();
        vista.actualizaTabla(gestor.getTareas());

    }


    @Override
    public void eliminarrTarea(String clave){
        if(clave.isEmpty()){ vista.seleccioneTareaE(); return;}
        if(!gestor.eliminarTarea(clave)) vista.noExistia();
        vista.actualizaTabla(gestor.getTareas());
        vista.vaciaInfo();

    }

    @Override
    public Tarea devuelveTarea(String clave){
        return gestor.devuelveTarea(clave);
    }



    @Override
    public Object[][] matrizTabla(Map<String,Tarea> mapa){
        Object[][] res =new Object[mapa.size()][4];
        int i=0;
        for(Tarea tarea: mapa.values()){
            res[i][0]=tarea.getCodigo();
            res[i][1]=tarea.getTitulo();
            res[i][2]=tarea.getPrioridad();
            res[i][3]=tarea.isCompletada();
            i++;
        }
        return res;

    }

    @Override
    public  void serializar() throws IOException {
        //Serializamos un archivo a un objeto de datos
        File fich=new File("datos.bin");
        FileOutputStream fos = new FileOutputStream(fich);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(gestor);
        oos.close();
    }




    @Override
    public Gestor getGestor() {
        return gestor;
    }

    public void setVista(InformaVista vista) {
        this.vista = vista;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }
}



