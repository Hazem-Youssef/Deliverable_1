/**
 * BlackJackGame created using the base code provided in Card.java, changes implemented in BlackjackCard.java that extends the Card.java class.
 *
 * Authors: Group 9 (Monika Skiba, Hazem Youssef, Aarushi Sharma)
 * Date: October 9, 2023
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BlackJackGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain = true;

        while (playAgain) {
            List<BlackjackCard> deck = createDeck();
            List<BlackjackCard> playerHand = new ArrayList<>();
            List<BlackjackCard> dealerHand = new ArrayList<>();

            playerHand.add(drawCard(deck, random));
            playerHand.add(drawCard(deck, random));
            dealerHand.add(drawCard(deck, random));
            dealerHand.add(drawCard(deck, random));

            System.out.println("Welcome to Blackjack!");

            while (true) {
                System.out.println("Your hand: " + playerHand);
                System.out.println("Your score: " + calculateScore(playerHand));

                if (calculateScore(playerHand) > 21) {
                    System.out.println("Bust! You lose.");
                    break;
                }

                System.out.print("Do you want to Hit (H) or stand (S)? ");
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("H")) {
                    playerHand.add(drawCard(deck, random));
                } else if (choice.equalsIgnoreCase("S")) {
                    break;
                }
            }

            // Dealer's turn
            while (calculateScore(dealerHand) < 17) {
                dealerHand.add(drawCard(deck, random));
            }

            System.out.println("Dealer's hand: " + dealerHand);
            System.out.println("Dealer's score: " + calculateScore(dealerHand));

            // Determine the winner
            if (calculateScore(playerHand) > 21 || (calculateScore(dealerHand) <= 21 && calculateScore(dealerHand) >= calculateScore(playerHand))) {
                System.out.println("Dealer wins!");
            } else {
                System.out.println("You win!");
            }

            System.out.print("Play again? (Y/N): ");
            String playAgainChoice = scanner.nextLine();
            playAgain = playAgainChoice.equalsIgnoreCase("Y");
        }

        scanner.close();
    }

    public static List<BlackjackCard> createDeck() {
        List<BlackjackCard> deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String value : values) {
                int cardValue = value.equals("Ace") ? 11 : (value.equals("Jack") || value.equals("Queen") || value.equals("King")) ? 10 : Integer.parseInt(value);
                deck.add(new BlackjackCard(suit, value, cardValue));
            }
        }

        return deck;
    }

    public static BlackjackCard drawCard(List<BlackjackCard> deck, Random random) {
        int index = random.nextInt(deck.size());
        BlackjackCard card = deck.get(index);
        deck.remove(index);
        return card;
    }

    public static int calculateScore(List<BlackjackCard> hand) {
        int score = 0;
        int numAces = 0;

        for (BlackjackCard card : hand) {
            int cardValue = card.getValue();
            if (cardValue == 1) {
                score += 11;
                numAces++;
            } else {
                score += cardValue;
            }
        }

        // Handle Aces
        while (score > 21 && numAces > 0) {
            score -= 10;
            numAces--;
        }

        return score;
    }
}

