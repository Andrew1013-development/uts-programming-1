import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        PointCard pc = new PointCard(Map.ofEntries(
                Map.entry(Gem.BLUE, 8),
                Map.entry(Gem.YELLOW, 13)
        ), 16);
        TradeCard tc = new TradeCard(Map.ofEntries(
           Map.entry(Gem.BLUE, 10),
           Map.entry(Gem.GREEN, 12)
        ), Map.ofEntries(
            Map.entry(Gem.PINK, 5)
        ));
        UpgradeCard uc = new UpgradeCard(25);

        System.out.println(pc);
        System.out.println(tc);
        System.out.println(uc);
    }
}
