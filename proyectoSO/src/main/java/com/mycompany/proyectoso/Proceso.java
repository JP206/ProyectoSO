/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoso;

/**
 *
 * @author sanbr
 */
public class Proceso {
    public Integer priority;
    public Integer totalTime; // Tiempo que demora en finalizarse el proceso.
    public Integer in_outputTime; // Tiempo en que llega una interrupcion
    public Integer in_outputWait; // Tiempo en que usa un recurso
    public String processName; // Nombre del proceso
  
    
    public Proceso(Integer totalTime, Integer in_outputTime, Integer in_outputWait, String processName, Integer priority)
    {
        this.priority = priority;
        this.in_outputTime = in_outputTime;
        this.in_outputWait = in_outputWait;
        this.totalTime = totalTime;
        this.processName = processName;
        Scheduler.AddListo(this);
    }
}
