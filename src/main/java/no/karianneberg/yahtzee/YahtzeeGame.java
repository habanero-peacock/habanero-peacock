package no.karianneberg.yahtzee;

import java.util.*;

public class YahtzeeGame {
    private ThrowResultStrategy throwResultStrategy;
    private Set<Combination> scoredCombinations;

    private Throw currentThrow;
    private int currentScore;
    private int currentRoundNumber;
    private List<Integer> currentlyHeldDice;
    private int currentNumberOfThrowsInThisRound = 0;

    public static final int NUMBER_OF_ROUNDS = Combination.values().length;
    private static final int MAX_NUMBER_OF_THROWS_PER_ROUND = 3;

    public YahtzeeGame(ThrowResultStrategy throwResultStrategy) {
        this.throwResultStrategy = throwResultStrategy;
        this.currentlyHeldDice = new ArrayList<Integer>();
        this.currentRoundNumber = 1;
        this.scoredCombinations = new HashSet<Combination>();
    }

    public void throwDice() {
        if(currentNumberOfThrowsInThisRound >= MAX_NUMBER_OF_THROWS_PER_ROUND) {
            throw new YahtzeeException("You cannot throw the dice more than " + MAX_NUMBER_OF_THROWS_PER_ROUND + " times per round!");
        }

        Throw newThrow = throwResultStrategy.throwDice();
        currentThrow = currentlyHeldDice.isEmpty() ? newThrow : currentThrow.mergeWith(newThrow, currentlyHeldDice);
        currentNumberOfThrowsInThisRound++;
    }

    public void holdDice(Integer... positions) {
        this.currentlyHeldDice = Arrays.asList(positions);
    }

    public int scoreFor(Combination combo) {
        if(currentThrow == null) {
            throw  new YahtzeeException("You have to throw at least once before you score");
        }

        if(scoredCombinations.contains(combo)) {
            throw new YahtzeeException("This combination has already been taken!");
        }

        scoredCombinations.add(combo);

        int num;
        int score = 0;

        switch (combo) {
            case ONES:
                num = currentThrow.getNumberOfDiceWithValue(1);
                score = num;
                break;
            case TWOS:
                num = currentThrow.getNumberOfDiceWithValue(2);
                score = num * 2;
                break;
            case THREES:
                num = currentThrow.getNumberOfDiceWithValue(3);
                score = num * 3;
                break;
            case FOURS:
                num = currentThrow.getNumberOfDiceWithValue(4);
                score = num * 4;
                break;
            case FIVES:
                num = currentThrow.getNumberOfDiceWithValue(5);
                score = num * 5;
                break;
            case SIXES:
                num = currentThrow.getNumberOfDiceWithValue(6);
                score = num * 6;
                break;
            case ONE_PAIR:
                num = currentThrow.findPair();
                score = num > 0 ? num * 2 : 0;
                break;
            case TWO_PAIRS:
                TwoPairs result = currentThrow.findTwoPairs();
                if (result == null) {
                    score = 0;
                } else {
                    score = (result.getFirst() * 2) + (result.getSecond() * 2);
                }
                break;
            case THREE_OF_A_KIND:
                num = currentThrow.findThreeOfAKind();
                score = num > 0 ? num * 3 : 0;
                break;
            case FOUR_OF_A_KIND:
                num = currentThrow.findFourOfAKind();
                score = num > 0 ? num * 4 : 0;
                break;
            case FULL_HOUSE:
                FullHouse fullHouse = currentThrow.findFullHouse();
                if (fullHouse == null) {
                    score = 0;
                } else {
                    score = (fullHouse.getFirst() * 2) + (fullHouse.getSecond() * 3);
                }
                break;
            case SMALL_STRAIGHT:
                score = currentThrow.isSmallStraight() ? 15 : 0;
                break;
            case LARGE_STRAIGHT:
                score = currentThrow.isLargeStraight() ? 20 : 0;
                break;
            case CHANCE:
                score = currentThrow.sum();
                break;
            case YAHTZEE:
                score = currentThrow.isYahtzee() ? 50 : 0;
                break;
        }

        currentScore += score;
        currentRoundNumber++;
        currentNumberOfThrowsInThisRound = 0;
        currentlyHeldDice = new ArrayList<Integer>();
        currentThrow = null;

        return score;
    }

    public int finalScore() {
        return currentScore;
    }

    public Map<Integer, Integer> getCurrentThrow() {
        return currentThrow.asMap();
    }

    public boolean isOver() {
        return currentRoundNumber > NUMBER_OF_ROUNDS;
    }

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public Set<Combination> getRemainingCombos() {
        Set<Combination> allCombos = new HashSet<Combination>(Arrays.asList(Combination.values()));
        allCombos.removeAll(scoredCombinations);

        return new HashSet<Combination>(allCombos);
    }
}
