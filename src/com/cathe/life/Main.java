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

      RectangularField field = new RectangularField(5, 5);
      for (int i = 0; i < 30; ++i) {
        field.update();
        logger.log(Level.INFO, "Turn: " + (i + 1) + "; Alive: " + field.getNumAlive());
      }

      logger.log(Level.INFO, "Program end");
    } catch (IOException ignored) {
    }
  }
}
