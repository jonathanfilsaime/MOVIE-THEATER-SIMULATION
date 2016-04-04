/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author jonathan
 */
public class movieTheater implements Runnable {

    @Override
    public void run() {
        System.out.println("Movie Theater is Open!!!");
        
        
        
        while(true){
            
        //customer enters the movie theater
        Project2.enterMovie.stop();
        Project2.enterMovieSem.stop();
        customer chris = (customer) Project2.orderRecievedQueue.dequeue();
        System.out.println("Customer " + chris.getID() + " enters theater to see " + Project2.movieNames[chris.getMoviePick()]);
        Project2.enterMovie.go();

        //counts how moany customer enter the theater
        Project2.count4++;
        
        //number of custoemr who left and number of customer who entered should equal 50
        //don't exit the program until all the customers are accounted for 
        if(Project2.count4 + Project2.count3 == 50)
        {
            System.out.println("number of customer who entered the theater : " + Project2.count2 + " number of customer who left the theater : "+ Project2.count3 + " total count " + (Project2.count2 + Project2.count3));
            Project2.totalNumberOfCust.go();
        }

                
        }
    }
    
}
