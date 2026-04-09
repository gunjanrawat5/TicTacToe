package services;

import exception.InvalidCellException;
import model.*;
import model.constants.BotDifficultyLevel;
import model.constants.CellState;
import services.strategy.BotPlayingStrategy;
import services.strategy.BotPlayingStrategyFactory;

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


    public Move executeMove(Bot bot, Game game){
        BotDifficultyLevel botDifficultyLevel = bot.getBotDifficultyLevel();
        BotPlayingStrategy botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(botDifficultyLevel);
        return botPlayingStrategy.executeMove(bot,game);
    }
}
