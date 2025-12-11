public final class UpgradeCard extends Card {
    private final int gems;

    public UpgradeCard(int gems) {
        super();
        this.gems = gems;
    }
    public int upgrades() {
        return this.gems;
    }
    public String toString() {
        return "Upgrade: " + Integer.toString(gems);
    }
}