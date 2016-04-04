/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Random;



/**
 *
 * @author jonathan
 */
public class customer implements Runnable {
    
    private final int i;
    private int moviePick;
   
    customer(int i){
        this.i = i;
    }
    
    @Override
    public void run() {
        moviePick = pickMovie();
        
        
        // once created the customer puts himself in line to see the box Office and picks a movie
        Project2.pickMovie.stop();
        Project2.pickMovieQueue.enqueue(this);
        Project2.pickMovie.go();
        Project2.buyTicket.go();
        
        System.out.println("customer " + i +" created, buying ticket to  " + Project2.movieNames[moviePick]);  
    }
    
    public int getMoviePick(){
        return moviePick;
    }
    
    public int pickMovie(){
        int max = 4;
        int min = 0;      
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    public int getID(){
        return this.i;
    }
    
    
    
    
}
