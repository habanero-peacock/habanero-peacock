package no.karianneberg.yahtzee;

import no.karianneberg.yahtzee.game.Throw;
import no.karianneberg.yahtzee.game.ThrowResultStrategy;

public class OneThroughFiveStrategy implements ThrowResultStrategy {
    public Throw throwDice() {
        return new Throw(1, 2, 3, 4, 5);
    }
}
