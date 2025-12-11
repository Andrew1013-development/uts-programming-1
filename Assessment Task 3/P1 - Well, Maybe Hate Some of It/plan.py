from abc import ABC
from parts import Gem, Card, TradeCard, UpgradeCard, PointCard, Player
from copy import deepcopy
from collections import deque

class Move(ABC):
    pass


class Play(Move):
    def __init__(self, pos: int) -> None:
        self.pos = pos
    def position(self) -> int:
        return self.pos
    def __str__(self) -> str:
        return "Play " + str(self.pos)
    def __repr__(self) -> str:
        return self.__str__()


class Rest(Move):
    def __str__(self) -> str:
        return "Rest"
    def __repr__(self) -> str:
        return self.__str__()

def plan(player: Player, card: PointCard) -> list[Move]:
    visited = set()
    q = deque()

    # BFS to branch and bound to a viable solution    
    visited.add(state(player))
    q.append((player, []))
    while q:
        #print(visited)
        current_player, path = q.popleft()
        # try playing any unused card
        for i in range(0, current_player.hand_size()):
            if current_player.can_play(i):
                next_player = current_player.copy()
                next_player.play(i)

                if next_player.has_gems(card.cost()):
                    return path + [Play(i)]
                if state(next_player) not in visited:
                    visited.add(state(next_player))
                    q.append((next_player, path + [Play(i)]))
        # try resting
        next_player_rest = current_player.copy()
        next_player_rest.rest()

        if (next_player_rest.has_gems(card.cost())):
            return path + [Rest()]
        if state(next_player_rest) not in visited:
            visited.add(state(next_player_rest))
            q.append((next_player_rest, path + [Rest()]))
    # no viable solution was reached
    return [] 

def state(player: Player):
    return (
        tuple(sorted(player.caravan.items(), key=lambda item: item[0].value)), 
        tuple(player.used)
    )