package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleNumberGuess{

    @Autowired
    private MessageGenerator messageGenerator;

    @Autowired
    private Game game;

    private static final Logger logger = LoggerFactory.getLogger(ConsoleNumberGuess.class);

    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        logger.info("start() is called. Container is ready for use");

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());
            int guess = scanner.nextInt();
            game.setGuess(guess);
            game.check();
            if (game.isGameWon() || game.isGameLost()) {
                scanner.nextLine();
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again? y/n");
                String playAgain = scanner.nextLine().trim();
                if (!playAgain.equalsIgnoreCase("y")) {
                    break;
                }
                game.reset();
            }
        }
    }
}
