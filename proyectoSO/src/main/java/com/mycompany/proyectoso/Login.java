package com.mycompany.proyectoso;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sanbr
 */
public class Login {
    
    public boolean esUsuario(String nombre, String contraseña){
        ManejadorArchivosGenerico manejador = new ManejadorArchivosGenerico();
        String[] usuarios = manejador.leerArchivo("src\\usuarios.txt");     // Guardo usuarios y contraseñas en array de strings. Usuarios están en txt llamado usuarios en la carpeta src, un usuario:contraseña por linea
        String[] usuariosSplit;
        int i = 0;
        boolean nombreValido = false;
        boolean contraseñaValida = false;
        boolean resultado = nombreValido && contraseñaValida;   // El resultado es la conjuncion de ambas condiciones
        while (i < usuarios.length && !resultado){      // Si el resultado es verdadero, ya corta el while
            usuariosSplit = usuarios[i].split(":");
            if (usuariosSplit[0].equals(nombre)){
                nombreValido = true;        
            }
            if (usuariosSplit[1].equals(contraseña)){
                contraseñaValida = true;
            }
            resultado = nombreValido && contraseñaValida;
            i++;
        }
        return resultado;
    }
    
    public void agregarUsuario(String nombre, String contraseña){
        ManejadorArchivosGenerico manejador = new ManejadorArchivosGenerico();
        String usuario = nombre + ":" + contraseña;
        String[] usuarios = {usuario};    // Creo arreglo de strings con usuario y contraseña para escribir en el txt
        manejador.escribirArchivo("src\\usuarios.txt", usuarios);    // Escribo el txt "usuarios" en la carpeta src del proyecto con el usuario nuevo
    }   
}
