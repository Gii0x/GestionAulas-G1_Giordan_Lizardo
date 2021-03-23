/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_giordan_lizardo;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Gii0
 */
public class Proyecto_Giordan_Lizardo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File fichero = new File("/GitKrakenProject_Gio_Liz/Aula/clasroom.txt");
        
        try {
            // Codificación ISO-8859-1 (ANSI) o UTF-8 dependiendo de cómo esté creado el fichero de texto
            Scanner lectorFichero = new Scanner(fichero, "ISO-8859-1");
            
            while(lectorFichero.hasNext()) {
                System.out.println(lectorFichero.nextLine());
            }
            
            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }
    }
    
}
