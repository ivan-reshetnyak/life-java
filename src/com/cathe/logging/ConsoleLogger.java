package com.cathe.logging;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

public class ConsoleLogger extends Logger {
  public ConsoleLogger( String name ) throws IOException {
    super(name);
    init();
  }

  public ConsoleLogger( String name, Level level ) throws IOException {
    super(name, level);
    init();
  }

  private void init() {
    ConsoleHandler fileHandler = new ConsoleHandler();
    fileHandler.setFormatter(new CopyFormatter());
    logger.addHandler(fileHandler);
  }
}
