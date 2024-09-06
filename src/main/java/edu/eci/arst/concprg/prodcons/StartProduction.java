/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arst.concprg.prodcons;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StartProduction {

    private static long stock;

    public static long getStock(){
        return stock;
    }

    public static void setStock(long stock){
        StartProduction.stock=stock;
    }
    
    
    public static void main(String[] args) {
        setStock(0);
        Queue<Integer> queue=new LinkedBlockingQueue<>();
        
        
        new Producer(queue,1200).start();
        
        //let the producer create products for 5 seconds (stock).
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(StartProduction.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Consumer(queue).start();
    }
    

}