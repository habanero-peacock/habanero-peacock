package no.karianneberg.yahtzee.game;

import java.util.*;

public class Throw {

    private final List<Integer> dice;

    public Throw(Integer... dice) {
        List<Integer> list = Arrays.asList(dice);
        Collections.sort(list);
        Collections.reverse(list);
        this.dice = Collections.unmodifiableList(list);
    }

    public int getNumberOfDiceWithValue(int value) {
        int number = 0;

        for (Integer val : getDice()) {
            if (val.equals(value)) {
                number++;
            }
        }

        return number;
    }

    public int sum() {
        int sum = 0;
        for (Integer integer : getDice()) {
            sum += integer;
        }
        return sum;
    }

    public Map<Integer, Integer> asMap() {
        HashMap<Integer, Integer> diceInPositions = new HashMap<Integer, Integer>();
        for (int i = 0; i < getDice().size(); i++) {
            diceInPositions.put(i, getDice().get(i));
        }

        return diceInPositions;
    }

    public Throw mergeWith(Throw newThrow, List<Integer> currentlyHeldDice) {
        Map<Integer, Integer> throwMap = newThrow.asMap();
        List<Integer> values = new ArrayList<Integer>();

        for (Integer key : throwMap.keySet()) {
            if (currentlyHeldDice.contains(key)) {
                throwMap.put(key, getDice().get(key));
            }

            values.add(key, throwMap.get(key));
        }

        return new Throw(values.toArray(new Integer[throwMap.size()]));
    }

    public List<Integer> getDice() {
        return new ArrayList<Integer>(dice);
    }
}