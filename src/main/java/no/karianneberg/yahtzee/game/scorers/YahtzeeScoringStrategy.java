package no.karianneberg.yahtzee.game.scorers;

import no.karianneberg.yahtzee.game.Throw;

public class YahtzeeScoringStrategy implements ScoringStrategy {
    public int score(Throw theThrow) {
        return theThrow.getDice().get(0).equals(theThrow.getDice().get(4)) ? 50 : 0;
    }
}
