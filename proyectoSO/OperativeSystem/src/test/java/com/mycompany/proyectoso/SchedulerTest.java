package com.mycompany.proyectoso;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import Logic.Recurso;
import Logic.Proceso;
import Logic.Scheduler;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author sanbr
 */
public class SchedulerTest {
    /**
     * Test of SetTimeOut method, of class Scheduler.
     */
    @Test
    public void testSingleProcessRoute() {
        Recurso recurso = new Recurso();
        Scheduler.cpusLeft = 5;
        Proceso process = new Proceso(3, 2, 2, "PROCESO1", 1, recurso);
        assertEquals(Scheduler.listasPrioridades[process.priority][0], process);
        Scheduler.AddEjecutandose();
        assertEquals(Scheduler.ejecutandose.contains(process), true);
        assertEquals(Scheduler.cpusLeft, 4);
        assertEquals(Scheduler.listasPrioridades[process.priority][0], null);
        while(Scheduler.bloqueados.size() == 0)
        {
            System.out.println("Primer while");
        }
        assertEquals(Scheduler.bloqueados.contains(process), true);
        assertEquals(recurso.isBlocked, true);
        assertEquals(process.isBlocked, true);
        while(Scheduler.listasPrioridades[process.priority][0] != process)
        {
            System.out.println("Segundo while");
        }
        assertEquals(Scheduler.listasPrioridades[process.priority][0], process);
        assertEquals(2, process.timeLeft);
        Scheduler.AddEjecutandose();
        while(Scheduler.ejecutandose.contains(process))
        {
            System.out.println("Tercero while");
        }
        Scheduler.AddEjecutandose();
        while(Scheduler.ejecutandose.contains(process))
        {
            System.out.println("Cuarto while");
        }
        while(Scheduler.ejecutandose.contains(process))
        {
            System.out.println("Cuarto while");
        }
        assertEquals(Scheduler.ejecutandose.contains(process), false);
        assertEquals(Scheduler.bloqueados.contains(process), false);
        System.out.println(Arrays.toString(Scheduler.listasPrioridades[process.priority]));
        assertEquals(Scheduler.listasPrioridades[process.priority][0], null);
    }
}
