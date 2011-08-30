package no.karianneberg.yahtzee.game.scorers;

import no.karianneberg.yahtzee.game.Throw;

import java.util.List;

public class FullHouseScoringStrategy implements ScoringStrategy {

    public int score(Throw theThrow) {

        int pairValue = 0;
        int threeOfAKindValue = 0;

        List<Integer> dice = theThrow.getDice();
        if(fullHouseWithPairLast(dice)) {
            threeOfAKindValue = dice.get(0);
            pairValue = dice.get(3);
        } else if(fullHouseWithPairFirst(dice)) {
            pairValue = dice.get(0);
            threeOfAKindValue = dice.get(2);
        }

        return (pairValue * 2) + (threeOfAKindValue * 3);
    }

    private boolean fullHouseWithPairFirst(List<Integer> dice) {
        return dice.get(0).equals(dice.get(1)) && dice.get(2).equals(dice.get(4));
    }

    private boolean fullHouseWithPairLast(List<Integer> dice) {
        return dice.get(0).equals(dice.get(2)) && dice.get(3).equals(dice.get(4));
    }

}
