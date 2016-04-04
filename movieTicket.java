/*
 * this class maintins the number of tickets for all the movies 
 */


/**
 *
 * @author jonathan
 */
public class movieTicket {
    private int[] ticketCount;
    private String[] Name;
    
    public movieTicket(read r){
        this.ticketCount = new int[r.getNumberOfTicket().size()];
        this.Name = new String[r.getMovieName().size()];
        
        for(int i = 0; i < r.getNumberOfTicket().size(); i++ ){
            this.ticketCount[i] = r.getNumberOfTicket().get(i);
            this.Name[i] = r.getMovieName().get(i);
        }
    }
    
    public int[] getTicketCount(){
        return ticketCount;
    }
    
    public String[] getName(){
        return Name;
    }
    
}
