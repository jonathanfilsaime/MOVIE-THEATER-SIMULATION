/**
 *
 * @author jonathan
 */

import java.util.logging.Level;
import java.util.logging.Logger;

public class boxOffice implements Runnable{

    private final int i;
    
   
    boxOffice(int i){
        this.i = i;
    }
    
    @Override
    public void run() {
        
        
        System.out.println("box Office agent " + i +" created.");
        Project2.count++;

        if(Project2.count == 2){
            Project2.boxOfficeCreated.go();
            Project2.boxOfficeCreated.go();
        }
        //don't eneter the while loop until both of them are created 
        Project2.boxOfficeCreated.stop();
      
        
        while(true){
            
            //find out which customer and which tickets the customer wants to buy
            Project2.buyTicket.stop();
            Project2.sellTicket.stop();
            customer temp = (customer) Project2.pickMovieQueue.dequeue();
            Project2.sellTicket.go(); 
            System.out.println("box office agent " + i + " serving customer  " + temp.getID());
            
            try {
                Thread.sleep(90);
            } catch (InterruptedException ex) {
                Logger.getLogger(boxOffice.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //if tickets are still available 
            if(Project2.numberOfTickets[temp.getMoviePick()] > 0){
                
                //decrement the number of ticket for this movie
                Project2.numberOfTickets[temp.getMoviePick()]--;
            
                System.out.println("box office agent " + i + " sold ticket for " + Project2.movieNames[temp.getMoviePick()] +
                    " to customer " + temp.getID());
                
                //sell ticket to the customer and put guide to the ticker taker's queue
                Project2.ticketSold.stop();
                Project2.ticketSoldQueue.enqueue(temp);
                System.out.println("Customer " + temp.getID() + " in line to see ticket taker" );
                Project2.seeTicketTaker.go();
                Project2.ticketSold.go();
                Project2.count2++;
                
            }else{
                
                //no more tickets left for the movie 
                System.out.println(Project2.movieNames[temp.getMoviePick()] + " IS SOLD OUT!");
                
                //customer left
                Project2.ticketSoldOut.stop();
                System.out.println("customer "+ temp.getID() +" left");
                Project2.ticketSoldOut.go();
                Project2.count3++;
                
            }
            
            //number of total customer who visited the box office 
            Project2.count1++;
            if(Project2.count1 >= 50){
                Project2.boxOfficeDone.go();
            }
        
        }
    }
}
