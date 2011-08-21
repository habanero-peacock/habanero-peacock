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
        int pairValue = findPair(new ArrayList<Integer>(dice));
        return pairValue;
    }

    private int findPair(List<Integer> dice) {
        if (dice.size() < 2) {
            return -1;
        }

        System.out.println("Checking " + dice.get(0) + " and " + dice.get(1));

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

        System.out.println("firstPairValue = " + firstPairValue);
        List<Integer> newList = new ArrayList<Integer>();
        int numOfMatchesFound = 0;
        for (Integer integer : dice) {
            System.out.println("integer = " + integer);
            if (integer != firstPairValue) {
                System.out.println("Is unequal, adding...");
                newList.add(integer);
            } else {
                if (numOfMatchesFound < 2) {
                    System.out.println("Is equal, accumulating...");
                    numOfMatchesFound++;
                } else {
                    newList.add(integer);
                }

            }
            System.out.println("");
        }

        System.out.println("newList.size() = " + newList.size());

        int secondPairValue = findPair(newList);

        if (firstPairValue < 0 || secondPairValue < 0) {
            return null;
        }

        return new TwoPairs(firstPairValue, secondPairValue);
    }
}