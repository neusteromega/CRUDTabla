/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.crudtabla;

import java.util.Scanner;

/**
 *
 * @author ME
 */
public class Utilidades {
    public static void separador(){
        System.out.println(new String(new char[80]).replace("\0", "-"));
    }
    
    public static void print(String text){    
        System.out.println(text);
    }
    
    public static void encabezado(String texto) {
        separador();
        print(texto);
        separador();
    }
    
    public static void menu() {
        String titulo = "|                           SUPERMERCADOS LA COLONIA                           |";
        String opciones = "|------------------------------------------------------------------------------|\n"
                        + "|              I: Insertar              |             A: Actualizar            |\n"
                        + "|---------------------------------------|--------------------------------------|\n"
                        + "|              L: Listar                |             E: Eliminar              |\n"
                        + "|---------------------------------------|--------------------------------------|\n"
                        + "|                                    S: Salir                                  |";
        separador();
        print(titulo);
        print(opciones);
        separador(); 
    }
    
    public static String capturarCampo(Scanner entradaTeclado, String leyenda, String valorPredeterminado){
       print(leyenda + "(" + valorPredeterminado + "):");
       String input = entradaTeclado.nextLine();
       
       if (input.isEmpty() || input.contentEquals(valorPredeterminado)){
           return valorPredeterminado;
       }
       
       return input;
    }
    
    public static void printEncabezadoTabla (){
        separador();
        print(String.format("%s\t%s\t%s\t\t%s", "ID", "PRODUCTO", "PRECIO", "CANTIDAD"));
        separador();
    }
}
