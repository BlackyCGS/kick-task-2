package by.glesap.task2.reader.impl;

import by.glesap.task2.reader.CustomFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CustomFileReaderImpl implements CustomFileReader {
  private static final Logger logger = LoggerFactory.getLogger(CustomFileReaderImpl.class);
  private static final String FILENAME = "data/input.txt";

  @Override
  public String readFile(String filename) throws IOException {
    logger.info("readFile() called");
    String text;
    List<String> list;
    try {
      Path path = Paths.get(filename);
      if (!Files.exists(path)) {
        path = Paths.get(FILENAME);
      }
      list = Files.readAllLines(path);
      StringBuilder sb = new StringBuilder();
      for (String line : list) {
        sb.append(line);
        if (!list.getLast().equals(line)) {
          sb.append("\n");
        }
      }
      text = sb.toString();
      logger.info("File {} read successfully", FILENAME);

    } catch (IOException e) {
      throw new IOException(e);
    }
    return text;
  }
}
