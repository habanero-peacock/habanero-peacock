package no.karianneberg.yahtzee.game.scorers;

import no.karianneberg.yahtzee.game.Throw;

import java.util.List;

public class NumberOfAKindScoringStrategy implements ScoringStrategy {

    private int number;

    public NumberOfAKindScoringStrategy(int number) {
        this.number = number;
    }

    public int score(Throw theThrow) {
        List<Integer> dice = theThrow.getDice();

        int numberOfAKindValue = 0;

        for (int i = 0; (i < dice.size() - number + 1) && (numberOfAKindValue <= 0); i++) {
            if (dice.get(i).equals(dice.get(i + number - 1))) {
                numberOfAKindValue = dice.get(i);
            }
        }

        return numberOfAKindValue * number;
    }
}
