/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.proyectoso;

/**
 *
 * @author Luana
 */
public class ProyectoSO {

    public static void main(String[] args) {
        Recurso recurso1 = new Recurso();
        
        Proceso process5 = new Proceso(10, 2, 2, "PROCESO1", 1, recurso1);
        Proceso process4 = new Proceso(10, 2, 2, "PROCESO2", 2, recurso1);
        Proceso process3 = new Proceso(10, 2, 2, "PROCESO3", 3, recurso1);
        Proceso process2 = new Proceso(10, 2, 2, "PROCESO4", 4, recurso1);
        Proceso process1 = new Proceso(10, 2, 2, "PROCESO5", 5, recurso1);
        
        
        
        for(Proceso[] aux : Scheduler.listasPrioridades)
        {
            for(Proceso aux2 : aux)
            {
                if (aux2 != null){
                    System.out.println(aux2.processName);
                }
            }
        }
        
        Scheduler.AddEjecutandose();
        Scheduler.AddEjecutandose();
        Scheduler.AddEjecutandose();
        Scheduler.AddEjecutandose();
        Scheduler.AddEjecutandose();
        System.out.println(Scheduler.ejecutandose.size());
        while(recurso1.isBlocked)
        {
            
        }
        Scheduler.AddEjecutandose();
        Scheduler.AddEjecutandose();
        Scheduler.AddEjecutandose();
        Scheduler.AddEjecutandose();
        Scheduler.AddEjecutandose();
        Scheduler.AddEjecutandose();
        Scheduler.AddEjecutandose();
        System.out.println("---------------------------------");
        for(Proceso aux : Scheduler.ejecutandose)
        {
            System.out.println(aux.processName);
        }
        System.out.println("---------------------------------");
    }
}
