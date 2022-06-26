/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

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

    public static int cpusLeft = 3;
    public static Proceso[][] listasPrioridades = new Proceso[99][99];
    public static ArrayList<Proceso> bloqueados = new ArrayList<>();
    public static ArrayList<Recurso> recursosListos = new ArrayList<>();
    public static ArrayList<Proceso> ejecutandose = new ArrayList<>();
    public static HashMap<Integer, Integer> prioridadEjecutada = new HashMap<Integer, Integer>();

    private static int _timeout = 15;
    
    public static int GetTimeOut() {
        return Scheduler._timeout;
    }

    public static void SetTimeOut(int newTime) {
        if (newTime > 0) {
            Scheduler._timeout = newTime;
        }
    }

    public static void AddListo(Proceso process) {
        int i = 0;
        int j = 0;
        boolean isAdded = false;
        while (i < Scheduler.listasPrioridades[process.priority].length) {
            while (j < Scheduler.listasPrioridades[process.priority].length) {
                if (Scheduler.listasPrioridades[process.priority][j] == process)
                {
                    isAdded = true;
                }
                j++;
            }
            j = 0;
            i++;
        }
        if (!isAdded)
        {
            i = 0;
            while (i < Scheduler.listasPrioridades[process.priority].length && Scheduler.listasPrioridades[process.priority][i] != null) {
                i++;
            }
            if (i < Scheduler.listasPrioridades[process.priority].length && process.timeLeft > 0)
            {
               Scheduler.listasPrioridades[process.priority][i] = process; 
               Scheduler.RemoveEjecutandose(Scheduler.listasPrioridades[process.priority][i]);
            }
        }
    }
    
    public static void AddBloqueado(Proceso process) {
        if (!Scheduler.bloqueados.contains(process) && process.timeLeft > 0) {
            process.timesBlocked ++;
            if (process.timesBlocked > 3)
            {
                process.priority ++;
            }
            Scheduler.bloqueados.add(process);
            process.isBlocked = true;
            Scheduler.RemoveEjecutandose(process);
        }
    }
    
    
    public static void RemoveBloqueado(Proceso process) {
        if (Scheduler.bloqueados.contains(process)) {
            process.isBlocked = false;
            Scheduler.bloqueados.remove(process);
            Scheduler.AddListo(process);
        }
    }

    public static void AddEjecutandose() {
        boolean outWhile = false; 
        if (Scheduler.cpusLeft > 0)
        {
            int i = 0;
            int j = 0;
            while (i < Scheduler.listasPrioridades.length) {
                j = 0;
                while (j < Scheduler.listasPrioridades[i].length)
                {
                    if (Scheduler.listasPrioridades[i][j] != null)  // Si encuentra posicion no nula, hay proceso
                    {
                        if (!Scheduler.ejecutandose.contains(Scheduler.listasPrioridades[i][j]) && Scheduler.listasPrioridades[i][j].timeLeft > 0)
                        {
                            Scheduler.listasPrioridades[i][j].isBlocked = false;
                            Scheduler.ejecutandose.add(Scheduler.listasPrioridades[i][j]);  // Agrego proceso encontrado a lista de ejecutandose
                            Scheduler.cpusLeft--;
                            Scheduler.listasPrioridades[i][j].StartTimer();
                            Scheduler.listasPrioridades[i][j] = null;  // Quito el proceso de los listos
                            //System.out.println(Arrays.toString(Scheduler.listasPrioridades[i]));
                            outWhile = true;     // Salgo de los while, ya encontré el siguiente listo para ejecutarse
                            break;
                        }
                    }
                    j++;
                }
                i++;
                if(outWhile == true){
                    break;
                }
            }
        }
    }

    public static void RemoveEjecutandose(Proceso process) {
        if (Scheduler.ejecutandose.contains(process)) {
            Scheduler.cpusLeft++;
            Scheduler.ejecutandose.remove(process);
            if (process.timeLeft > 0 && process.isBlocked == false){
                Scheduler.AddListo(process);    // Si queda tiempo todavía cuando se remueve, es por timeout y vuelve a listos
            }
        }
    }
}
