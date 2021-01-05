package com.cathe.life.output;

import com.cathe.life.logic.Cell;
import com.cathe.life.logic.Field;

import java.io.IOException;
import java.util.List;

public class RectangularFileRenderer extends FileRenderer {
  public void set( int width, int height ) {
    this.width = width;
    this.height = height;
  }

  public RectangularFileRenderer( int width, int height ) {
    super();
    set(width, height);
  }

  public RectangularFileRenderer( int width, int height, String aliveSymbol, String deadSymbol ) {
    super(aliveSymbol, deadSymbol);
    set(width, height);
  }

  public RectangularFileRenderer( int width, int height, String fileName ) throws IOException {
    super(fileName);
    set(width, height);
  }

  public RectangularFileRenderer( int width, int height, String aliveSymbol, String deadSymbol, String fileName ) throws IOException {
    super(aliveSymbol, deadSymbol, fileName);
    set(width, height);
  }

  @Override
  public void render( Field field ) {
    try {
      List<Cell> cells = field.getCells();
      for (int y = 0; y < height; ++y) {
        for (int x = 0; x < width; ++x) {
          if (cells.get(y * width + x).isAlive())
            printAlive();
          else
            printDead();
        }
        fileWriter.write("\n");
      }
      fileWriter.write("\n");
      fileWriter.flush();
    } catch (IOException ignored) {}
  }

  protected int width, height;
}
