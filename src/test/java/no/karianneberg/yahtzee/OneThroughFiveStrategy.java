package no.karianneberg.yahtzee;

public class OneThroughFiveStrategy implements ThrowResultStrategy {
    public Throw throwDice() {
        return new Throw(1, 2, 3, 4, 5);
    }
}
