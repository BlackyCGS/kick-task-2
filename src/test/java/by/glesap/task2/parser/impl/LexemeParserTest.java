package by.glesap.task2.parser.impl;

import by.glesap.task2.composite.TextComponent;
import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.CharacterLeaf;
import by.glesap.task2.composite.impl.TextCompositeImpl;
import by.glesap.task2.enums.TextComponentType;
import by.glesap.task2.parser.CustomParser;
import by.glesap.task2.reader.CustomFileReader;
import by.glesap.task2.reader.impl.CustomFileReaderImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LexemeParserTest {
  private final CustomParser parser = new LexemeParser();
  private final CustomFileReader reader = new CustomFileReaderImpl();

  @ParameterizedTest
  @ValueSource(strings = {"home.", "treat,", "word!", "word?" })
  void givenLexemeWithPunctuation_whenLexemeParser_thenRootHasCompositeAndLeaf(String lexeme) {
    TextComposite root = new TextCompositeImpl(TextComponentType.LEXEME);
    parser.parse(lexeme, root);
    List<TextComponent> components = root.getChildren();
    for (TextComponent component : components) {
      if (component instanceof CharacterLeaf) {
        assertTrue(lexeme.endsWith(component.asText()));
      }
      if (component instanceof TextCompositeImpl) {
        assertEquals(TextComponentType.WORD, ((TextCompositeImpl) component).getType());
      }
    }
    assertEquals(2, components.size());
  }

  @ParameterizedTest
  @ValueSource(strings = {"home", "treat", "word", "word" })
  void givenLexemeWithoutPunctuiation_whenLexemeParser_thenRootHasComposite(String lexeme) {
    TextComposite root = new TextCompositeImpl(TextComponentType.LEXEME);
    parser.parse(lexeme, root);
    assertEquals(1, root.getChildren().size());
  }
}
