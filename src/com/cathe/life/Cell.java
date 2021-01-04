package com.cathe.life;

import java.util.LinkedList;

class Cell {
  enum State {
    DEAD, ALIVE
  }
  private static State DEFAULT_STATE = State.DEAD;
  private State state, nextState;
  private LinkedList<Cell> neighbours;

  @FunctionalInterface
  interface Rule {
    State update( int neighboursCount );
  }
  private LinkedList<Rule> rules;

  Cell() {
    neighbours = new LinkedList<>();
    nextState = state = DEFAULT_STATE;
  }

  Cell( State startingState ) {
    this();
    nextState = state = startingState;
  }

  Cell add( Cell newNeighbour ) {
    neighbours.add(newNeighbour);
    return this;
  }

  boolean isAlive() {
    return state == State.ALIVE;
  }

  int countNeighbours() {
    int count = 0;
    for (Cell neighbour : neighbours) {
      if (neighbour.isAlive()) {
        ++count;
      }
    }
    return count;
  }

  void advanceState() {
    state = nextState;
  }

  void update() {
    State newState;
    int neighboursCount = countNeighbours();
    for (Rule rule : rules) {
      newState = rule.update(neighboursCount);
      if (newState != nextState) {
        nextState = newState;
        return;
      }
    }
  }

  Cell add( Rule newRule ) {
    rules.add(newRule);
    return this;
  }
}
