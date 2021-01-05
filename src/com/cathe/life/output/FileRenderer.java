package com.cathe.life.output;

import com.cathe.life.logic.Cell;
import com.cathe.life.logic.Field;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileRenderer implements Renderer {
  public static final String
          defaultAliveSymbol = "1",
          defaultDeadSymbol = "0";

  public FileRenderer() {
    this(defaultAliveSymbol, defaultDeadSymbol);
  }

  public FileRenderer( String aliveSymbol, String deadSymbol ) {
    this.aliveSymbol = aliveSymbol;
    this.deadSymbol = deadSymbol;
    this.fileWriter = null;
  }

  public FileRenderer( String fileName ) throws IOException {
    this();
    open(fileName);
  }

  public FileRenderer( String aliveSymbol, String deadSymbol, String fileName ) throws IOException {
    this(aliveSymbol, deadSymbol);
    open(fileName);
  }

  public void open( String fileName ) throws IOException {
    this.fileWriter = null;
    this.fileWriter = new FileWriter(fileName);
  }

  @Override
  public void render( Field field ) {
    try {
      List<Cell> cells = field.getCells();
      for (Cell cell : cells)
        if (cell.isAlive())
          printAlive();
        else
          printDead();
      fileWriter.flush();
    } catch (IOException ignored) {}
  }

  protected void printAlive() throws IOException {
    fileWriter.write(aliveSymbol);
  }

  protected void printDead() throws IOException {
    fileWriter.write(deadSymbol);
  }

  protected String aliveSymbol, deadSymbol;
  protected FileWriter fileWriter;
}
