package no.karianneberg.yahtzee.game.scorers;

import no.karianneberg.yahtzee.game.Scorer;
import no.karianneberg.yahtzee.game.Throw;
import no.karianneberg.yahtzee.game.Combination;

import java.util.HashMap;
import java.util.Map;

public class StrategyScorer implements Scorer {

    public Map<Combination, ScoringStrategy> scorers = new HashMap<Combination, ScoringStrategy>();

    public StrategyScorer() {
        scorers.put(Combination.ONES, new SameSideScoringStrategy(1));
        scorers.put(Combination.TWOS, new SameSideScoringStrategy(2));
        scorers.put(Combination.THREES, new SameSideScoringStrategy(3));
        scorers.put(Combination.FOURS, new SameSideScoringStrategy(4));
        scorers.put(Combination.FIVES, new SameSideScoringStrategy(5));
        scorers.put(Combination.SIXES, new SameSideScoringStrategy(6));
        scorers.put(Combination.ONE_PAIR, new NumberOfAKindScoringStrategy(2));
        scorers.put(Combination.TWO_PAIRS, new TwoPairsScoringStrategy());
        scorers.put(Combination.THREE_OF_A_KIND, new NumberOfAKindScoringStrategy(3));
        scorers.put(Combination.FOUR_OF_A_KIND, new NumberOfAKindScoringStrategy(4));
        scorers.put(Combination.FULL_HOUSE, new FullHouseScoringStrategy());
        scorers.put(Combination.SMALL_STRAIGHT, new StraightScoringStrategy(1));
        scorers.put(Combination.LARGE_STRAIGHT, new StraightScoringStrategy(2));
        scorers.put(Combination.CHANCE, new ChanceScoringStrategy());
        scorers.put(Combination.YAHTZEE, new YahtzeeScoringStrategy());
    }

    public int score(Combination combo, Throw theThrow) {
        return scorers.get(combo).score(theThrow);
    }
}
