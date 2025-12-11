import java.util.Map;
import java.util.stream.Collectors;

public final class TradeCard extends Card {
    private final Map<Gem, Integer> value;

    public TradeCard(Map<Gem, Integer> cost, Map<Gem, Integer> value) {
        super(cost);
        this.value = value;
    }
    public Map<Gem, Integer> value() {
        return this.value;
    }
    @Override
    public String toString() {
        String cost_string = "", value_string = "";

        cost_string = this.cost().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getValue() + " " + entry.getKey())
                .collect(Collectors.joining(", "));
        value_string = this.value.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getValue() + " " + entry.getKey())
                .collect(Collectors.joining(", "));

        return "Trade: " + cost_string + " -> " + value_string;
    }
}
