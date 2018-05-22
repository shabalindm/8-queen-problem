package ru.jeditty.eigth_queen;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Queen8 {

    /**
     *
     * @param p1
     * @param p2
     * @return true if queens at positions p1 and p2 hit each other
     */
    boolean hit(Position p1, Position p2){
        return p1.x == p2.x
                || p1.y == p2.y
                || p1.x - p2.x == p1.y - p2.y
                || p1.x - p2.x == - (p1.y - p2.y);
    }

    /**
     * @param combination
     * @param p
     * @return true, if queen at position p hits any of the queen from combination
     */
    boolean hit(Combination combination, Position p){
        while (combination != null){
            Position p1 = combination.position;

            if(hit(p1, p)){
                return true;
            }

            combination = combination.next;
        }

        return false;
    }




    Stream<Position> getPositionsAtLine(int x){
        return IntStream.range(0, 8).boxed().
                map(y -> new Position(x, y));
    }


    Stream<Combination> find(){
        Stream<Combination> combinations = getPositionsAtLine(0)
                .map(Combination::new);

        for(int x = 1; x < 8; x++) {
            int finalX = x;
            combinations = combinations
                    .flatMap((Combination combination) ->
                            getSavePositions(combination, finalX)
                                    .map(combination::add));
        }

        return combinations;
    }

    /**
     *
     * @param combination
     * @param x
     * @return positions for the queen at line x, that are not hit by any of the queen from combination
     */
    private Stream<Position> getSavePositions(Combination combination, int x) {
        return getPositionsAtLine(x)
                .filter(position -> ! hit(combination, position));
    }


    public static void main(String[] args) {
        List<Combination> combinations = new Queen8().find().collect(Collectors.toList());

        //assertion: there exist 92 solution of 8 queen problem
        if(92 != combinations.size()){
            throw new RuntimeException();
        }
    }



}

