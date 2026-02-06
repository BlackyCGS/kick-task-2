package by.glesap.task2.parser.impl;

import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.TextCompositeImpl;
import by.glesap.task2.enums.TextComponentType;
import by.glesap.task2.reader.CustomFileReader;
import by.glesap.task2.reader.impl.CustomFileReaderImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SentenceParserTest {
  BaseParser parser = new SentenceParser();
  CustomFileReader reader = new CustomFileReaderImpl();

  @Test
  void givenSentence_whenParseSentence_thenCorrect() throws IOException {
    String text = reader.readFile("data/sentenceTestInput.txt");
    TextComposite root = new TextCompositeImpl(TextComponentType.SENTENCE);
    parser.parse(text, root);
    assertEquals(21, root.getChildren().size());
  }
}
