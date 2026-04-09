package services;

import model.*;
import model.constants.CellState;

import java.util.List;

public class RandomBotPlayingStrategy {

    public static Move executeMove(Player player, Game game){
        Board board = game.getBoard();
        Move move = null;
        for(List<Cell> cells : board.getCells()){
            for(Cell cell : cells){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    cell.setCellState(CellState.FULL);
                    cell.setPlayer(player);
                    move = new Move(cell,player);
                    game.getMoves().add(move);
                    game.getBoardState().add(board.clone());
                }
            }
        }
        return  move;
        // TODO : Randomise move for bot - figure out empty cells and choose cell randomly
    }
}
