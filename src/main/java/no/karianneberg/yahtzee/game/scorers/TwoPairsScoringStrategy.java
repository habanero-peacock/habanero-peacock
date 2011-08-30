package no.karianneberg.yahtzee.game.scorers;

import no.karianneberg.yahtzee.game.Throw;

import java.util.List;

public class TwoPairsScoringStrategy implements ScoringStrategy {

    public int score(Throw theThrow) {

        int first = 0;
        int second = 0;

        List<Integer> theDice = theThrow.getDice();
        
        if (leftoverDieLast(theDice)) {
            first = theDice.get(0);
            second = theDice.get(2);
        } else if (leftoverDieInTheMiddle(theDice)) {
            first = theDice.get(0);
            second = theDice.get(3);
        } else if (leftoverDieAtTheEnd(theDice)) {
            first = theDice.get(1);
            second = theDice.get(3);
        }

        return (first * 2) + (second * 2);
    }

    private boolean leftoverDieAtTheEnd(List<Integer> theDice) {
        return theDice.get(1).equals(theDice.get(2)) && theDice.get(3).equals(theDice.get(4));
    }

    private boolean leftoverDieInTheMiddle(List<Integer> theDice) {
        return theDice.get(0).equals(theDice.get(1)) && theDice.get(3).equals(theDice.get(4));
    }

    private boolean leftoverDieLast(List<Integer> theDice) {
        return theDice.get(0).equals(theDice.get(1)) && theDice.get(2).equals(theDice.get(3));
    }
}
