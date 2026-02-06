package by.glesap.task2.parser.impl;

import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.TextCompositeImpl;
import by.glesap.task2.enums.TextComponentType;
import by.glesap.task2.reader.CustomFileReader;
import by.glesap.task2.reader.impl.CustomFileReaderImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordParserTest {
  BaseParser parser = new WordParser();
  CustomFileReader reader = new CustomFileReaderImpl();

  @ParameterizedTest
  @ValueSource(strings = {"home", "treat", "word", "word" })
  void givenWord_whenParseText_thenCorrect(String word) {
    TextComposite root = new TextCompositeImpl(TextComponentType.WORD);
    parser.parse(word, root);
    assertEquals(word, root.asText());
  }
}
