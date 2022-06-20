/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoso;

/**
 *
 * @author sanbr
 */
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer timeout es una clase Timer para cortar la ejecucion de un
 * programa luego de que el quantum de tiempo ha pasado.
 * @author juanp
 */

public class TimerTimeOut {
    Timer timer;
    public Proceso process;
    public int seconds;
    public TimerTimeOut(Proceso process) {
        timer = new Timer();
        
        if(process.timeLeft < Scheduler.GetTimeOut())
        {
            this.seconds = process.timeLeft;
        }
        else
        {
           this.seconds = Scheduler.GetTimeOut(); 
        }
        this.process = process;
        timer.schedule(new StopTask(), this.seconds * 1000);
    }


    class StopTask extends TimerTask {
        @Override
        public void run() {
                Scheduler.RemoveEjecutandose(process);
                process.timeLeft = process.timeLeft - seconds;
                process.in_outputTimeLeft -= seconds;
                if (process.timeLeft > 0)
                {
                    Scheduler.AddListo(process);
                } 
                else
                {
                    int i = 0;
                    int j = 0;
                    while (i < Scheduler.listasPrioridades[process.priority].length) {
                        j = 0;
                        while (j < Scheduler.listasPrioridades[process.priority].length) {
                            if (Scheduler.listasPrioridades[process.priority][j] == process)
                            {
                                Scheduler.listasPrioridades[process.priority][j] = null;
                                break;
                            }
                            j++;
                        }
                        i++;
                    }
                    System.out.println("SE FINALIZO EL PROCESO");
                }
           
            
            timer.cancel();
        }
    }
}