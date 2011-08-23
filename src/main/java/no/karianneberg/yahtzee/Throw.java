package no.karianneberg.yahtzee;

import java.util.*;

public class Throw {

    private List<Integer> dice;

    public Throw(Integer... dice) {
        this.dice = Arrays.asList(dice);
    }

    public int getNumberOfDiceWithValue(int value) {
        int number = 0;

        for (Integer val : dice) {
            if (val.equals(value)) {
                number++;
            }
        }

        return number;
    }

    public int findPair() {
        Collections.sort(dice);
        Collections.reverse(dice);
        return findPair(new ArrayList<Integer>(dice));
    }

    private int findPair(List<Integer> dice) {
        if (dice.size() < 2) {
            return -1;
        }

        if (dice.get(0).equals(dice.get(1))) {
            return dice.get(0);
        } else {
            dice.remove(0);
            return findPair(dice);
        }
    }

    public TwoPairs findTwoPairs() {
        Collections.sort(dice);
        Collections.reverse(dice);
        ArrayList<Integer> list = new ArrayList<Integer>(dice);
        int firstPairValue = findPair(list);

        List<Integer> newList = new ArrayList<Integer>();
        int numOfMatchesFound = 0;
        for (Integer integer : dice) {
            if (integer != firstPairValue) {
                newList.add(integer);
            } else {
                if (numOfMatchesFound < 2) {
                    numOfMatchesFound++;
                } else {
                    newList.add(integer);
                }

            }
        }

        int secondPairValue = findPair(newList);

        if (firstPairValue < 0 || secondPairValue < 0) {
            return null;
        }

        return new TwoPairs(firstPairValue, secondPairValue);
    }

    public int findThreeOfAKind() {
        Collections.sort(dice);
        Collections.reverse(dice);
        return findThreeOfAKind(new ArrayList<Integer>(dice));
    }

    private int findThreeOfAKind(List<Integer> dice) {
        if (dice.size() < 3) {
            return -1;
        }

        if (dice.get(0).equals(dice.get(2))) {
            return dice.get(0);
        } else {
            dice.remove(0);
            return findThreeOfAKind(dice);
        }
    }

    public int findFourOfAKind() {
        Collections.sort(dice);
        Collections.reverse(dice);
        return findFourOfAKind(new ArrayList<Integer>(dice));
    }

    private int findFourOfAKind(List<Integer> dice) {
        if (dice.size() < 4) {
            return -1;
        }

        if (dice.get(0).equals(dice.get(3))) {
            return dice.get(0);
        } else {
            dice.remove(0);
            return findThreeOfAKind(dice);
        }
    }

    public FullHouse findFullHouse() {
        Collections.sort(dice);
        Collections.reverse(dice);
        ArrayList<Integer> list = new ArrayList<Integer>(dice);
        int pairValue = findPair(list);

        List<Integer> newList = new ArrayList<Integer>();
        int numOfMatchesFound = 0;
        for (Integer integer : dice) {
            if (integer != pairValue) {
                newList.add(integer);
            } else {
                if (numOfMatchesFound < 2) {
                    numOfMatchesFound++;
                } else {
                    newList.add(integer);
                }

            }
        }

        int threeOfAKindValue = findThreeOfAKind(newList);

        if (pairValue < 0 || threeOfAKindValue < 0) {
            return null;
        }

        return new FullHouse(pairValue, threeOfAKindValue);
    }

    public boolean isSmallStraight() {
        Collections.sort(dice);
        for(int i = 0; i < dice.size(); i++) {
            if(dice.get(i) != (i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isLargeStraight() {
        Collections.sort(dice);
        for(int i = 0; i < dice.size(); i++) {
            if(dice.get(i) != (i + 2)) {
                return false;
            }
        }
        return true;
    }

    public int sum() {
        int sum = 0;
        for(Integer integer : dice) {
            sum += integer;
        }
        return sum;
    }

    public boolean isYahtzee() {
        Collections.sort(dice);
        return dice.get(0).equals(dice.get(4));
    }
}