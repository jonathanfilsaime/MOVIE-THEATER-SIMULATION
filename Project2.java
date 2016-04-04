/*
 * class project design to teach multi thread 
 * made heavy use of semaphores and queues
 */

import java.io.FileNotFoundException;
/**
 * jxf131930@utdallas.edu
 * @author Jonathan Fils-Aime 
 */

public class Project2 {

    public static String[] movieNames;
    public static int[] numberOfTickets;
    public static boolean[] soldOut;
    public static int count = 0;
    public static int count1 = 0;
    public static int count2 = 0;
    public static int count3 = 0;
    public static int count4 = 0;

    public static boolean[] flag = new boolean[50];

    public static easyQueue pickMovieQueue = new easyQueue();
    public static easyQueue ticketSoldQueue = new easyQueue();
    public static easyQueue tearTicketQueue = new easyQueue();
    public static easyQueue requestToBuyQueue = new easyQueue();
    public static easyQueue orderTakenQueue = new easyQueue();
    public static easyQueue orderRecievedQueue = new easyQueue();

    public static Sem pickMovie = new Sem(1);
    public static Sem boxOfficeCreated = new Sem(0);
    public static Sem buyTicket = new Sem(0);
    public static Sem sellTicket = new Sem(1);
    public static Sem ticketSoldOut = new Sem(1);
    public static Sem ticketSold = new Sem(1);
    public static Sem seeTicketTaker = new Sem(0);
    public static Sem tearTicket = new Sem(1);
    public static Sem concessionStandSem = new Sem(0);
    public static Sem requestToBuy = new Sem(1);
    public static Sem orderTaken = new Sem(1);
    public static Sem orderReceived = new Sem(1);
    public static Sem boxOfficeDone = new Sem(0);
    public static Sem notBuyingSnack = new Sem(1);
    public static Sem enterMovieSem = new Sem(0);
    public static Sem enterMovie = new Sem(1);
    public static Sem totalNumberOfCust = new Sem(0);

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        //read the file 
        String fileName = args[0];
        read r = new read(fileName);
        movieTicket m = new movieTicket(r);
        movieNames = m.getName();
        numberOfTickets = m.getTicketCount();
        soldOut = new boolean[50];

        //box office agent creation
        int j = 2;
        Thread boxThread[] = new Thread[j];

        for (int x = 0; x < 2; x++) {
            boxOffice b = new boxOffice(x);
            boxThread[x] = new Thread(b);
            boxThread[x].start();
        }

        //ticket taker created 
        ticketTaker t = new ticketTaker(1);
        Thread ttThread = new Thread(t);
        ttThread.start();

        //concession stan agent created
        concessionStanAgent s = new concessionStanAgent(1);
        Thread csaThread = new Thread(s);
        csaThread.start();

        //movie theater created
        movieTheater m1 = new movieTheater();
        Thread mThread = new Thread(m1);
        mThread.start();

        int i = 50;
        Thread custThread[] = new Thread[i];

        //customer created
        for (int x = 0; x < 50; x++) {
            customer c = new customer(x);
            custThread[x] = new Thread(c);
            custThread[x].start();
        }

        //box office done 
        boxOfficeDone.stop();
        
        //everyone is accounted for 
        totalNumberOfCust.stop();

        //join customer threads
        for (int x = 0; x < 50; x++) {
            System.out.println("Joined customer " + x);
            custThread[x].join();
        }

        System.exit(0);
    }
}
