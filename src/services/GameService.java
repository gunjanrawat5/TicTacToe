package services;

import exception.InvalidCellException;
import model.*;
import model.constants.BotDifficultyLevel;
import model.constants.CellState;
import model.constants.GameState;
import services.strategy.BotPlayingStrategy;
import services.strategy.BotPlayingStrategyFactory;
import services.strategy.O1WinnerCheckStrategy;
import services.strategy.WinnerCheckStrategy;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameService {

    private WinnerCheckStrategy winnerCheckStrategy;

    public GameService(int dimension) {
         winnerCheckStrategy = new O1WinnerCheckStrategy(dimension);
    }

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


    public Move executeMove(Player player, Game game){
        BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(BotDifficultyLevel.MEDIUM);
        return botPlayingStrategy.executeMove(player,game);
    }

    public Game createGame(List<Player> players, int size){
        Board board = new Board(size);
        return new Game(board,players);
    }

    public  Game startGame(Game game){
        game.setGameState(GameState.IN_PROGRESS);
        Collections.shuffle(game.getPlayers());
        return game;
    }

    public GameState checkWinner(Game game, Move currentMove){
        Player player = winnerCheckStrategy.checkWinner(game.getBoard(),currentMove);
        if(player!=null){
            return GameState.WINNER;
        } else{
            return GameState.IN_PROGRESS;
        }
    }
}
