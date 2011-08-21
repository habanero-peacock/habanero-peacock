package no.karianneberg.yahtzee;

public class YahtzeeGame {
    private ThrowResultStrategy throwResultStrategy;
    private Throw currentThrow;

    public YahtzeeGame(ThrowResultStrategy throwResultStrategy) {

        this.throwResultStrategy = throwResultStrategy;
    }

    public void throwDice() {
        this.currentThrow = throwResultStrategy.throwDice();
    }

    public void holdDice() {
    }

    public int scoreFor(Combination combo) {

        int num = 0;

        switch(combo) {
            case ONES:
                num = currentThrow.getNumberOfDiceWithValue(1);
                return num * 1;
            case TWOS:
                num = currentThrow.getNumberOfDiceWithValue(2);
                return num * 2;
            case THREES:
                num = currentThrow.getNumberOfDiceWithValue(3);
                return num * 3;
            case FOURS:
                num = currentThrow.getNumberOfDiceWithValue(4);
                return num * 4;
            case FIVES:
                num = currentThrow.getNumberOfDiceWithValue(5);
                return num * 5;
            case SIXES:
                num = currentThrow.getNumberOfDiceWithValue(6);
                return num * 6;
            case ONE_PAIR:
                num = currentThrow.findPair();
                return num > 0 ? num * 2 : 0;
            case TWO_PAIRS:
                TwoPairs result = currentThrow.findTwoPairs();
                if(result == null) return 0;
                return (result.getFirst() * 2) + (result.getSecond() * 2);
        }
        return num;
    }

    public int finalScore() {
        return 0;  //To change body of created methods use File | Settings | File Templates.
    }
}