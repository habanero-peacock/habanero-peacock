package no.karianneberg.yahtzee;

import no.karianneberg.yahtzee.game.Combination;
import no.karianneberg.yahtzee.game.YahtzeeGame;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static no.karianneberg.yahtzee.game.Combination.*;
import static org.fest.assertions.Assertions.assertThat;


public class AcceptanceTest {

    @Test
    public void onePlayer() {

        YahtzeeGame game = new YahtzeeGame(new OneThroughFiveStrategy());

        Map<Combination, Integer> scores = new HashMap<Combination, Integer>();
        scores.put(ONES, 1);
        scores.put(TWOS, 2);
        scores.put(THREES, 3);
        scores.put(FOURS, 4);
        scores.put(FIVES, 5);
        scores.put(SIXES, 0);
        scores.put(ONE_PAIR, 0);
        scores.put(TWO_PAIRS, 0);
        scores.put(THREE_OF_A_KIND, 0);
        scores.put(FOUR_OF_A_KIND, 0);
        scores.put(FULL_HOUSE, 0);
        scores.put(SMALL_STRAIGHT, 15);
        scores.put(LARGE_STRAIGHT, 0);
        scores.put(YAHTZEE, 0);
        scores.put(CHANCE, 15);

        for (Combination combo : scores.keySet()) {
            game.throwDice();
            game.holdDice();
            game.throwDice();
            int score = game.scoreFor(combo);
            assertThat(score).isEqualTo(scores.get(combo));
        }

        assertThat(game.finalScore()).isEqualTo(45);
        assertThat(game.isOver()).isTrue();
    }
}