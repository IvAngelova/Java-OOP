package T01_WorkingWithAbstraction.exercise.cards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

       /* System.out.println("Card Suits:");
        for (CardSuits cardSuit : CardSuits.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",
                    cardSuit.ordinal(), cardSuit.name());
        }

        System.out.println("Card Ranks:");
        for (CardRanks cardRank : CardRanks.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",
                    cardRank.ordinal(), cardRank.name());
        }  */

        CardRanks cardRank = CardRanks.valueOf(scan.nextLine());
        CardSuits cardSuit = CardSuits.valueOf(scan.nextLine());
        Card card = new Card(cardSuit, cardRank);
        System.out.printf("Card name: %s of %s; Card power: %d",
                cardRank.toString(), cardSuit.toString(), card.getPower());
    }
}
