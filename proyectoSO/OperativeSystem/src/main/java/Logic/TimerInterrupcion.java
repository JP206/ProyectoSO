/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

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
        if (process.in_outputTimeLeft > 0 )
        {
            process.timerActivated = false;
            timer = new Timer();
            this.seconds = process.in_outputTimeLeft;
            this.process = process;
            timer.schedule(new StopTask(), this.seconds * 1000);
        }
    }


    class StopTask extends TimerTask {
        @Override
        public void run() {
            if(!process.timerActivated)
            {
                process.in_outputTimeLeft = process.in_outputTime;
                process.timerActivated = true;
                process.recursoUsado.Usar(process);
                Scheduler.RemoveEjecutandose(process);
                Scheduler.AddBloqueado(process);
                if (process.timeLeft - seconds < 0)
                {
                    process.timeLeft = 0;
                }
                else
                {
                    process.timeLeft -= seconds;
                }
            }
           
            timer.cancel();
        }
    }
}
