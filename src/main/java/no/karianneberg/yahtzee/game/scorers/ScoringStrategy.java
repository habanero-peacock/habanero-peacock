package no.karianneberg.yahtzee.game.scorers;

import no.karianneberg.yahtzee.game.Throw;

public interface ScoringStrategy {
    int score(Throw theThrow);
}
