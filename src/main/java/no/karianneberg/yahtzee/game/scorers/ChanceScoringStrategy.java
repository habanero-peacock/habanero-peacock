package no.karianneberg.yahtzee.game.scorers;

import no.karianneberg.yahtzee.game.Throw;

public class ChanceScoringStrategy implements ScoringStrategy {
    public int score(Throw theThrow) {
        return theThrow.sum();
    }
}
