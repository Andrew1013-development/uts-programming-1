import java.util.Map;
import java.util.stream.Collectors;

public final class PointCard extends Card {
    private final int points;

    public PointCard(Map<Gem, Integer> cost, int points) {
        super(cost);
        this.points = points;
    }
    public int points() {
        return this.points;
    }
    @Override
    public String toString() {
        String cost_string = "";

        if (this.cost() == null || this.cost().isEmpty()) {
            return "Nothing";
        } else {
            cost_string = this.cost().entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(entry -> entry.getValue() + " " + entry.getKey())
                    .collect(Collectors.joining(", "));
        }
        return "Points: " + cost_string + " -> " + this.points;
    }
}
