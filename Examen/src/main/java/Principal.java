import Controlador.ImplementaControlador;
import Modelo.Exceptions.FileDoesntExist;
import Modelo.Gestor.Gestor;
import Modelo.ImplementaModelo;
import Vista.ImplementaVista;

import java.io.*;


public class Principal {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ImplementaControlador controlador = new ImplementaControlador();
        ImplementaVista vista = new ImplementaVista();
        ImplementaModelo modelo = new ImplementaModelo(deserializar());
        modelo.setVista(vista);
        controlador.setVista(vista);
        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.creaGUI();

    }


    public static Gestor deserializar() throws IOException, ClassNotFoundException {
        File fich = new File("datos.bin");
        try{
            if(!fich.exists()) throw new FileDoesntExist(fich);
        }catch (FileDoesntExist e) {
            e.printStackTrace();
            return new Gestor();
        }

        FileInputStream door = new FileInputStream(fich);
        ObjectInputStream reader;
        try{
            reader = new ObjectInputStream(door);
        }catch (StreamCorruptedException e){
            System.out.println("ARCHIVO CORRUPTO");
            return new Gestor();
        }
       return ((Gestor) reader.readObject());
    }

}
