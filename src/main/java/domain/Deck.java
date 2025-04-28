package domain;

import java.util.LinkedList;

public class Deck {
    private LinkedList<Card> deck;
    public Deck() {
        this.deck = new LinkedList<>();
    }

    public void insertCardAtIndex(Card card, int index) {
        this.checkBounds(index);
    }

    public Card getCardAtIndex(int index){
        this.checkBounds(index);
        return deck.get(index);
    }

    private void checkBounds(int index){
        if(index < 0 || index > deck.size()) throw new IndexOutOfBoundsException("Invalid index: index out of range");
    }

}
