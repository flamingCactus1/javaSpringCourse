package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameImpl implements Game {

    //==constants==
    private static final Logger logger = LoggerFactory.getLogger(GameImpl.class);
    //==fields==
    private NumberGenerator numberGenerator;
    private int guessCount = 10;
    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;


    //==public methods==
    public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @Override
    public void reset() {
        smallest = 0;
        biggest = numberGenerator.getMaxNumber();
        guess = 0;
        remainingGuesses = guessCount;
        number = numberGenerator.next();
        logger.debug("number is {}", number);
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public void check() {
        checkValidNumberRange();
        if (validNumberRange) {
            if (guess > number) {
                biggest = guess-1;
            }
        }

        if (guess < number) {
            smallest = guess+1;
        }

        remainingGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameLost() {
        return guess == number;
    }

    @Override
    public boolean isGameWon() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    //==private methods==
    private void checkValidNumberRange() {
        validNumberRange = (guess >= smallest && guess <= biggest);
    }
}
