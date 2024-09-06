/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;

import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author hcadavid
 */
public class Producer extends Thread {

    private Queue<Integer> queue;


    private Random rand;
    private final long stockLimit;


    public Producer(Queue<Integer> queue,long stockLimit) {
        this.queue = queue;
        rand = new Random(System.currentTimeMillis());
        this.stockLimit=stockLimit;
    }


    private synchronized void produce(){
        int dataSeed = rand.nextInt(100);
        while (StartProduction.getStock() > stockLimit ||StartProduction.getStock() + dataSeed > stockLimit){
            try {
                wait();
            } catch (InterruptedException e) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        notifyAll();
        System.out.println("Producer added " + dataSeed);
        queue.add(dataSeed);
        StartProduction.setStock(StartProduction.getStock() + dataSeed);
    }

    @Override
    public void run() {
        while (true) {
            produce();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
