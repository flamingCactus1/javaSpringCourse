package academy.learnprogramming.config;

import academy.learnprogramming.MaxNumber;
import academy.learnprogramming.GuessCount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {
    private int maxNumber = 50;
    private int guessCount = 8;

    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }

    @Bean
    @MaxNumber
    public int maxNumber() {
        return this.maxNumber;

    }
}
