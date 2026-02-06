package by.glesap.task2.parser.impl;

import by.glesap.task2.composite.TextComponent;
import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.CharacterLeaf;
import by.glesap.task2.enums.TextComponentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordParser extends BaseParser {
  private static final Logger logger = LoggerFactory.getLogger(WordParser.class);

  @Override
  public void parse(String text, TextComposite parent) {
    logger.info("WordParser.parse(): start");
    for (Character c : text.toCharArray()) {
      TextComponent characterLeaf = new CharacterLeaf(TextComponentType.SYMBOL, c);
      logger.info("WordParser.parse(): created leaf for symbol");
      parent.add(characterLeaf);
    }
  }
}
