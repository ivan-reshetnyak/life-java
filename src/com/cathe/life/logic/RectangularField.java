package com.cathe.life.logic;

import com.cathe.life.input.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class RectangularField extends Field {
  protected int width, height;
  private static final int[][] deltas = { {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1} };
  private static final Cell.Rule[] commonRules = {
          ( Cell cell ) -> {
            if (cell.isAlive() && (cell.neighbourCount < 2 || cell.neighbourCount > 3))
              return Cell.State.DEAD;
            return cell.getState();
          },
          ( Cell cell ) -> {
            if (!cell.isAlive() && cell.neighbourCount == 3)
              return Cell.State.ALIVE;
            return cell.getState();
          } };
  protected List<Cell.Rule> rules;

  public RectangularField() {
    this(0, 0);
  }

  public RectangularField( int width, int height ) {
    this.width = width;
    this.height = height;
    rules = Arrays.asList(commonRules);

    int size = width * height;
    cells = new ArrayList<>(size);
    for (int i = 0; i < size; ++i)
      cells.add(new Cell());
    link();
  }

  void link() {
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

  @Override
  public boolean fromScanner( Scanner scanner ) {
    JSONObject scanned = scanner.scan();

    if (!scanned.containsKey("width") ||
            !scanned.containsKey("height") ||
            !scanned.containsKey("cells"))
      return false;

    width = Math.toIntExact((long) scanned.get("width"));
    height = Math.toIntExact((long) scanned.get("height"));
    cells = new ArrayList<>(width * height);
    JSONArray cellArray = (JSONArray) scanned.get("cells");
    cellArray.forEach(cell -> cells.add(new Cell((long) cell == 0 ? Cell.State.DEAD : Cell.State.ALIVE)));
    link();
    return true;
  }
}
