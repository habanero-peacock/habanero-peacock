package no.karianneberg.yahtzee.game.scorers;

import no.karianneberg.yahtzee.game.Throw;

public class SameSideScoringStrategy implements ScoringStrategy {
    private int dieValue;

    public SameSideScoringStrategy(int dieValue) {
        this.dieValue = dieValue;
    }

    public int score(Throw theThrow) {
        return theThrow.getNumberOfDiceWithValue(dieValue) * dieValue;
    }
}
