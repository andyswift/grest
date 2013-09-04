package no.iterate

/**
 * Explain the responsibility of this class
 */
class Robot{
    void move(Direction dir){println "Rover: moving $dir"}
}

enum Direction{
    left, right, forward, backward
}
