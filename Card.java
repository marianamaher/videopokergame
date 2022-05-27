
// *** This class creates the cards of the deck, and it gives them the suit and rank.
// There are also a few accessor methods to be used by the other classes. 

public class Card implements Comparable<Card>{
	
    private int suit; //use integers 1-4 to encode the suit
    private int rank; //use integers 1-13 to encode the rank
    
    public Card(int s, int r){
        
        suit = s;
        rank = r;
        
    }
	
    //method to compare the cards' suits and ranks. 
    public int compareTo(Card c){
        
        if(sameRank(c)){
            return this.getSuit()-c.getSuit();
        }
        
        return differentRank(c);
        
    }
	
    //method to print the cards. 
    public String toString(){
        
        String cardStyle;
        String cardSuit;
        String cardRank;
        
        if(suit==1)
            cardSuit = "CLUBS";
        else if(suit==2)
            cardSuit = "DIAMONDS";
        else if (suit==3)
            cardSuit = "HEARTS";
        else 
            cardSuit = "SPADES";
        
       if(rank>1 && rank<11)
           cardRank =""+rank;
       else if(rank==1)
            cardRank ="ACE";
        else if(rank==11)
           cardRank ="JACK";
        else if(rank==12)
           cardRank ="QUEEN";
        else 
           cardRank ="KING";
        cardStyle = cardRank + " of " + cardSuit; 
        return cardStyle;  
   
    }
    
    public int getSuit(){
        
        return suit;  
    }
    
    public int getRank(){
        
        return rank;  
    }
    
    public boolean sameSuit(Card c){
        
        return this.getSuit()==c.getSuit();
        
    }
    
    public boolean sameRank(Card c){
        
        return this.getRank()==c.getRank();
        
    }
    
    public int differentRank(Card c){
        
        return this.getRank()-c.getRank();
        
    }
}
