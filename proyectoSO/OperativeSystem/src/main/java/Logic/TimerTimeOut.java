/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

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
        process.timerActivated = false;
        
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
            if(!process.timerActivated)
            {
                process.timerActivated = true;
                process.timeLeft = process.timeLeft - seconds;
                process.in_outputTimeLeft -= seconds;
                if (process.timeLeft > 0)
                {
                    Scheduler.AddListo(process);
                }
                
                if (process.priority < 99)
                {
                    process.timesBlocked --;
                    if (process.timesBlocked <= 0)
                    {
                        process.priority ++;
                    }
                }
                Scheduler.RemoveEjecutandose(process);
            }
            timer.cancel();
        }
    }
}