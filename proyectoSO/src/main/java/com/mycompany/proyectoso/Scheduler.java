/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoso;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

/**
 *
 * @author sanbr
 */
public class Scheduler {

    public static CPU[] cpus = new CPU[30];
    public static ArrayList<Proceso> listos = new ArrayList<>();
    public static ArrayList<Proceso> bloqueados = new ArrayList<>();
    public static ArrayList<Recurso> recursosListos = new ArrayList<>();
    public static ArrayList<Proceso> ejecutandose = new ArrayList<>();

    private static Integer _timeout;

    public static Integer GetTimeOut() {
        return Scheduler._timeout;
    }

    public static void SetTimeOut(Integer newTime) {
        if (newTime > 0) {
            Scheduler._timeout = newTime;
        }
    }

    public static void AddCpu(CPU cpu) {
        int i = 0;
        while (i < Scheduler.cpus.length && Scheduler.cpus[i] != null) {
            i++;
        }
        if (i < 30)
        {
           Scheduler.cpus[i] = cpu; 
        }
    }
    

    public static void AddListo(Proceso process) {
        if (!Scheduler.listos.contains(process)) {
            Scheduler.listos.add(process);
        }
    }

    public static void RemoveListo(Proceso process) {
        if (Scheduler.listos.contains(process)) {
            Scheduler.listos.remove(process);
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

    public static void AddEjecutandose(Proceso process) {
        if (!Scheduler.ejecutandose.contains(process)) {
            int i = 0;
            while (i < Scheduler.cpus.length && Scheduler.cpus[i].process != null) {
                i++;
            }
            if (i < Scheduler.cpus.length) {
                Scheduler.RemoveListo(process);
                Scheduler.cpus[i].process = process;
                new TimerPrivado(Scheduler.GetTimeOut(), process);
                Scheduler.AddEjecutandose(process);
            }
        }
    }

    public static void RemoveEjecutandose(Proceso process) {
        if (Scheduler.ejecutandose.contains(process)) {
            Scheduler.ejecutandose.remove(process);
        }
    }
}
