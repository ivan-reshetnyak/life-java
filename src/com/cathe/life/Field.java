package com.cathe.life;

import java.util.List;

public abstract class Field {
  protected List<Cell> cells;

  abstract boolean fromScanner( Scanner scanner );

  private int numAlive;

  void update() {
    for (Cell cell : cells)
      cell.update();
    numAlive = 0;
    for (Cell cell : cells) {
      cell.advanceState();
      if (cell.isAlive())
        ++numAlive;
    }
  }

  int getNumAlive() {
    return numAlive;
  }
}
