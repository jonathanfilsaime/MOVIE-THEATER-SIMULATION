/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jonathan
 */
public class read {
    private ArrayList<String> movieNameArr = new ArrayList<String>();
    private ArrayList<Integer> numberOfTicketArr = new ArrayList<Integer>();
    
    //constructor
    public read(String fileName) throws FileNotFoundException{

        int number;
        String name;
        
        String line = ""; 
        FileReader fileReader = new FileReader(fileName);
        BufferedReader buffer = new BufferedReader(fileReader);
        
        //find the patter for the number of tickets 
        String pattern = "(\\s\\d+)";
        Pattern r = Pattern.compile(pattern);
        
      
        
        try {
            while((line = buffer.readLine()) != null){
                Matcher f = r.matcher(line);
                
                if(f.find()){
                    //parse the number of tickets
                    number = Integer.parseInt(f.group(0).replaceFirst("\\s", ""));
                    numberOfTicketArr.add(number);
                    }
                
                //parse the name of the movie
                name = line.replaceAll("(\\s\\d+)", "");
                movieNameArr.add(name);
                }
            
            } catch (IOException ex) {
            Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getMovieName(){
        return movieNameArr;
    }
    
    public ArrayList<Integer> getNumberOfTicket(){
        return numberOfTicketArr;
    }
    
}
