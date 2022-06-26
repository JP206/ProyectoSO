/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package Logic;

/**
 *
 * @author Luana
 */
public class ProyectoSO {

    public static void main(String[] args) {
        Recurso recurso1 = new Recurso("Impresora");
        
        Proceso process5 = new Proceso(100, 19, 24, "PROCESO1", 1, recurso1);
        Proceso process4 = new Proceso(100, 18, 23, "PROCESO2", 2, recurso1);
        Proceso process3 = new Proceso(100, 17, 22, "PROCESO3", 3, recurso1);
        Proceso process2 = new Proceso(100, 16, 21, "PROCESO4", 4, recurso1);
        Proceso process1 = new Proceso(100, 15, 20, "PROCESO5", 5, recurso1);
        Scheduler.cpusLeft = 10;
   
        System.out.println(Scheduler.ejecutandose.size());
        int ejecutandoseSize = Scheduler.ejecutandose.size();
        int bloqueadosSize = Scheduler.bloqueados.size();
        while(true)
        {
           Scheduler.AddEjecutandose();
           if(ejecutandoseSize != Scheduler.ejecutandose.size())
           {
               System.out.println("----------------------------");
               int i = 0;
               ejecutandoseSize = Scheduler.ejecutandose.size();
               while (i < ejecutandoseSize && Scheduler.ejecutandose.size() == ejecutandoseSize) {
                   ejecutandoseSize = Scheduler.ejecutandose.size();
                   System.out.println(Scheduler.ejecutandose.get(i).processName);
                   i++;
               }  
           }
           if(bloqueadosSize != Scheduler.bloqueados.size())
           {
               System.out.println("--------------BLOQUEADOS---------------");
               bloqueadosSize = Scheduler.bloqueados.size();
               int i = 0;
               while (i < bloqueadosSize) {
                   System.out.println(Scheduler.bloqueados.get(i).processName);
                   i++;
               }
           }
        }
    }
}
