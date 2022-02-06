/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ME
 */
public class ModeloProductos {
    private Connection _conexion = null;
    
    public ModeloProductos(){
        _conexion = ConexionSQL.getConexion();
        String sqlCrearTabla = "CREATE TABLE IF NOT EXISTS productos"
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + " nombre TEXT NOT NULL,"
                + " descripcion TEXT NOT NULL,"
                + " precio DECIMAL(10,2),"
                + " cantidad DECIMAL(10,2));";
        
        try {
            Statement comando = _conexion.createStatement();
            int resultado = comando.executeUpdate(sqlCrearTabla);
            
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<Producto> obtenerProductos (){
        try {     
            ArrayList productos = new ArrayList<Producto>();
            Statement comandoSQL = _conexion.createStatement();
            ResultSet registroEnTabla = comandoSQL.executeQuery("SELECT * from productos;");
            
            while (registroEnTabla.next()){
                Producto productoActual = new Producto();
                
                productoActual.setId(registroEnTabla.getInt("id"));
                productoActual.setNombre(registroEnTabla.getString("nombre"));
                productoActual.setDescripcion(registroEnTabla.getString("descripcion"));
                productoActual.setPrecio(registroEnTabla.getDouble("precio"));
                productoActual.setCantidad(registroEnTabla.getInt("cantidad"));
                
                productos.add(productoActual);
            }
            
            return productos;
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return new ArrayList<Producto>();
        }
    }
    
    public int insertarNuevoProducto(Producto nuevoProducto){
        try {
            String sqlInsertar = "INSERT INTO productos (nombre, descripcion, precio, cantidad) values (?, ?, ?, ?);"; 
            PreparedStatement comandoSQL = _conexion.prepareStatement(sqlInsertar);

            comandoSQL.setString(1, nuevoProducto.getNombre());
            comandoSQL.setString(2, nuevoProducto.getDescripcion());
            comandoSQL.setDouble(3, nuevoProducto.getPrecio());
            comandoSQL.setDouble(4, nuevoProducto.getCantidad());
            
            int RegistrosAfectados = comandoSQL.executeUpdate();
            comandoSQL.close();
            return RegistrosAfectados; 
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int actualizarProducto(Producto productoActualizado){
        try {
            String sqlActualizar = "UPDATE productos SET nombre=?, descripcion=?, precio=?, cantidad=? WHERE id=?";
            PreparedStatement comandoSQL = _conexion.prepareStatement(sqlActualizar);
            comandoSQL.setString(1, productoActualizado.getNombre());
            comandoSQL.setString(2, productoActualizado.getDescripcion());
            comandoSQL.setDouble(3, productoActualizado.getPrecio());
            comandoSQL.setDouble(4, productoActualizado.getCantidad());
            comandoSQL.setInt(5, productoActualizado.getId());
            
            int RegistrosAfectados = comandoSQL.executeUpdate();
            comandoSQL.close();
            return RegistrosAfectados;
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
    
    public int eliminarProducto(int id){
        try {
            String sqlEliminar = "DELETE FROM productos WHERE id=?";
            PreparedStatement comandoSQL = _conexion.prepareStatement(sqlEliminar);
            
            comandoSQL.setInt(1, id);
            
            int RegistrosAfectados = comandoSQL.executeUpdate();
            comandoSQL.close();
            return RegistrosAfectados;
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
