package no.karianneberg.yahtzee.game;

public interface Scorer {
    int score(Combination combo, Throw theThrow);
}
