/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoso;

/**
 * TimerInterrupcion es un clase timer para contar el tiempo que demora
 * un proceso en ejecucion en recibir una interrupcion. (AKA: Pedir un recurso)
 * @author sanbr
 */
import java.util.Timer;
import java.util.TimerTask;

public class TimerInterrupcion {
    Timer timer;
    public Proceso process;
    public int seconds;
    public TimerInterrupcion(Proceso process) {
        if (process.in_outputTimeLeft >= 0)
        {
            timer = new Timer();
            this.seconds = process.in_outputTimeLeft;
            this.process = process;
            if (process.timeLeft - Scheduler.GetTimeOut() > 0)
            {
                timer.schedule(new StopTask(), this.seconds * 1000);
            }
            
        }
    }


    class StopTask extends TimerTask {
        @Override
        public void run() {
                process.recursoUsado.Usar(process);
                Scheduler.RemoveEjecutandose(process);
                Scheduler.AddBloqueado(process);
           
            timer.cancel();
        }
    }
}
