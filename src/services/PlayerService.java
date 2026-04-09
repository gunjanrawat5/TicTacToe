package services;

import exception.DuplicateSymbolException;
import model.Bot;
import model.Player;
import model.constants.BotDifficultyLevel;
import model.constants.PlayerType;

import java.util.HashSet;

public class PlayerService {
    private static int counter = 1;
    private HashSet<Character> symbolSet;

    public PlayerService(){
        this.symbolSet = new HashSet<>();
    }

    public Player createPlayer(String name, char symbol){
        if(symbolSet.contains(symbol)){
            throw new DuplicateSymbolException("The symbol used for the player already exists");
        }
        symbolSet.add(symbol);
        return  new Player(
                counter++,
                name,
                symbol,
                PlayerType.HUMAN);
    }
    // SRP and OCP
    // Keeping these 2 methods separate because bot can change a lot - new features and it wont affect the player creation code
    public Bot createBot(String name, char symbol){
        if(symbolSet.contains(symbol)){
            throw new DuplicateSymbolException("The symbol used for the player already exists");
        }
        symbolSet.add(symbol);
        return new Bot(
                counter++,
                name,
                symbol,
                PlayerType.BOT,
                BotDifficultyLevel.MEDIUM
        );
    }
}
