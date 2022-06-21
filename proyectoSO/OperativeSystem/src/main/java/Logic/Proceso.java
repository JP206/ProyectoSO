/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

/**
 *
 * @author sanbr
 */
public class Proceso {
    public int priority;
    public int totalTime; // Tiempo que demora en finalizarse el proceso.
    public int in_outputTime; // Tiempo en que llega una interrupcion
    public int in_outputWait; // Tiempo en que usa un recurso
    public int in_outputTimeLeft; // Tiempo restante para pedir un recurso
    public String processName; // Nombre del proceso
    public int timeLeft;    // ULTIMO INTEGER
    public Recurso recursoUsado;
    public boolean isBlocked = false;
    public boolean timerActivated = false;
    
  
    
    public Proceso(int totalTime, int in_outputTime, int in_outputWait, String processName, int priority, Recurso recurso)
    {
        this.priority = priority;
        this.in_outputTime = in_outputTime;
        this.in_outputWait = in_outputWait;
        this.totalTime = totalTime;
        this.processName = processName;
        this.timeLeft = totalTime;
        this.recursoUsado = recurso;
        this.in_outputTimeLeft = in_outputTime;
        Scheduler.AddListo(this);
    }
    
    public void StartTimer(){
        new TimerInterrupcion(this);
        new TimerTimeOut(this);
    }
    
}
