/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoso;

import java.lang.reflect.Array;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author sanbr
 */
public class Scheduler {

    public static int cpusLeft;
    public static Proceso[][] listasPrioridades = new Proceso[99][99];
    public static ArrayList<Proceso> bloqueados = new ArrayList<>();
    public static ArrayList<Recurso> recursosListos = new ArrayList<>();
    public static ArrayList<Proceso> ejecutandose = new ArrayList<>();
    public static HashMap<Integer, Integer> prioridadEjecutada = new HashMap<Integer, Integer>();

    private static Integer _timeout;
    
    public static Integer GetTimeOut() {
        return Scheduler._timeout;
    }

    public static void SetTimeOut(Integer newTime) {
        if (newTime > 0) {
            Scheduler._timeout = newTime;
        }
    }

    public static void AddListo(Proceso process) {
        int i = 0;
        while (i < Scheduler.listasPrioridades[process.priority].length && Scheduler.listasPrioridades[process.priority][i] != null) {
            i++;
        }
        if (i < 99)
        {
           Scheduler.listasPrioridades[process.priority][i] = process; 
        }
    }
    

    public static void AddBloqueado(Proceso process) {
        if (!Scheduler.bloqueados.contains(process)) {
            Scheduler.bloqueados.add(process);
            Scheduler.ejecutandose.remove(process);
        }
    }

    public static void RemoveBloqueado(Proceso process) {
        if (Scheduler.bloqueados.contains(process)) {
            Scheduler.bloqueados.remove(process);
            Scheduler.AddListo(process);
        }
    }

    public static boolean AddEjecutandose() {
        if (Scheduler.cpusLeft > 0)
        {
            int i = 0;
            int j = 0;
            while (i < Scheduler.listasPrioridades.length) {
                while (j < Scheduler.listasPrioridades[i].length)
                {
                    if (Scheduler.listasPrioridades[i][j] != null)  // Si encuentra posicion no nula, hay proceso
                    {
                        Scheduler.ejecutandose.add(Scheduler.listasPrioridades[i][j]);  // Agrego proceso encontrado a lista de ejecutandose
                        Scheduler.listasPrioridades[i][j] = null;  // Quito el proceso de los listos
                        break;      // Salgo de los while, ya encontré el siguiente listo para ejecutarse
                    }
                    j++;
                }
                i++;
            }
            return true;
        }
        return false;   // No entra al if porque no hay cpu libre, devuelve false
    }

    public static void RemoveEjecutandose(Proceso process) {
        if (Scheduler.ejecutandose.contains(process)) {
            Scheduler.ejecutandose.remove(process);
            Scheduler.cpusLeft++;
        }
    }
}
