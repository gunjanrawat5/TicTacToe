package controller;

import model.*;
import model.constants.GameState;
import model.constants.PlayerType;
import services.BoardService;
import services.GameService;
import services.PlayerService;
import services.strategy.EasyBotPlayingStrategy;

import java.util.*;

public class GameController {
    private Scanner sc;
    private PlayerService playerService;
    private GameService gameService;
    private BoardService boardService;
    public GameController(PlayerService playerService, GameService gameService, BoardService boardService){
        this.sc = new Scanner(System.in);
        this.playerService = playerService;
        this.gameService = gameService;
        this.boardService = boardService;
    }
    public List<Player> generatePlayerList(int playerCount){
        System.out.println("Please enter 1 if you want a bot in the game, else 0: ");
        int botCheck = sc.nextInt();
        List<Player> players = new ArrayList<>();
        if(botCheck == 1){
            //TODO : user inp for bot difficulty level and create bot from that
            // TODO : take user inp for bot name and symbol
            Bot bot = playerService.createBot("Bot",'$');
            players.add(bot);
            playerCount--;
        }
        for(int i = 0; i<playerCount;i++){
            System.out.println("Enter the name of player: ");
            String playerName = sc.next();
            System.out.println("Enter the symbol for player: "+ playerName);
            char symbol = sc.next().charAt(0);
            Player player = playerService.createPlayer(playerName,symbol);
            players.add(player);
        }
        Collections.shuffle(players);
        return players;

    }

    public Move createMove(Player player, Game game){
        if(player.getPlayerType().equals(PlayerType.HUMAN)) {
            System.out.println("Please enter the row: ");
            int row = sc.nextInt();
            System.out.println("Please enter the col: ");
            int col = sc.nextInt();
            //TODO: validate row and col before executing
            return gameService.executeMove(player,game,row,col);
        }
        else{
            return gameService.executeMove(player, game);
        }
    }

    public Game undo(int moves, Game game){return null;}

    public void replayGame(Game game){};

    public GameState checkWinner(Game game, Move move){

        return gameService.checkWinner(game,move);
    }

    public void displayBoard(Game game){
        boardService.displayBoard(game.getBoard());
    }
}
