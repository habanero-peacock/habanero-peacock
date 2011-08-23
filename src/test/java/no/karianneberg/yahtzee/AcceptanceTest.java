package no.karianneberg.yahtzee;

import org.junit.Test;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static no.karianneberg.yahtzee.Combination.*;
import static no.karianneberg.util.Maps.asMap;
import static no.karianneberg.util.Maps.entry;


public class AcceptanceTest {

    @Test
    public void onePlayer() {

        YahtzeeGame game = new YahtzeeGame(new OneThroughFiveStrategy());

        Map<Combination, Integer> scores = asMap(
                entry(ONES, 1),
                entry(TWOS, 2),
                entry(THREES, 3),
                entry(FOURS, 4),
                entry(FIVES, 5),
                entry(SIXES, 0),
                entry(ONE_PAIR, 0),
                entry(TWO_PAIRS, 0),
                entry(THREE_OF_A_KIND, 0),
                entry(FOUR_OF_A_KIND, 0),
                entry(FULL_HOUSE, 0),
                entry(SMALL_STRAIGHT, 15),
                entry(LARGE_STRAIGHT, 0),
                entry(YAHTZEE, 0),
                entry(CHANCE, 15)
        );

        for (Combination combo : scores.keySet()) {
            game.throwDice();
            game.holdDice();
            game.throwDice();
            int score = game.scoreFor(combo);
            assertThat(score).isEqualTo(scores.get(combo));
        }

        assertThat(game.finalScore()).isEqualTo(45);
    }
}