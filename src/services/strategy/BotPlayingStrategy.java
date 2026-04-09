package services.strategy;

import model.Game;
import model.Move;
import model.Player;

public interface BotPlayingStrategy {
    Move executeMove(Player player, Game game);
}
