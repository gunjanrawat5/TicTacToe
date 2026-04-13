package services.strategy;

import model.Board;
import model.Move;
import model.Player;

public interface WinnerCheckStrategy {
    Player checkWinner(Board board, Move currentMove);
}
