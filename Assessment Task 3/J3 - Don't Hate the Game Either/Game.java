import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Game {
    private final static Player p = new Player();
    private final static List<Card> deck = new ArrayList<>();
    private final static List<PointCard> point_deck = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc_file, sc;
        int choice;
        boolean chosen;

        try {
            sc_file = new Scanner(new File(args[0]));
            while (sc_file.hasNextLine()) {
                switch (sc_file.nextLine()) {
                    case "Trade":
                        deck.add(new TradeCard(
                                string_to_gems(sc_file.nextLine()),
                                string_to_gems(sc_file.nextLine())
                        ));
                        break;
                    case "Upgrade":
                        deck.add(new UpgradeCard(sc_file.nextInt()));
                        sc_file.nextLine();
                        break;
                    case "Point":
                        point_deck.add(new PointCard(
                                string_to_gems(sc_file.nextLine()),
                                sc_file.nextInt()
                        ));
                        sc_file.nextLine();
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            // ???
        }

        sc = new Scanner(System.in);

        System.out.println("Let's Play Decade!");
        while (true) {
            if (p.hasFivePointCards() || point_deck.isEmpty()) {
                System.out.printf("The player scored %d points!\n", p.score());
                break;
            }

            chosen = false;
            System.out.println("Would you like to:");
            System.out.println("0. Play a card");
            System.out.println("1. Rest");
            System.out.println("2. Buy a Merchant Card");
            System.out.println("3. Claim a Point Card");

            do {
                System.out.print("Enter your selection: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 0:
                        chosen = true;
                        play_operation(sc);
                        break;
                    case 1:
                        chosen = true;
                        rest_operation();
                        break;
                    case 2:
                        chosen = true;
                        buy_operation(sc);
                        break;
                    case 3:
                        chosen = true;
                        claim_operation(sc);
                        break;
                    default:
                        System.out.println("That's not a valid selection, try again.");
                }
            } while (!chosen);
        }
    }

    private static Map<Gem, Integer> string_to_gems(String gem_string) {
        String[] gem_count;
        Map<Gem, Integer> gems = new EnumMap<>(Gem.class);

        gem_count = gem_string.split(",");
        for (int i = 0; i < gem_count.length; i++)
            if (!Objects.equals(gem_count[i], "0"))
                gems.put(Gem.getGemByIndex(i), Integer.parseInt(gem_count[i]));
        return gems;
    }
    private static void play_operation(Scanner sc) {
        final String separator = "============";
        int pos;
        boolean played = false;

        System.out.println(separator);
        System.out.println("Current Hand");
        System.out.println(separator);
        p.printHand();
        System.out.println(separator);
        System.out.println("You have the following gems:");
        p.printCaravan();
        System.out.println("Which card would you like to play?");
        while (!played) {
            System.out.print("Enter your selection: ");
            pos = sc.nextInt();
            if (p.canPlay(pos)) {
                p.play(pos);
                played = true;
                System.out.println("You played the card.");
                System.out.println("You have the following gems:");
                p.printCaravan();
            } else System.out.println("That's not a valid selection, try again.");
        }

    }
    private static void rest_operation() {
        p.rest();
        System.out.println("You rested and refilled your hand.");
    }
    private static void buy_operation(Scanner sc) {
        final String separator = "======================";
        int pos;
        boolean bought = false;

        System.out.println(separator);
        System.out.println("Current Merchant Cards");
        System.out.println(separator);
        for (int i = 0; i <= Math.min(5, deck.size() - 1); i++) System.out.printf("%d. %s\n", i, deck.get(i).toString());
        System.out.println(separator);
        System.out.print("You have the following gems: ");
        p.printCaravan();
        System.out.println("Which card would you like to buy?");
        while (!bought) {
            System.out.print("Enter your selection: ");
            pos = sc.nextInt();
            if (p.canBuy(pos)) {
                p.buy(pos, deck.get(pos));
                deck.remove(pos);
                bought = true;
                System.out.println("You bought the card.");
            } else System.out.println("That's not a valid selection, try again.");
        }
    }
    private static void claim_operation(Scanner sc) {
        final String separator = "===================";
        int pos;
        boolean claimed = false;

        System.out.println(separator);
        System.out.println("Current Point Cards");
        System.out.println(separator);
        for (int i = 0; i < point_deck.size(); i++) System.out.printf("%d. %s\n", i, point_deck.get(i).toString());
        System.out.println(separator);
        System.out.print("You have the following gems: ");
        p.printCaravan();
        System.out.println("Which card would you like to claim?");

        while (!claimed) {
            System.out.print("Enter your selection: ");
            pos = sc.nextInt();
            if (pos < 0 || pos >= point_deck.size()) System.out.println("That's not a valid selection, try again.");
            else {
                claimed = true;
                if (p.canClaim(point_deck.get(pos))) {
                    p.claim(point_deck.get(pos));
                    point_deck.remove(pos);
                    System.out.println("You claimed the card.");
                } else System.out.println("You cannot claim that card.");
            }
        }

    }
}
