package no.karianneberg.yahtzee.game;

import java.util.Collections;
import java.util.List;

class Round {

    private final int roundNumber;
    private ThrowResultStrategy throwResultStrategy;
    private int numberOfThrows = 0;
    private Throw currentThrow;
    private List<Integer> heldDice;

    static final int MAX_NUMBER_OF_THROWS_PER_ROUND = 3;

    Round(int roundNumber, ThrowResultStrategy throwResultStrategy) {
        this.roundNumber = roundNumber;
        this.throwResultStrategy = throwResultStrategy;
        this.heldDice = Collections.emptyList();
    }

    void throwDice() {
        if (numberOfThrows >= MAX_NUMBER_OF_THROWS_PER_ROUND) {
            throw new YahtzeeException("You cannot throw the dice more than " + MAX_NUMBER_OF_THROWS_PER_ROUND + " times per round!");
        }

        Throw newThrow = throwResultStrategy.throwDice();
        currentThrow = heldDice.isEmpty() ? newThrow : currentThrow.mergeWith(newThrow, heldDice);
        numberOfThrows++;
    }

    void holdDice(List<Integer> heldDice) {
        this.heldDice = heldDice;
    }

    int getRoundNumber() {
        return roundNumber;
    }

    Throw getCurrentThrow() {
        return currentThrow;
    }

    boolean hasNoThrowsYet() {
        return getCurrentThrow() == null;
    }
}
