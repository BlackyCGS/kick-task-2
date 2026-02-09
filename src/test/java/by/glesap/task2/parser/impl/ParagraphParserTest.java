package by.glesap.task2.parser.impl;

import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.TextCompositeImpl;
import by.glesap.task2.parser.CustomParser;
import by.glesap.task2.reader.CustomFileReader;
import by.glesap.task2.reader.impl.CustomFileReaderImpl;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static by.glesap.task2.enums.TextComponentType.PARAGRAPH;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParagraphParserTest {
  private final CustomParser parser = new ParagraphParser();
  private final CustomFileReader reader = new CustomFileReaderImpl();

  @Test
  void givenParagraph_whenParseParagraph_thenCorrect() throws IOException {
    String text = reader.readFile("data/paragraphTestInput.txt");
    TextComposite root = new TextCompositeImpl(PARAGRAPH);
    parser.parse(text, root);
    assertEquals(2, root.getChildren().size());
  }
}
