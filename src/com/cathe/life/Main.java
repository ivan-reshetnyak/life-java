package com.cathe.life;

import com.cathe.logging.FileLogger;

import java.io.IOException;
import java.util.logging.Level;

public class Main {
  public static void main( String[] Args ) {
    try {
      FileLogger logger = new FileLogger(Main.class.getName() + ".logger", Level.FINEST,
              "logs/main.log");
      logger.log(Level.INFO, "Program start");
      logger.log(Level.INFO, "Program end");
    } catch (IOException ignored) {
    }
  }
}
