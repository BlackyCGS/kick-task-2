package by.glesap.task2.parser.impl;

import by.glesap.task2.parser.CustomParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseParser implements CustomParser {
  private static final Logger logger = LoggerFactory.getLogger(BaseParser.class);
  private CustomParser nextParser;

  @Override
  public void setNextParser(CustomParser nextParser) {
    logger.info("Setting Next Parser");
    this.nextParser = nextParser;
  }

  CustomParser getNextParser() {
    return nextParser;
  }

}
