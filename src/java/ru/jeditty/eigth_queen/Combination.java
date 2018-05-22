package ru.jeditty.eigth_queen;

/**
 * LinkedList of Positions
 */
public class Combination {
    final Position position;
    final Combination next;

    private Combination(Position position, Combination next) {
        this.position = position;
        this.next = next;
    }

   public Combination(Position position) {
        this(position, null);
    }

   public Combination add(Position p) {
        return new Combination(p, this);
    }

}

