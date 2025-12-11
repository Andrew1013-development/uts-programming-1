import java.util.Map;
import java.util.Map.Entry;
import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;

public class Player {
    private final Map<Gem, Integer> gems;
    private final List<Card> cards, used_cards;
    private final List<PointCard> point_cards;

    // class methods
    public Player() {
        this.gems = new EnumMap<>(Gem.class);
        this.cards = new ArrayList<>();
        this.used_cards = new ArrayList<>();
        this.point_cards = new ArrayList<>();   

        gems.put(Gem.YELLOW, 3);
        for (int i = 1; i <= Gem.values().length - 1; i++) gems.put(Gem.getGemByIndex(i), 0);

        cards.add(new TradeCard(Map.of(), Map.ofEntries(Map.entry(Gem.YELLOW, 2))));
        cards.add(new UpgradeCard(2));
    }
    public boolean canBuy(int merchant_pos) { return gems() >= merchant_pos; }
    public void buy(int merchant_pos, Card merchant_card) {
        remove_gems(merchant_pos);
        cards.add(merchant_card);
    }
    public boolean canClaim(PointCard point_card) { return check_cost(point_card); }
    public void claim(PointCard point_card) {
        remove_gems(point_card.cost());
        point_cards.add(point_card);
    }
    public boolean canPlay(int player_pos) {
        if (player_pos < 0 || player_pos >= cards.size()) return false;
        return switch (cards.get(player_pos)) {
            case TradeCard tc -> check_cost(tc);
            case UpgradeCard uc -> true;
            default -> false;
        };
    }
    public void play(int player_pos) {
        int upgrade_remaining, diff;

        switch (cards.get(player_pos)) {
            case TradeCard tc:
                remove_gems(tc.cost());
                add_gems(tc.value());
                break;
            case UpgradeCard uc:
                upgrade_remaining = uc.upgrades();
                for (Entry<Gem, Integer> gem : gems.entrySet()) {
                    if (gem.getKey() != Gem.PINK) {
                        diff = Math.min(gem.getValue(), upgrade_remaining);
                        gems.put(gem.getKey().upgrade(), diff);
                        gems.put(gem.getKey(), gem.getValue() - diff);
                        upgrade_remaining -= diff;
                    }
                }
                break;
            default:
                break;
        }
        used_cards.add(cards.get(player_pos));
        cards.remove(player_pos);
    }
    public void rest() {
        while (!used_cards.isEmpty()) {
            cards.add(used_cards.getLast());
            used_cards.removeLast();
        }
    }
    public int score() {
        int score_card = 0, score_gem = 0;

        for (PointCard pc : point_cards) score_card += pc.points();
        for (Entry<Gem, Integer> gem : gems.entrySet())
            if (gem.getKey() != Gem.YELLOW)
                score_gem += gem.getValue();
        //System.out.printf("%d (%d PointCard, %d Gem)\n", score_card + score_gem, score_card, score_gem);
        return score_card + score_gem;
    }
    public boolean hasFivePointCards() { return point_cards.size() >= 5; }

    // class helpers
    private int gems() {
        int total_gems = 0;

        for (Entry<Gem, Integer> gem : gems.entrySet()) total_gems += gem.getValue();
        return total_gems;
    }
    private void remove_gems(int cost) {
        int diff;

        for (Entry<Gem, Integer> gem : gems.entrySet()) {
            diff = Math.min(gem.getValue(), cost);
            gems.put(gem.getKey(), gem.getValue() - diff);
            cost -= diff;
        }
    }
    private void remove_gems(Map<Gem, Integer> cost) {
        for (Entry<Gem, Integer> gem : cost.entrySet())
            gems.put(gem.getKey(), gems.get(gem.getKey()) - gem.getValue());
    }
    private void add_gems(Map<Gem, Integer> value) {
        for (Entry<Gem, Integer> gem : value.entrySet())
            gems.put(gem.getKey(), gems.get(gem.getKey()) + gem.getValue());
    }
    private boolean check_cost(Card card) {
        for (Entry<Gem, Integer> gem : card.cost().entrySet())
            if (card.cost().get(gem.getKey()) > gems.get(gem.getKey()))
                return false;
        return true;
    }
}