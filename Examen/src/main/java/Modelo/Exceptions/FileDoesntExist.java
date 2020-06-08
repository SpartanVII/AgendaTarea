package Modelo.Exceptions;

import java.io.File;

public class FileDoesntExist extends Exception {

        public FileDoesntExist() {
            super ("El archivo no existe");
        }

        public FileDoesntExist(File archivo) {
            super ("El archivo "+archivo.getName()+" no existía");
        }

}
