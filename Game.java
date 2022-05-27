
// *** This game contains the body of the video poker game, 
// with methods that build the main game.
// There are also methods to sort the cards, create the types of hands and evaluate them.   
// methods to sort cards, check the hands of the player and print them. 


import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.util.Collections;
import java.lang.reflect.Array;
import java.lang.Integer;

public class Game {
	
    private Player p;
    private Deck cards;
    private ArrayList<Card> hand;
    private Scanner input;
    
    // this method instantiates the variables and builds the characters
    // for the checkHand and command-line argument. 
    public Game(String[] testHand){
        
        p = new Player();
        cards = new Deck();
        hand = new ArrayList<Card>();
        int i=0;
        
        while(i<5){
            int rank = Integer.parseInt(testHand[i].substring(1));
            if(rank==0){ 
                rank = 13;
            }            
            if(testHand[i].substring(0,1).equals("c")){
                Card c = new Card(1,rank);
                hand.add(c);
            }
            if(testHand[i].substring(0,1).equals("d")){
                Card c = new Card(2,rank);
                hand.add(c);
            }
            if(testHand[i].substring(0,1).equals("h")){
                Card c = new Card(3,rank);
                hand.add(c);
            }
            if(testHand[i].substring(0,1).equals("s")){
                Card c = new Card(4,rank);
                hand.add(c);
            }
            i++;
        }
        
        Comparator<Card> c = (card1, card2) -> card1.compareTo(card2);
        hand.sort(c);
        System.out.println("Here is your hand: ");
        for(int a=0; a<5; a++){
            System.out.println(hand.get(a).toString());
        }
    }
	
    public Game(){
        
        p = new Player();
        cards = new Deck();
        hand = new ArrayList<Card>();
        
    }
	
    // Here we print and play the actual game
    // using other classes to call the bankroll and evaluate the hands.
    public void play(){
        
        
        input = new Scanner(System.in);
        int userSelection;
        int tokens = 0;
        int checkHandValue;
        boolean playagain = true;
        
        System.out.println("***** WELCOME TO VIDEO POKER *****");
        while(playagain){
            cards.shuffle();
            System.out.println("It's your lucky day, you have " + p.getBankroll() + " tokens.");
            System.out.print("How many tokens (1-5) would you like to buy? ");
            tokens = input.nextInt();
            p.bets(tokens);
            System.out.println("Amount in bet is: " +p.getBets());
            
            for(int i=0; i<5; i++){
                hand.add(cards.deal()); }
            Collections.sort(hand);
            System.out.println("Here is your hand: ");
            for(int i=0; i<5; i++){
                System.out.println(hand.get(i));
            }
            
            for(int i =0; i<5; i++){
            System.out.println("Would you like to replace card number " +(i+1) + "? Yes-1, No-0.");
            int userResponse = input.nextInt();
            if(userResponse==1){
                hand.set(i, cards.deal());
            }
        }
        
        System.out.println("Here is your updated hand: ");
        for(int i = 0; i<5; i++){
            System.out.println(hand.get(i)); 
        }
        
        checkHandValue = checkHand();
        System.out.println(" Result of hand:  " +checkHandValue);
        System.out.println("\n Now you have:  " +p.getBankroll()+ " tokens left.");
        
        System.out.println("Would you like to play again? Yes-1 / No-0 ");
        int answer = input.nextInt();
        if(answer==0){
            playagain= false;
        }
            hand = new ArrayList<Card>();
            cards = new Deck();
             
    }
    }
    
    public void testPlay(){
        
        this.checkHand();

         // this method is used for testing the checkHand method
         // it evaluates the hand passed as a command-line argument.      
    }
    
	public int checkHand(){  
        

        if(royalFlush(hand)){
            
            System.out.print("Royal Flush. ");
            p.winnings(250);
            return 250;
            
        }
        else if(straightFlush(hand)){
            
            System.out.print("Straight Flush. ");
            p.winnings(50);
            return 50;
        }
        else if(fourKind(hand)){
            
            System.out.print("Four of a Kind. ");
            p.winnings(25);
            return 25;
        }
        else if(fullHouse(hand)){
            
            System.out.print("Full House. ");
            p.winnings(6);
            return 6;
        }
        else if(isFlush(hand)){
            
            System.out.print("Flush. ");
            p.winnings(5);
            return 5;
        }
        else if(straightHand(hand)){
            
            System.out.print("Straight hand. ");
            p.winnings(4);
            return 4;
        }
        else if(threeKind(hand)){
            
            System.out.print("Three of a kind. ");
            p.winnings(3);
            return 3;
        }
        else if(twoPairs(hand)){
            
            System.out.print("Two Pairs. ");
            p.winnings(2);
            return 2;
        }
        else if(onePair(hand)==1){
            
            System.out.print("One Pair. ");
            p.winnings(1);
            return 1;
        }
        else{
            
            System.out.print(" No Pair. ");
            return 0;
        }
    }
    
    // all the methods below are the construction of the types of hands. 
    private int onePair(ArrayList<Card> hand){
        
        Card tempCard;
        int i=1;
        int totalPairs = 0;
        
        while(i<hand.size()){
            tempCard = hand.get(i-1);
            if(tempCard.sameRank(hand.get(i))){
                i++;
                totalPairs++; }
            i++;
        }
        return totalPairs;
    }
    
    private boolean twoPairs(ArrayList<Card> hand){
        
        if(onePair(hand)==2)
            return true;
        else
            return false;
    }
    
    private boolean threeKind(ArrayList<Card> hand){
        
        if(onePair(hand)==0)
            return false;
        else if(hand.get(0).sameRank(hand.get(2)) || hand.get(1).sameRank(hand.get(3))
                || hand.get(2).sameRank(hand.get(4))) {
            
            return true;
        } 
        
        else {
            return false;
        }
    }
    
    private boolean straightHand(ArrayList<Card> hand){
        
        Card firstCards = hand.get(0);
        Card secondCards = hand.get(1);
        Card fifthCards = hand.get(4);  
        
        if(onePair(hand)>0){
            return false;
        }
        
        else if(fifthCards.differentRank(firstCards)==4){
            return true;
        }
        
        else if(firstCards.getRank()==1 && fifthCards.getRank()==13 && secondCards.getRank()==10){
            return true;
        }
        else{
            return false;
        }
    }
    
    private boolean fullHouse(ArrayList<Card> hand){
        
        if(onePair(hand)==2 && threeKind(hand)==true && fourKind(hand)==false){
            return true;
        }
        else{
            return false;
        }
    }
    
    private boolean isFlush(ArrayList<Card> hand){
        
        if(onePair(hand)>0){  
            return false;
        }
        
        else{
            int i=1; 
            for(Card card: hand){
                if(!card.sameSuit(hand.get(i))){
                    return false;
                }
                if(i<hand.size()-1){
                    i++;
                }
            }
            
            return true;
        }
    }
    
    private boolean fourKind(ArrayList<Card> hand){
        
        if(onePair(hand)==2 && threeKind(hand)==true){
            if(hand.get(0).sameRank(hand.get(3)) || hand.get(1).sameRank(hand.get(4))){ 
                return true;
            }
        }
        
        return false;
    }
    
    private boolean straightFlush(ArrayList<Card> hand){
        
        if(isFlush(hand) && straightHand(hand)){
            return true;
        }
        
        else{
            
            return false;
        }
    }
    
    private boolean royalFlush(ArrayList<Card> hand){
        
        if(straightFlush(hand) && hand.get(0).getRank()==1){
            return true;
        }
        
        else{
            return false;
        }
       
    }
    
    public ArrayList<Card> sortHand(ArrayList<Card> hand){
        
        Collections.sort(hand);
        return hand;
    }
   
}
             