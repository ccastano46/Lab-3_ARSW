/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class Consumer extends Thread{
    
    private Queue<Integer> queue;
    
    
    public Consumer(Queue<Integer> queue){
        this.queue=queue;        
    }

    /**
     * Metodo que pone a dormir el hilo de consumidor
     * @param miliseconds, milisegundos por el cual se va a dormir el hilo
     */

    private void goToSleep(long miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        while (true) {
                if (queue.size() > 0) {
                    int elem=queue.poll();
                    StartProduction.setStock(StartProduction.getStock() - elem);
                    System.out.println("Consumer consumes "+elem);
                }else goToSleep(6000);
                goToSleep(4000);
        }
    }
}
