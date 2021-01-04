package com.cathe.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class FileLogger extends BaseLogger {
  public FileLogger( String name, String fileName ) throws IOException {
    super(name);
    open(fileName);
  }

  public FileLogger( String name, Level level, String fileName ) throws IOException {
    super(name, level);
    open(fileName);
  }

  public void open( String fileName ) throws IOException {
    FileHandler fileHandler = new FileHandler(fileName);
    fileHandler.setFormatter(new SimpleFormatter());
    logger.addHandler(fileHandler);
  }
}
