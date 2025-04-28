package domain;

import java.util.LinkedList;

public class Deck {
    private LinkedList<Card> deck;
    public Deck() {
        this.deck = new LinkedList<>();
    }

    public void insertCardAtIndex(Card card, int index) {
        checkBounds(index);
        deck.add(index, card);
    }

    public Card getCardAtIndex(int index){
        checkBounds(index);
        return deck.get(index);
    }

    int getSize() {
        return deck.size();
    }

    private void checkBounds(int index){
        if(index < 0 || index > getSize()) throw new IndexOutOfBoundsException("Invalid index: index out of range");
    }

}
