/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoso;

/**
 * Timer Salir es una clase que timer cuya funcion es contar el tiempo que un
 * recurso es usado por un proceso.
 * @author sanbr
 */
import java.util.Timer;
import java.util.TimerTask;

public class TimerSalir {
    Timer timer;
    public Proceso process;
    public int seconds;
    public TimerSalir(Proceso process) {
        timer = new Timer();
        this.seconds = process.in_outputTime;
        this.process = process;
        timer.schedule(new StopTask(), this.seconds * 1000);
    }


    class StopTask extends TimerTask {
        @Override
        public void run() {
            process.isBlocked = false;
            process.in_outputTimeLeft = process.in_outputTime;
            Scheduler.RemoveBloqueado(process);
            Scheduler.AddListo(process);
            timer.cancel();
        }
    }
}
