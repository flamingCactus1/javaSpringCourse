package academy.learnprogramming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        logger.info("Guess the number game");

        //create context
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        //get number generator bean from context (container)
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

        //call method next() to get a random number
        int number = numberGenerator.next();

        logger.info("received number is {}", number);

        //get game bean from context (container)<
        Game game = context.getBean(Game.class);

        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);
        logger.info(messageGenerator.getMainMessage());
        logger.info(messageGenerator.getResultMessage());

        //close context
        context.close();

    }
}
