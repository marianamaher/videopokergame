
// *** This class creates a player, and sets up the bankroll 
// to return tokens lost and won during the game. 

import java.util.ArrayList;
import java.util.*;

public class Player {
	
		
    private int bankroll;
    private int bet;
    private ArrayList<Card> hand;

    //you may choose to use more instance variables
		
    public Player(){	
        
        bankroll = 100;
        hand = new ArrayList<Card>();
        
    }
		
    public void bets(int amt){
        
        bet = amt;
        bankroll = (bankroll-bet);
        //player makes a bet
    }

    public void winnings(int odds){
       
        bankroll += odds*bet;
    }

    public int getBankroll(){
        
        return bankroll;
        //return current balance of bankroll
    }
    
    public int getBets(){
        
        return bet;
    }
    
    public void addCard(Card c){
        
        hand.add(c);
        bankroll++;
    }
    
    public void removeCard(Card c){
        
        if(bankroll>0){
            hand.remove(c);
            bankroll--;
        }
    }
    
    public ArrayList<Card> getHand(){
        
        return hand;
        
    }
    

    //you may wish to use more methods here
}


