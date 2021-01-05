package com.cathe.logging;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;

public class Logger {
  protected java.util.logging.Logger logger;

  public Logger( String name ) {
    logger = java.util.logging.Logger.getLogger(name);
    logger.setUseParentHandlers(false);
  }

  public Logger( String name, Level level ) {
    this(name);
    setLevel(level);
  }

  public void setLevel( Level level ) {
    logger.setLevel(level);
  }
  public void log( Level level, String message ) {
    logger.log(level, message);
  }
  public void addHandler( Handler handler ) {
    logger.addHandler(handler);
  }

  public void addHandler( Handler handler, Formatter formatter ) {
    handler.setFormatter(formatter);
    logger.addHandler(handler);
  }
}