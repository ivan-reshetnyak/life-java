package com.cathe.life.logic;

import com.cathe.life.input.Scanner;

import java.util.List;

public abstract class Field {
  protected List<Cell> cells;

  public abstract boolean fromScanner( Scanner scanner );

  public void update() {
    for (Cell cell : cells)
      cell.update();
    numAlive = 0;
    for (Cell cell : cells) {
      cell.advanceState();
      if (cell.isAlive())
        ++numAlive;
    }
  }

  public int getNumAlive() {
    return numAlive;
  }
  public List<Cell> getCells() {
    return cells;
  }

  private int numAlive;
}
