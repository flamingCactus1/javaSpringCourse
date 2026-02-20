package academy.learnprogramming.game;

import academy.learnprogramming.GuessCount;
import academy.learnprogramming.numbergenerator.NumberGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
@Getter
public class GameImpl implements Game {

    //==fields==
    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;
    @Setter
    private int guess;
    private final int guessCount;
    private int number;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Autowired
    public GameImpl(NumberGenerator numberGenerator,@GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    //==init==
    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber();
        biggest = numberGenerator.getMaxNumber();
        guess = numberGenerator.getMinNumber();
        remainingGuesses = guessCount;
        number = numberGenerator.next();
        log.debug("number is {}", number);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("In Game preDestroy()");
    }

    @Override
    public void check() {
        checkValidNumberRange();
        if (validNumberRange) {
            if (guess > number & guess < numberGenerator.getMaxNumber()) {
                biggest = guess - 1;
            }
        }

        if (guess < number && guess > numberGenerator.getMinNumber()) {
            smallest = guess + 1;
        }

        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    //==private methods==
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest && guess <= biggest);
    }
}
