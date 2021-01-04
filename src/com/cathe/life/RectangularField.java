package com.cathe.life;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class RectangularField extends Field {
  protected int width, height;
  private static final int[][] deltas = { {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, -1} };
  private static final Cell.Rule[] commonRules = {
          ( Cell cell ) -> {
            if (cell.isAlive() && (cell.neighbourCount == 2 || cell.neighbourCount == 3))
              return Cell.State.ALIVE;
            return cell.getState();
          },
          ( Cell cell ) -> {
            if (!cell.isAlive() && cell.neighbourCount == 3)
              return Cell.State.ALIVE;
            return cell.getState();
          },
          ( Cell cell ) -> Cell.State.DEAD };
  protected List<Cell.Rule> rules;

  RectangularField() {
    this(0, 0);
  }

  RectangularField( int width, int height ) {
    this.width = width;
    this.height = height;
    rules = Arrays.asList(commonRules);

    int size = width * height;
    cells = new ArrayList<>(size);
    for (int i = 0; i < size; ++i)
      cells.add(new Cell());
    for (int y = 0; y < height; ++y)
      for (int x = 0; x < width; ++x) {
        Cell curCell = get(x, y);
        curCell.set(rules);
        for (int[] delta : deltas)
          curCell.add(get(x + delta[0], y + delta[1]));
      }
  }

  Cell get( int x, int y ) {
    while (x < 0)
      x += width;
    x %= width;
    while (y < 0)
      y += height;
    y %= height;
    return cells.get(y * width + x);
  }
}
