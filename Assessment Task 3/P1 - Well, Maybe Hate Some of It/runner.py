from parts import Gem, Card, TradeCard, UpgradeCard, PointCard, Player
from plan import Move, plan

if __name__ == "__main__":
    player = Player([UpgradeCard(2)], {Gem.YELLOW: 3})
    print(plan(player, PointCard({Gem.GREEN: 2}, 15)))
