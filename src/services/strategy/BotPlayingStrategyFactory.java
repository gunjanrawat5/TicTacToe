package services.strategy;

import model.constants.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        //TODO  : Update with switch case
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        } else{
            return new MediumBotPlayingStrategy();
        }
    }
}
