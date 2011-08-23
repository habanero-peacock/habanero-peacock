package no.karianneberg.yahtzee;

public class YahtzeeGame {
    private ThrowResultStrategy throwResultStrategy;
    private Throw currentThrow;
    private int currentScore;

    public YahtzeeGame(ThrowResultStrategy throwResultStrategy) {

        this.throwResultStrategy = throwResultStrategy;
    }

    public void throwDice() {
        this.currentThrow = throwResultStrategy.throwDice();
    }

    public void holdDice() {
    }

    public int scoreFor(Combination combo) {

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

        return score;
    }

    public int finalScore() {
        return currentScore;
    }
}
