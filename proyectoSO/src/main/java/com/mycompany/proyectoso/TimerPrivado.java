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

public class TimerPrivado {
    Timer timer;
    public Proceso process;

    public TimerPrivado(int seconds, Proceso process) {
        timer = new Timer();
        this.process = process;
        timer.schedule(new StopTask(), seconds * 1000);
    }


    class StopTask extends TimerTask {
        @Override
        public void run() {
            Scheduler.RemoveEjecutandose(process);
            timer.cancel();
        }
    }
}