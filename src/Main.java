import controller.GameController;
import exception.GameDrawnException;
import model.Game;
import model.Move;
import model.Player;
import model.constants.GameState;
import model.constants.PlayerType;
import services.BoardService;
import services.GameService;
import services.PlayerService;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the dimension of board: ");
        int dimension = sc.nextInt();
        GameService gameService = new GameService(dimension);
        PlayerService playerService = new PlayerService();
        BoardService boardService = new BoardService();
        GameController gameController = new GameController(playerService,gameService,boardService);


        List<Player> players = gameController.generatePlayerList(dimension-1);
        Game game = gameService.createGame(players,dimension);
        game = gameService.startGame(game);
        int moveIndex = 0;
        gameController.displayBoard(game);
        while(true){
            Player currentPlayer = game.getPlayers().get(moveIndex);
            System.out.println("Player to make a move: " + currentPlayer.getName());
            Move currentMove = gameController.createMove(currentPlayer,game);
            gameController.displayBoard(game);
            if(currentPlayer.getPlayerType().equals(PlayerType.HUMAN) && !currentPlayer.isHasUsedUndo()){
                System.out.println("Please enter the number of steps you want to undo, if you dont want to undo press 0: ");
                int undoCount = sc.nextInt();
                if(undoCount>0) {
                    gameController.undo(undoCount, game);
                    currentPlayer.setHasUsedUndo(true);
                    gameController.displayBoard(game);
                } else{
                    System.out.println("Undo not used, moving forward");
                }

            }
            try {
                GameState gameState = gameController.checkWinner(game,currentMove);
                if(gameState.equals(GameState.WINNER)){
                    game.setWinner(currentPlayer);
                    System.out.println("Game over ! The winner is : " + currentPlayer.getName());
                    break;
                }
            } catch (GameDrawnException ex){
                System.out.println("Game ends in a draw !");
                break;
            }
            moveIndex = (moveIndex + 1)% (dimension-1);

        }



    }
}