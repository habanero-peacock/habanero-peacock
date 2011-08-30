package no.karianneberg.yahtzee.game;

import no.karianneberg.yahtzee.game.scorers.StrategyScorer;

import java.util.*;

public class YahtzeeGame {
    private Set<Combination> scoredCombinations;

    private Scorer scorer;
    private int currentScore;
    private Queue<Round> remainingRounds;

    public static final int NUMBER_OF_ROUNDS = Combination.values().length;

    public YahtzeeGame(ThrowResultStrategy throwResultStrategy) {
        this.scoredCombinations = new HashSet<Combination>();
        this.scorer = new StrategyScorer();

        this.remainingRounds = new LinkedList<Round>();
        for(int roundNumber = 1; roundNumber <= NUMBER_OF_ROUNDS; roundNumber++) {
            remainingRounds.add(new Round(roundNumber, throwResultStrategy));
        }
    }

    public void throwDice() {
        getCurrentRound().throwDice();
    }

    public void holdDice(Integer... positions) {
        this.getCurrentRound().holdDice(Arrays.asList(positions));
    }

    public int scoreFor(Combination combo) {
        if (getCurrentRound().hasNoThrowsYet()) {
            throw new YahtzeeException("You have to throw at least once before you score");
        }

        if (scoredCombinations.contains(combo)) {
            throw new YahtzeeException("This combination has already been scored!");
        }

        scoredCombinations.add(combo);

        int score = scorer.score(combo, getCurrentRound().getCurrentThrow());

        currentScore += score;
        remainingRounds.remove();

        return score;
    }

    public int finalScore() {
        return currentScore;
    }

    public Map<Integer, Integer> getCurrentThrowAsMap() {
        return getCurrentRound().getCurrentThrow().asMap();
    }

    public boolean isOver() {
        return remainingRounds.isEmpty();
    }

    public int getCurrentRoundNumber() {
        return getCurrentRound().getRoundNumber();
    }

    public Set<Combination> getRemainingCombos() {
        Set<Combination> allCombos = new HashSet<Combination>(Arrays.asList(Combination.values()));
        allCombos.removeAll(scoredCombinations);

        return new HashSet<Combination>(allCombos);
    }

    private Round getCurrentRound() {
        return remainingRounds.peek();
    }
}