package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Deck {
    private LinkedList<Card> deck;
    private Random rand;

    public Deck() {
        this.deck = new LinkedList<>();
        this.rand = new Random();
    }
    public Deck(Random rand) {
        this.deck = new LinkedList<>();
        this.rand = rand;
    }

    public void insertCardAtIndex(Card card, int index) {
        checkBounds(index);
        deck.add(index, card);
    }

    public void insertCardAtRandomIndex(Card card) {
        int index = this.rand.nextInt(getSize() + 1);
        insertCardAtIndex(card, index);
    }

    public Card drawCard(){
        return deck.poll();
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public void flipDeck(){
        Collections.reverse(deck);
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
