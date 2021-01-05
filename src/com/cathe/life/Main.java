package com.cathe.life;

import com.cathe.life.input.FileScanner;
import com.cathe.life.logic.RectangularField;
import com.cathe.life.output.FileRenderer;
import com.cathe.life.output.RectangularFileRenderer;
import com.cathe.logging.CopyFormatter;
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
      if (Args.length < 3) {
        logger.log(Level.INFO, "...failure!");
        logger.log(Level.SEVERE, "Wrong console argument format!");
        logger.log(Level.INFO, "Expected: <input file name> <number of turns simulated> <output file name>!");
        return;
      }
      String inputFileName = Args[0];
      int turnsSimulated = Integer.parseInt(Args[1]);
      String outputFileName = Args[2];
      logger.log(Level.INFO, "...successful");

      RectangularField field = new RectangularField();
      logger.log(Level.INFO, "Loading field...");
      if (field.fromScanner(new FileScanner(inputFileName)))
        logger.log(Level.INFO, "...successful");
      else {
        logger.log(Level.INFO, "...failure!");
        logger.log(Level.SEVERE, "ERROR: failed to load field!");
        return;
      }

      logger.log(Level.INFO, "Opening output stream...");
      RectangularFileRenderer renderer = new RectangularFileRenderer(field.getWidth(), field.getHeight(),
              outputFileName);
      logger.log(Level.INFO, "...successful");
      logger.log(Level.INFO, "Running simulation...");
      for (int i = 0; i <= turnsSimulated; ++i) {
        logger.log(Level.FINEST, "Turn: " + i + "; Alive: " + field.getNumAlive());
        renderer.render(field);
        field.update();
      }
      logger.log(Level.INFO, "...successful");

      logger.log(Level.INFO, "Program end");
    } catch (IOException ignored) {
    }
  }
}
