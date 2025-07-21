package application;


import domain.core.rule.*;
import domain.core.rule.playing.GoToDeuceIfBothAtForty;
import domain.core.rule.playing.WinIfOnlyWinnerAtForty;
import domain.core.rule.WinTransitionStrategy;
import domain.core.state.*;
import domain.port.TennisGamePort;
import domain.usecase.TennisGameUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * Configuration Spring principale pour l'application TennisApp.
 * <p>
 * Déclare et assemble les beans nécessaires à l'injection de dépendances,
 * notamment les stratégies métier, les adaptateurs et les ports.
 * Permet de personnaliser ou remplacer facilement les règles métier et les adaptateurs
 * sans modifier le cœur du domaine.
 */

@Configuration
public class AppConfig {


    @Bean
    public TennisGamePort tennisGamePort(GameRuleStrategy gameRule) {
        return new TennisGameUseCase(gameRule);
    }


    @Bean
    public GameRuleStrategy gameRuleStrategy(ScoreRule scoreRule) {
        return new ClassicGameRule(Map.of(Playing.class, new PlayingTransitionStrategy(scoreRule, List.of(new GoToDeuceIfBothAtForty(), new WinIfOnlyWinnerAtForty())),
                Deuce.class, new DeuceTransitionStrategy(),
                Advantage.class, new AdvantageTransitionStrategy(),
                Win.class, new WinTransitionStrategy()));
    }


    @Bean
    public ScoreRule scoreRule() {
        return new ClassicScoreRule();
    }

}
