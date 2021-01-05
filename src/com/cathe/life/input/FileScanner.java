package com.cathe.life.input;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileScanner implements Scanner {
  protected FileReader fileReader;
  protected JSONParser jsonParser;

  public FileScanner() {
    jsonParser = new JSONParser();
    fileReader = null;
  }

  public FileScanner( String fileName ) {
    this();
    open(fileName);
  }

  public void open( String fileName ) {
    fileReader = null;
    try {
      fileReader = new FileReader(fileName);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public JSONObject scan() {
    if (fileReader == null)
      return new JSONObject();

    try {
      return (JSONObject) jsonParser.parse(fileReader);
    } catch (ParseException | IOException e) {
      return new JSONObject();
    }
  }
}
