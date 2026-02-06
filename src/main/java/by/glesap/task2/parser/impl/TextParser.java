package by.glesap.task2.parser.impl;

import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.TextCompositeImpl;
import by.glesap.task2.enums.TextComponentType;
import by.glesap.task2.parser.CustomParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser extends BaseParser {
  private static final Logger logger = LoggerFactory.getLogger(TextParser.class);
  /**
   * Matches text after line terminators (\n) till next end of line is encountered
   */
  private static final String PARAGRAPH_MATCHER = "(?m)^.*$";

  @Override
  public void parse(String text, TextComposite parent) {
    logger.info("TextParser.parse(): start");
    Pattern pattern = Pattern.compile(PARAGRAPH_MATCHER);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      TextComposite composite = new TextCompositeImpl(TextComponentType.PARAGRAPH);
      parent.add(composite);
      CustomParser nextParser = this.getNextParser();
      String paragraph = matcher.group();
      if (nextParser != null) {
        logger.info("TextParser.parse(): delegating to next parser");
        nextParser.parse(paragraph, composite);
      }
    }
  }
}
