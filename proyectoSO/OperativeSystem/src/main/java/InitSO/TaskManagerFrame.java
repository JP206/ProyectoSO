/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InitSO;

import Logic.Proceso;
import Logic.Recurso;
import Logic.Scheduler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Kevin
 */
public class TaskManagerFrame extends javax.swing.JFrame {

    String[] columns = {
        "Name",
        "Priority",
        "Output Time",
        "Output Wait",
        "Total Time",
        "Resource"
    };
    DefaultTableModel tablaTask = new DefaultTableModel(null, columns);
    DefaultTableModel tablaTask1 = new DefaultTableModel(null, columns);
    DefaultTableModel tablaTask2 = new DefaultTableModel(null, columns);
    public static Boolean runtime = false;
    /**
     * Creates new form TaskManagerFrame
     */
    public TaskManagerFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        tblProcess.setModel(tablaTask);
        tblProcess1.setModel(tablaTask1);
        tblProcess2.setModel(tablaTask2);
        Recurso recurso1 = new Recurso();
       Proceso process5 = new Proceso(10000000, 10, 6, "PROCESO1", 1, recurso1);
        Proceso process4 = new Proceso(10000000, 10, 6, "PROCESO2", 2, recurso1);
        Proceso process3 = new Proceso(10000000, 10, 6, "PROCESO3", 3, recurso1);
        Proceso process2 = new Proceso(10000000, 10, 6, "PROCESO4", 4, recurso1);
        Proceso process1 = new Proceso(10000000, 10, 6, "PROCESO5", 5, recurso1);
        Thread hilo = new Thread(runnable);
        hilo.start();
    }

    private void actualizartablaPrioridades() {
        int rowscount = tblProcess.getRowCount();
        for (int i = rowscount - 1; i >= 0; i--) {
            tablaTask.removeRow(i);
        }
        Proceso[][] filas = Scheduler.listasPrioridades;

        int i = 0;
        int j = 0;
        while (i < filas.length) {
            j = 0;
            while (j < filas[i].length) {
                if (filas[i][j] != null) {
                    Vector<String> Name = new Vector<>(Arrays.asList(filas[i][j].processName));
                    Vector<Integer> Priority = new Vector<>(Arrays.asList(filas[i][j].priority));
                    Vector<Integer> Output_Time = new Vector<>(Arrays.asList(filas[i][j].in_outputTime));
                    Vector<Integer> Output_Wait = new Vector<>(Arrays.asList(filas[i][j].in_outputWait));
                    Vector<Integer> Total_Time = new Vector<>(Arrays.asList(filas[i][j].totalTime));
                    Vector<Recurso> Resource = new Vector<>(Arrays.asList(filas[i][j].recursoUsado));

                    Vector<Object> row = new Vector<Object>();
                    row.addElement(Name.get(0));
                    row.addElement(Priority.get(0));
                    row.addElement(Output_Time.get(0));
                    row.addElement(Output_Wait.get(0));
                    row.addElement(Total_Time.get(0));
                    row.addElement(Resource.get(0));

                    tablaTask.addRow(row);
                }

                j++;
            }
            i++;
        }
    }

    private void actualizartablaProcesosBloq() {
        int rowscount = tblProcess2.getRowCount();
        for (int i = rowscount - 1; i >= 0; i--) {
            tablaTask2.removeRow(i);
        }
        ArrayList<Proceso> process = Scheduler.bloqueados;

        for (int i = 0; i < process.size(); i++) {
            if (process.get(i) != null)
            {
                Vector<String> Name = new Vector<>(Arrays.asList(process.get(i).processName));
                Vector<Integer> Priority = new Vector<>(Arrays.asList(process.get(i).priority));
                Vector<Integer> Output_Time = new Vector<>(Arrays.asList(process.get(i).in_outputTime));
                Vector<Integer> Output_Wait = new Vector<>(Arrays.asList(process.get(i).in_outputWait));
                Vector<Integer> Total_Time = new Vector<>(Arrays.asList(process.get(i).totalTime));
                Vector<Recurso> Resource = new Vector<>(Arrays.asList(process.get(i).recursoUsado));

                Vector<Object> row = new Vector<Object>();
                row.addElement(Name.get(0));
                row.addElement(Priority.get(0));
                row.addElement(Output_Time.get(0));
                row.addElement(Output_Wait.get(0));
                row.addElement(Total_Time.get(0));
                row.addElement(Resource.get(0));
                tablaTask2.addRow(row);
            }
        }
    }
    
    private void actualizartablaProcesosEjecutandose() {
        int rowscount = tblProcess1.getRowCount();
        for (int i = rowscount - 1; i >= 0; i--) {
            tablaTask1.removeRow(i);
        }
        
        ArrayList<Proceso> process = Scheduler.ejecutandose;

        for (int i = 0; i < process.size(); i++) {
            if (process.get(i) != null)
            {
                Vector<String> Name = new Vector<>(Arrays.asList(process.get(i).processName));
                Vector<Integer> Priority = new Vector<>(Arrays.asList(process.get(i).priority));
                Vector<Integer> Output_Time = new Vector<>(Arrays.asList(process.get(i).in_outputTime));
                Vector<Integer> Output_Wait = new Vector<>(Arrays.asList(process.get(i).in_outputWait));
                Vector<Integer> Total_Time = new Vector<>(Arrays.asList(process.get(i).totalTime));
                Vector<Recurso> Resource = new Vector<>(Arrays.asList(process.get(i).recursoUsado));

                Vector<Object> row = new Vector<Object>();
                row.addElement(Name.get(0));
                row.addElement(Priority.get(0));
                row.addElement(Output_Time.get(0));
                row.addElement(Output_Wait.get(0));
                row.addElement(Total_Time.get(0));
                row.addElement(Resource.get(0));
                tablaTask1.addRow(row);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblProcess = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        checkEjecutandose = new javax.swing.JCheckBox();
        checkListo = new javax.swing.JCheckBox();
        CheckBloqueado = new javax.swing.JCheckBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblProcess1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProcess2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TaskManager");
        setLocationByPlatform(true);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProcess.setFont(new java.awt.Font("Bauhaus Light", 0, 18)); // NOI18N
        tblProcess.setForeground(new java.awt.Color(51, 51, 51));
        tblProcess.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Priority", "Output Time", "Output Wait", "Total Time", "Resource"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProcess.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblProcess.setFocusable(false);
        tblProcess.setGridColor(new java.awt.Color(255, 255, 255));
        tblProcess.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblProcess.setOpaque(false);
        tblProcess.setRequestFocusEnabled(false);
        tblProcess.setRowSelectionAllowed(false);
        tblProcess.setSelectionBackground(new java.awt.Color(0, 51, 102));
        tblProcess.setSelectionForeground(new java.awt.Color(204, 204, 204));
        tblProcess.setShowGrid(false);
        tblProcess.getTableHeader().setResizingAllowed(false);
        tblProcess.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblProcess);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 910, 150));

        jLabel8.setBackground(new java.awt.Color(153, 153, 153));
        jLabel8.setFont(new java.awt.Font("Bauhaus", 1, 60)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Task Manager");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1100, 80));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 830, 1110, 900));

        checkEjecutandose.setText("Ejecutandose");
        checkEjecutandose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkEjecutandoseMouseClicked(evt);
            }
        });
        checkEjecutandose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkEjecutandoseActionPerformed(evt);
            }
        });
        getContentPane().add(checkEjecutandose, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 640, 110, 20));

        checkListo.setText("Listos");
        checkListo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkListoActionPerformed(evt);
            }
        });
        getContentPane().add(checkListo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 80, -1));

        CheckBloqueado.setText("Bloqueados");
        CheckBloqueado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBloqueadoActionPerformed(evt);
            }
        });
        getContentPane().add(CheckBloqueado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 80, -1));

        tblProcess1.setFont(new java.awt.Font("Bauhaus Light", 0, 18)); // NOI18N
        tblProcess1.setForeground(new java.awt.Color(51, 51, 51));
        tblProcess1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Priority", "Output Time", "Output Wait", "Total Time", "Resource"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProcess1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblProcess1.setFocusable(false);
        tblProcess1.setGridColor(new java.awt.Color(255, 255, 255));
        tblProcess1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblProcess1.setOpaque(false);
        tblProcess1.setRequestFocusEnabled(false);
        tblProcess1.setRowSelectionAllowed(false);
        tblProcess1.setSelectionBackground(new java.awt.Color(0, 51, 102));
        tblProcess1.setSelectionForeground(new java.awt.Color(204, 204, 204));
        tblProcess1.setShowGrid(false);
        tblProcess1.getTableHeader().setResizingAllowed(false);
        tblProcess1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblProcess1);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 670, 910, 150));

        tblProcess2.setFont(new java.awt.Font("Bauhaus Light", 0, 18)); // NOI18N
        tblProcess2.setForeground(new java.awt.Color(51, 51, 51));
        tblProcess2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Priority", "Output Time", "Output Wait", "Total Time", "Resource"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProcess2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblProcess2.setFocusable(false);
        tblProcess2.setGridColor(new java.awt.Color(255, 255, 255));
        tblProcess2.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblProcess2.setOpaque(false);
        tblProcess2.setRequestFocusEnabled(false);
        tblProcess2.setRowSelectionAllowed(false);
        tblProcess2.setSelectionBackground(new java.awt.Color(0, 51, 102));
        tblProcess2.setSelectionForeground(new java.awt.Color(204, 204, 204));
        tblProcess2.setShowGrid(false);
        tblProcess2.getTableHeader().setResizingAllowed(false);
        tblProcess2.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblProcess2);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, 910, 150));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved

    }//GEN-LAST:event_formMouseMoved

    private void checkEjecutandoseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkEjecutandoseActionPerformed
        Boolean checkEjec = false;
        Boolean checkBloq = false;
        Boolean checkListos = false;

        checkEjec = checkEjecutandose.isSelected();
        checkBloq = CheckBloqueado.isSelected();
        checkListos = checkListo.isSelected();

        if (checkEjec == true) {
            CheckBloqueado.setEnabled(false);
            checkListo.setEnabled(false);
            actualizartablaProcesosEjecutandose();
        } else {
            int rowscount = tblProcess.getRowCount();
            for (int i = rowscount - 1; i >= 0; i--) {
                tablaTask.removeRow(i);
            }
            CheckBloqueado.setEnabled(true);
            checkListo.setEnabled(true);
        }

    }//GEN-LAST:event_checkEjecutandoseActionPerformed

    private void checkListoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkListoActionPerformed
        Boolean checkEjec = false;
        Boolean checkBloq = false;
        Boolean checkListos = false;

        checkEjec = checkEjecutandose.isSelected();
        checkBloq = CheckBloqueado.isSelected();
        checkListos = checkListo.isSelected();

        if (checkListos == true) {
            checkEjecutandose.setEnabled(false);
            CheckBloqueado.setEnabled(false);
            actualizartablaPrioridades();
        } else {
            int rowscount = tblProcess.getRowCount();
            for (int i = rowscount - 1; i >= 0; i--) {
                tablaTask.removeRow(i);
            }
            checkEjecutandose.setEnabled(true);
            CheckBloqueado.setEnabled(true);
        }
    }//GEN-LAST:event_checkListoActionPerformed

    private void CheckBloqueadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBloqueadoActionPerformed
        Boolean checkEjec = false;
        Boolean checkBloq = false;
        Boolean checkListos = false;

        checkEjec = checkEjecutandose.isSelected();
        checkBloq = CheckBloqueado.isSelected();
        checkListos = checkListo.isSelected();

        if (checkBloq == true) {
            checkEjecutandose.setEnabled(false);
            checkListo.setEnabled(false);
            actualizartablaProcesosBloq();
        } else {
            int rowscount = tblProcess.getRowCount();
            for (int i = rowscount - 1; i >= 0; i--) {
                tablaTask.removeRow(i);
            }
            checkEjecutandose.setEnabled(true);
            checkListo.setEnabled(true);
        }
    }//GEN-LAST:event_CheckBloqueadoActionPerformed
    
    Runnable runnable = new Runnable() {
  @Override
    public void run() {
      // Esto se ejecuta en segundo plano una única vez
      while (true) {
        // Pero usamos un truco y hacemos un ciclo infinito
        try {
          // En él, hacemos que el hilo duerma
          Thread.sleep(100);
          // Y después realizamos las operaciones
          actualizartablaProcesosEjecutandose();
          actualizartablaProcesosBloq();
          actualizartablaPrioridades();
          
          // Así, se da la impresión de que se ejecuta cada cierto tiempo
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  };

    private void checkEjecutandoseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkEjecutandoseMouseClicked

    }//GEN-LAST:event_checkEjecutandoseMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TaskManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TaskManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TaskManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TaskManagerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TaskManagerFrame().setVisible(true);
            }
        });
        while (runtime == false){
            Scheduler.AddEjecutandose();         
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckBloqueado;
    private javax.swing.JCheckBox checkEjecutandose;
    private javax.swing.JCheckBox checkListo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tblProcess;
    private javax.swing.JTable tblProcess1;
    private javax.swing.JTable tblProcess2;
    // End of variables declaration//GEN-END:variables
}
