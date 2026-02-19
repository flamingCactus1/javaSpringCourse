package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements MessageGenerator {
    private static final Logger logger = LoggerFactory.getLogger(MessageGeneratorImpl.class);
    private final Game game;

    public MessageGeneratorImpl(@Autowired Game game) {
        this.game = game;
    }


    @PostConstruct
    public void init() {
        if (isGamePresent()) {
            logger.debug("Game field in MessageGenerator was autowired correctly");
        } else {
            logger.debug("Game field in MessageGenerator was not autowired correctly and is null");
        }
    }

    private boolean isGamePresent() {
        return game != null;
    }

    @Override
    public String getMainMessage() {
        return "Number is between " +
                game.getSmallest() +
                " and " +
                game.getBiggest() +
                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon()){
            return "You guessed it! The number was " + game.getNumber();
        } else if (game.isGameLost()) {
            return "You lost! The number was " + game.getNumber();
        }else if (!game.isValidNumberRange()){
            return "Number is out of range";
        }else if(game.getRemainingGuesses() == game.getGuessCount()){
            return "What is you first guess?";
        }else {
            String direction = "Lower";
            if (game.getGuess() < game.getNumber()) {
                direction = "Upper";
            }
            return direction + "! You have " + game.getRemainingGuesses() + " guesses left.";
        }
    }
}
