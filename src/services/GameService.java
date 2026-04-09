package services;

import exception.InvalidCellException;
import model.Cell;
import model.Game;
import model.Move;
import model.Player;
import model.constants.CellState;

public class GameService {
    public Move executeMove(Player player, Game game, int row, int col){
        Cell cell = game.getBoard().getCells().get(row).get(col);
        if(!cell.getCellState().equals(CellState.EMPTY)){
            throw new InvalidCellException("Invalid cell has been chosen for the move, please try again");
        }
        cell.setCellState(CellState.FULL);
        cell.setPlayer(player);
        Move move = new Move(cell,player);
        game.getMoves().add(move);
        game.getBoardState().add(game.getBoard().clone());
        return move;
    }
}
