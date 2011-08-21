package no.karianneberg.yahtzee;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class YahtzeeGameTest {
    private ThrowResultStrategy resultStrategy;
    private YahtzeeGame game;

    @Before
    public void setup() {
        resultStrategy = mock(ThrowResultStrategy.class);
        game = new YahtzeeGame(resultStrategy);
    }

    @Test
    public void noPointsWhenGameHasNotStarted() throws Exception {
        assertThat(game.finalScore()).isZero();
    }

    @Test
    public void allOnesGivesFivePointsForOnes() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));

        game.throwDice();
        int score = game.scoreFor(Combination.ONES);

        assertThat(score).isEqualTo(5);
    }

    @Test
    public void allTwosGivesTenPointsForTwos() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(2, 2, 2, 2, 2));

        game.throwDice();
        int score = game.scoreFor(Combination.TWOS);

        assertThat(score).isEqualTo(10);
    }

    @Test
    public void allThreesGivesFifteenPointsForThrees() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(3, 3, 3, 3, 3));

        game.throwDice();
        int score = game.scoreFor(Combination.THREES);

        assertThat(score).isEqualTo(15);
    }

    @Test
    public void allFoursGivesTwentyPointsForFours() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(4, 4, 4, 4, 4));

        game.throwDice();
        int score = game.scoreFor(Combination.FOURS);

        assertThat(score).isEqualTo(20);
    }

    @Test
    public void allFivesGivesTwentyFivePointsForFives() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(5, 5, 5, 5, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.FIVES);

        assertThat(score).isEqualTo(25);
    }

    @Test
    public void allSixesGivesThirtyPointsForSixes() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(6, 6, 6, 6, 6));

        game.throwDice();
        int score = game.scoreFor(Combination.SIXES);

        assertThat(score).isEqualTo(30);
    }

    @Test
    public void twoOnesAndRestUnequalGivesTwoPointsForOnePair() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 2, 3, 4));

        game.throwDice();
        int score = game.scoreFor(Combination.ONE_PAIR);

        assertThat(score).isEqualTo(2);
    }

    @Test
    public void twoOnesAndTwoFivesGivesTenPointsForOnePair() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 5, 3, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.ONE_PAIR);

        assertThat(score).isEqualTo(10);
    }

    @Test
    public void smallStraightGivesZeroPointsForOnePair() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 2, 3, 4, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.ONE_PAIR);

        assertThat(score).isEqualTo(0);
    }

    @Test
    public void twoOnesAndTwoFivesGivesTwelvePointsForTwoPairs() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(5, 1, 5, 3, 1));

        game.throwDice();
        int score = game.scoreFor(Combination.TWO_PAIRS);

        assertThat(score).isEqualTo(12);
    }

    @Test
    public void fourOnesGivesFourPointsForTwoPairs() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 4, 1, 1));

        game.throwDice();
        int score = game.scoreFor(Combination.TWO_PAIRS);

        assertThat(score).isEqualTo(4);
    }
}
