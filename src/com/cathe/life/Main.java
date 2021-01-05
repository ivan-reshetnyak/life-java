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

      logger.log(Level.INFO, "Parsing console arguments...");
      if (Args.length < 3) {
        logger.log(Level.INFO, "...failure!");
        logger.log(Level.SEVERE, "Wrong console argument format!");
        logger.log(Level.INFO, "Expected: <file name> <number of turns simulated>!");
        return;
      }
      String fileName = Args[1];
      int turnsSimulated = Integer.parseInt(Args[2]);
      logger.log(Level.INFO, "...successful");

      RectangularField field = new RectangularField();
      logger.log(Level.INFO, "Loading field...");
      if (field.fromScanner(new FileScanner(fileName)))
        logger.log(Level.INFO, "...successful");
      else {
        logger.log(Level.INFO, "...failure!");
        logger.log(Level.SEVERE, "ERROR: failed to load field!");
        return;
      }

      for (int i = 0; i < turnsSimulated; ++i) {
        field.update();
        logger.log(Level.INFO, "Turn: " + (i + 1) + "; Alive: " + field.getNumAlive());
      }

      logger.log(Level.INFO, "Program end");
    } catch (IOException ignored) {
    }
  }
}
