package by.glesap.task2.parser.impl;

import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.TextCompositeImpl;
import by.glesap.task2.enums.TextComponentType;
import by.glesap.task2.parser.CustomParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends BaseParser {
  private static final Logger logger = LoggerFactory.getLogger(ParagraphParser.class);
  /**
   * Matches any sentence.
   */
  private static final String SENTENCE_MATCHER = "[^.!?]*[.!?);]+";

  @Override
  public void parse(String text, TextComposite parent) {
    logger.info("ParagraphParser.parse(): start");
    Pattern pattern = Pattern.compile(SENTENCE_MATCHER);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      TextComposite composite = new TextCompositeImpl(TextComponentType.SENTENCE);
      parent.add(composite);
      CustomParser nextParser = this.getNextParser();
      String sentence = matcher.group();
      if (nextParser != null) {
        logger.info("ParagraphParser.parse(): delegating to next parser");
        nextParser.parse(sentence, composite);
      }
    }
  }
}
