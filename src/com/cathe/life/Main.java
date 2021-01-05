package com.cathe.life;

import com.cathe.logging.CopyFormatter;
import com.cathe.logging.FileLogger;
import com.cathe.logging.Logger;

import java.io.IOException;
import java.util.logging.*;

public class Main {
  public static void main( String[] Args ) {
    try {
      Formatter formatter = new CopyFormatter();
      Logger logger = new Logger(Main.class.getName() + ".logger", Level.FINEST);
      logger.addHandler(new FileHandler("logs/main.log"), formatter);
      Handler consoleHandler = new ConsoleHandler();
      consoleHandler.setLevel(Level.FINEST);
      logger.addHandler(consoleHandler, formatter);
      logger.log(Level.INFO, "Program start");

      logger.log(Level.INFO, "Parsing console arguments...");
      if (Args.length < 2) {
        logger.log(Level.INFO, "...failure!");
        logger.log(Level.SEVERE, "Wrong console argument format!");
        logger.log(Level.INFO, "Expected: <file name> <number of turns simulated>!");
        return;
      }
      String fileName = Args[0];
      int turnsSimulated = Integer.parseInt(Args[1]);
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
        logger.log(Level.FINEST, "Turn: " + (i + 1) + "; Alive: " + field.getNumAlive());
      }

      logger.log(Level.INFO, "Program end");
    } catch (IOException ignored) {
    }
  }
}
