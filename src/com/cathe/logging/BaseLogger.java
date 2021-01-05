package com.cathe.logging;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

class CopyFormatter extends Formatter {
  private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

  @Override
  public String format( LogRecord record ) {
    return String.format(format,
            new Date(record.getMillis()),
                     record.getLevel().getLocalizedName(),
                     record.getMessage());
  }
}

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