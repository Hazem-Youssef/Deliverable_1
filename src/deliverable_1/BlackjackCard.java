/**
 * BlackjackCard Base Code Implementation, extending the Card.java class
 *
 * Authors: Group 9 (Monika Skiba, Hazem Youssef, Aarushi Sharma)
 * Date: October 9, 2023
 */


public class BlackjackCard extends Card {
    private final String rank;
    private final String suit;
    private final int value;

    public BlackjackCard(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public int getValue() {
        return value;
    }
}

