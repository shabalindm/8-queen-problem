package ru.jeditty.eigth_queen;

/**
 * LinkedList of Positions
 */
class Combination {
    final Position position;
    final Combination next;

    private Combination(Position position, Combination next) {
        this.position = position;
        this.next = next;
    }

    Combination(Position position) {
        this(position, null);
    }

    Combination add(Position p) {
        return new Combination(p, this);
    }

}

