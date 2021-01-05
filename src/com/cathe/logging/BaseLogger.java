package com.cathe.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseLogger {
  protected Logger logger;

  protected BaseLogger( String name ) {
    logger = Logger.getLogger(name);
    logger.setUseParentHandlers(false);
  }

  protected BaseLogger( String name, Level level ) {
    this(name);
    setLevel(level);
  }

  public void setLevel( Level level ) {
    logger.setLevel(level);
  }

  public void log( Level level, String message ) {
    logger.log(level, message);
  }
}