

public class Deck {
	
    private Card[] cards;
    private int top; // the index of the top of the deck. 
    private Card card; 
    
	// deck is built and initialized with 52 cards. 
    public Deck(){
        
        cards = new Card[52];
        int numOfCards = 0;
        
        //s for the suit and r for the rank
        for(int suit = 1; suit<5; suit++){ 
            for(int rank=1; rank<14; rank++){
                card = new Card(suit,rank);
                cards[numOfCards] = card;
                numOfCards++;
            }
        }
        top = 0;
        
    }
	
    //the deck is shuffled here.
    public void shuffle(){
   
        Card temp;
       
        for(int i=1; i<1000; i++){
            int rand = (int)(Math.random()*52);
            temp = cards[0];
            cards[0] = cards[rand];
            cards[rand] = temp;
        }
        
    }
    
    // we deal a card in here. 
    public Card deal(){
        
        if(top>51){
            top = 0;
            
            return new Card(0,0);
        }
        else {
            top++;
        return cards[top-1];
        }
    }
	
    //add more methods here if needed

}
