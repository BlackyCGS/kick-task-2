package by.glesap.task2.parser.impl;

import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.TextCompositeImpl;
import by.glesap.task2.parser.CustomParser;
import by.glesap.task2.reader.CustomFileReader;
import by.glesap.task2.reader.impl.CustomFileReaderImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static by.glesap.task2.enums.TextComponentType.TEXT;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TextParserTest {
  private final CustomParser textParser = new TextParser();
  private final CustomFileReader reader = new CustomFileReaderImpl();

  @Test
  void givenTextAndRoot_whenParseText_thenReturnParagraphs() throws IOException {
    String text = reader.readFile("data/input.txt");
    TextComposite root = new TextCompositeImpl(TEXT);
    textParser.parse(text, root);
    assertEquals(5, root.getChildren().size());
  }

}
