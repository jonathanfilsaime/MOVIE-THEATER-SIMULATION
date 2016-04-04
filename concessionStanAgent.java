/**
 *
 * @author jonathan
 */


import java.util.logging.Level;
import java.util.logging.Logger;

public class concessionStanAgent implements Runnable {
    
    String[] cs = {"Popcorn","Soda","Popcorn and Soda"};

    private final int i;
   
    concessionStanAgent(int i){
        this.i = i;
    }
    
    @Override
    public void run() {
        System.out.println("concession Stan Agent " + i +" created.");
        
        
        while(true){
        
        //serve the next customer 
        Project2.concessionStandSem.stop();
        customer james = (customer)Project2.tearTicketQueue.dequeue();
        
        //generate a random number
        int rand = (int) (Math.random() * 10);
        
        //half of the customer wants to buy snacks
        if(james.getID() % 2 == 0){
        
            //they randamly pick wheather they want soda, pop corn or both
            int food = ((james.getID()+ rand) % 3);
            System.out.println("Customer " +james.getID() + " in line to buy " + cs[food]);
        
            //customer are in the queue to be served
            Project2.requestToBuy.stop();
            Project2.requestToBuyQueue.enqueue(james);
            Project2.requestToBuy.go();
        
            //customer's order is taken
            Project2.orderTaken.stop();
            Project2.requestToBuyQueue.dequeue();
            System.out.println("Order for " + cs[food] + " taken from customer " +james.getID() );
            Project2.orderTakenQueue.enqueue(james);
            Project2.orderTaken.go();
        
        try {
                Thread.sleep(180);
            } catch (InterruptedException ex) {
                Logger.getLogger(boxOffice.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            //customer received his order and moved to the line to enter the theater
            Project2.orderReceived.stop();
            Project2.orderTakenQueue.dequeue();
            System.out.println("Customer " + james.getID() + " recieves " + cs[food]);
            Project2.orderRecievedQueue.enqueue(james);
            Project2.enterMovieSem.go();
            Project2.orderReceived.go();
        
        }else{
            
            //these customers decided not to buy snacks and moved to the line to enter the theater
            Project2.notBuyingSnack.stop();
            System.out.println("Customer " + james.getID() +" is smart and he decided not to buy overpriced food ");
            Project2.orderRecievedQueue.enqueue(james);
            Project2.enterMovieSem.go();
            Project2.notBuyingSnack.go();
        }
        
        }
    }
    
    
}
