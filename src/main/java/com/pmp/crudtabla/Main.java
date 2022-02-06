/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pmp.crudtabla;

import java.util.Scanner;
import java.util.ArrayList;

import com.pm.dao.ModeloProductos;
import com.pm.dao.Producto;
/**
 *
 * @author ME
 */
public class Main {
    private static Scanner entradaTeclado = new Scanner(System.in);
    private static ModeloProductos modelo = new ModeloProductos();
    
    public static void main ( String[] args ) {
        String opcion = "L";
        
        while (!opcion.contentEquals("S")){
            switch (opcion) {
                case "I":
                    //Utilidades.print("Insertar");
                    insertarNuevoProducto();
                    break;
                case "A":
                    //Utilidades.print("Actualizar");
                    actualizarUnProducto();
                    break;
                case "L":
                    //Utilidades.print("Listar");
                    mostrarListado();
                    break;
                case "E":
                    //Utilidades.print("Eliminar");
                    eliminarUnProducto();
                    break;
                default:
                    Utilidades.print("Opción no Encontrada");
            }
            
            Utilidades.menu();
            Utilidades.print("Ingrese su opción: ");
            opcion = entradaTeclado.nextLine().toUpperCase();
        }
    }
    
    private static void mostrarListado(){
        Utilidades.printEncabezadoTabla();
        ArrayList<Producto> productos = modelo.obtenerProductos();
        
        for (int i = 0; i < productos.size(); i++){
            Utilidades.print(productos.get(i).obtenerFila());
            Utilidades.separador();
        }
    }
    
    private static void insertarNuevoProducto() {
        Producto nuevoProducto = new Producto();
        Utilidades.encabezado("|                                NUEVO PRODUCTO                                |");
        nuevoProducto.setNombre(Utilidades.capturarCampo(entradaTeclado, "Nombre", "Producto XYZ"));
        nuevoProducto.setDescripcion(Utilidades.capturarCampo(entradaTeclado, "Descripcion", "-------"));
        nuevoProducto.setPrecio(Double.parseDouble(Utilidades.capturarCampo(entradaTeclado, "Precio", "100.00")));
        nuevoProducto.setCantidad(Integer.parseInt(Utilidades.capturarCampo(entradaTeclado, "Cantidad", "10")));
        Utilidades.separador();
        
        int insertado = modelo.insertarNuevoProducto(nuevoProducto);
        
        if (insertado > 0) {
            Utilidades.print("Producto Agregado Satisfactoriamente!");
        }
        
        Utilidades.separador();
    }
    
    private static void actualizarUnProducto() {
        int actualizado = 0;
        int encontrado = 0;
        Producto productoActualizado = new Producto();
        ArrayList<Producto> productos = modelo.obtenerProductos();
        
        Utilidades.encabezado("|                              ACTUALIZAR PRODUCTO                             |");
        Utilidades.print("Ingrese ID del registro a actualizar: ");
        int capturarId = Integer.parseInt(entradaTeclado.nextLine());
        
        for (int i = 0; i < productos.size(); i++) {
            if (capturarId == (productos.get(i).getId())) {
                productoActualizado.setId(productos.get(i).getId());
                productoActualizado.setNombre(Utilidades.capturarCampo(entradaTeclado, "Nombre", productos.get(i).getNombre()));
                productoActualizado.setDescripcion(Utilidades.capturarCampo(entradaTeclado, "Descripcion", productos.get(i).getDescripcion()));
                productoActualizado.setPrecio(Double.parseDouble(Utilidades.capturarCampo(entradaTeclado, "Precio", Double.toString(productos.get(i).getPrecio()))));
                productoActualizado.setCantidad(Integer.parseInt(Utilidades.capturarCampo(entradaTeclado, "Cantidad", Double.toString(productos.get(i).getCantidad()))));
                Utilidades.separador();
                
                actualizado = modelo.actualizarProducto(productoActualizado);
                encontrado = 1;
            }
        }
        
        if (actualizado > 0) {
            Utilidades.print("Producto Actualizado Satisfactoriamente!");
        }
        
        if (encontrado == 0) {
            Utilidades.print("Producto no encontrado");
        }
    }
    
    private static void eliminarUnProducto() {
        int eliminado = 0;
        int negacion = 0;
        String confirmar;
        ArrayList<Producto> productos = modelo.obtenerProductos();
        
        Utilidades.encabezado("|                               ELIMINAR PRODUCTO                              |");
        Utilidades.print("Ingrese ID del registro a eliminar: ");
        int capturarId = Integer.parseInt(entradaTeclado.nextLine());
        
        for (int i = 0; i < productos.size(); i++) {
            if (capturarId == (productos.get(i).getId())) {
                Utilidades.separador();
                Utilidades.print("ID: " + Integer.toString(productos.get(i).getId()));
                Utilidades.print("Nombre: " + productos.get(i).getNombre());
                Utilidades.print("Descripcion: " + productos.get(i).getDescripcion());
                Utilidades.print("Precio: " + Double.toString(productos.get(i).getPrecio()));
                Utilidades.print("Cantidad: " + Double.toString(productos.get(i).getCantidad()));
                Utilidades.separador();
                
                Utilidades.print("¿Desea eliminar el producto? S/N: ");
                confirmar = entradaTeclado.nextLine().toUpperCase();
                
                if (confirmar.contentEquals("S")) {
                    Utilidades.separador();
                    negacion = 1;
                    eliminado = modelo.eliminarProducto(capturarId);
                } else {
                    negacion = 1;
                    break;
                }
            } 
        }
        
        if (eliminado > 0) {
            Utilidades.print("Producto Eliminado Satisfactoriamente!");
        }
        
        if (negacion == 0) {
            Utilidades.print("Producto no encontrado");
        }
    }
}
