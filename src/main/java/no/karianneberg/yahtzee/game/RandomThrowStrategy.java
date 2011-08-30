package no.karianneberg.yahtzee.game;

public class RandomThrowStrategy implements ThrowResultStrategy {
    public Throw throwDice() {
        return new Throw(randomDie(), randomDie(), randomDie(), randomDie(), randomDie());
    }

    private int randomDie() {
        return (int) (Math.random() * 6) + 1;
    }
}
