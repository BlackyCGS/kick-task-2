package by.glesap.task2.parser.impl;

import by.glesap.task2.composite.TextComponent;
import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.CharacterLeaf;
import by.glesap.task2.composite.impl.TextCompositeImpl;
import by.glesap.task2.enums.TextComponentType;
import by.glesap.task2.parser.CustomParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends BaseParser {

  private static final Logger logger = LoggerFactory.getLogger(LexemeParser.class);
  /**
   * Matches any word without punctuation
   */
  private static final String WORD_MATCHER = "[\\p{Ps}\\p{Po}]*[\\p{L}']+[\\p{Pe}\"]*";
  /**
   * Matches any word that has or has not any punctuation, brackets, apostrophe
   */
  private static final String WORD_WITH_PUNCTUATION_MATCHER = "[\\p{Po}|\\p{Ps}]*[\\p{L}']+[\\p{Po}|\\p{Pe}]*";

  @Override
  public void parse(String text, TextComposite parent) {
    logger.info("LexemeParser.parse(): start");
    Pattern pattern = Pattern.compile(WORD_WITH_PUNCTUATION_MATCHER);
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      String word = matcher.group();
      if (word.matches(WORD_MATCHER)) {
        TextComposite composite = new TextCompositeImpl(TextComponentType.WORD);
        parent.add(composite);
        CustomParser next = getNextParser();
        if (next != null) {
          logger.info("LexemeParser.parse(): delegating to next parser");
          next.parse(text, composite);
        }
      }
      else {
        Pattern lexemePattern = Pattern.compile(WORD_MATCHER);
        Matcher lexemeMatcher = lexemePattern.matcher(word);
        int punctuationIndex = 0;
        if (lexemeMatcher.find()) {
          String word2 = lexemeMatcher.group();
          punctuationIndex = word2.length();
          TextComposite composite = new TextCompositeImpl(TextComponentType.WORD);
          parent.add(composite);
          CustomParser next = getNextParser();
          if (next != null) {
            logger.info("LexemeParser.parse(): delegating to next parser after " +
                    "punctuation match");
            next.parse(word2, composite);
          }
        }
        for (int i = punctuationIndex; i < word.length(); i++) {

          logger.info("LexemeParser.parse(): created leaf for punctuation");
          TextComponent punctuationLeaf =
                  new CharacterLeaf(TextComponentType.PUNCTUATION, word.charAt(i));
          parent.add(punctuationLeaf);
        }
      }
    }
  }
}
