package academy.learnprogramming.config;

import academy.learnprogramming.GuessCount;
import academy.learnprogramming.MaxNumber;
import academy.learnprogramming.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "academy.learnprogramming")
@PropertySource("classpath:config/game.properties")
public class GameConfig {
    @Value("${game.maxNumber:50}")
    private int maxNumber;
    @Value("${game.guessCount:5}")
    private int guessCount;
    @Value("${game.minNumber:0}")
    private int minNumber;

    @Bean
    @GuessCount
    public int guessCount(){
        return this.guessCount;
    }

    @Bean
    @MaxNumber
    public int maxNumber() {
        return this.maxNumber;

    }

    @Bean
    @MinNumber
    public int minNumber() {
        return this.minNumber;
    }
}
