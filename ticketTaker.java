/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class ticketTaker implements Runnable {
 
    private final int i;
   
    ticketTaker(int i){
        this.i = i;
    }
    
    @Override
    public void run() {
        System.out.println("Ticket Taker " + i +" created.");
        
        while(true){ 
            
            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(boxOffice.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //serve the cusomter who just left teh box office queue
            Project2.seeTicketTaker.stop();
            customer eric = (customer)Project2.ticketSoldQueue.dequeue();
            System.out.println("Ticket taken from customer " + eric.getID());
            Project2.tearTicket.stop();
            
            //guide the custoemr to the concession stand line
            Project2.tearTicketQueue.enqueue(eric);
            Project2.concessionStandSem.go();
            Project2.tearTicket.go();
       }
    }
}
