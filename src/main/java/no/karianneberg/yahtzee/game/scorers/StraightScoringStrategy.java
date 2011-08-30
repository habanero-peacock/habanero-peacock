package no.karianneberg.yahtzee.game.scorers;

import no.karianneberg.yahtzee.game.Throw;

import java.util.Collections;
import java.util.List;

public class StraightScoringStrategy implements ScoringStrategy {

    private int startOfSequence;

    public StraightScoringStrategy(int startOfSequence) {
        this.startOfSequence = startOfSequence;
    }

    public int score(Throw theThrow) {
        boolean isSmallStraight = isSequential(theThrow);

        return isSmallStraight ? theThrow.sum() : 0;
    }

    private boolean isSequential(Throw theThrow) {

        List<Integer> dice = theThrow.getDice();
        Collections.reverse(dice);
        for (int i = 0; i < dice.size(); i++) {
            if (dice.get(i) != (i + startOfSequence)) {
                return false;
            }
        }
        return true;
    }
}
