/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author ME
 */
public class ConexionSQL {
    private static Connection conex = null;
    
    private ConexionSQL(){
        
    }
    
    public static Connection getConexion(){
        try {
            if (conex == null) {
                Class.forName("org.sqlite.JDBC"); 
                conex = DriverManager.getConnection("jdbc:sqlite:supermercado.db");
            }
            
            return conex;
            
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
            return null;
        }
    }
}
