package by.glesap.task2.service.impl;

import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.TextCompositeImpl;
import by.glesap.task2.enums.TextComponentType;
import by.glesap.task2.parser.impl.*;
import by.glesap.task2.reader.CustomFileReader;
import by.glesap.task2.reader.impl.CustomFileReaderImpl;
import by.glesap.task2.service.TextOperation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextOperationImplTest {
  private static BaseParser parser;
  CustomFileReader reader = new CustomFileReaderImpl();
  TextOperation operation = new TextOperationImpl();

  @BeforeAll
  static void setUpBeforeClass() {
    BaseParser wordParser = new WordParser();
    BaseParser lexemeParser = new LexemeParser();
    BaseParser sentenceParser = new SentenceParser();
    BaseParser paragraphParser = new ParagraphParser();
    BaseParser textParser = new TextParser();
    lexemeParser.setNextParser(wordParser);
    sentenceParser.setNextParser(lexemeParser);
    paragraphParser.setNextParser(sentenceParser);
    textParser.setNextParser(paragraphParser);
    parser = textParser;
  }

  @Test
  void givenText_whenRestoreText_ThenCorrectText() throws IOException {
    String text = reader.readFile("data/input.txt");
    TextComposite root = new TextCompositeImpl(TextComponentType.TEXT);
    parser.parse(text, root);
    assertEquals(text, operation.restoreText(root));
  }

  @ParameterizedTest
  @MethodSource("provideParametersForMostSentencesTest")
  void givenText_whenFindMostSentencesWithSameWords_ThenCorrect(String filename,
                                                                int expected) throws IOException {
    String text = reader.readFile(filename);
    TextComposite root = new TextCompositeImpl(TextComponentType.TEXT);
    parser.parse(text, root);
    Optional<Integer> optionalResult = operation.findMostSentencesWithSameWords(root);
    Integer result = null;
    if (optionalResult.isPresent()) {
      result = optionalResult.get();
    }
    assertEquals(expected, result);
  }

  private static Stream<Arguments> provideParametersForMostSentencesTest() {
    return Stream.of(
            Arguments.of("data/opTestInput.txt" , 3)
    );
  }

  @ParameterizedTest
  @MethodSource("provideParametersForLexemesAsc")
  void givenText_whenFindLexemesAsc_ThenCorrect(String filename, String expected) throws IOException {
    String text = reader.readFile(filename);
    TextComposite root = new TextCompositeImpl(TextComponentType.TEXT);
    parser.parse(text, root);
    List<String> result = operation.getSentencesWithLexemesAsc(root);
    List<String> expectedResult;
    Path path = Paths.get(expected);
    expectedResult = Files.readAllLines(path);
    assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> provideParametersForLexemesAsc() {
    return Stream.of(
            Arguments.of("data/opTestInput1.txt" , "data/opTestInput2.txt")
    );
  }

  @ParameterizedTest
  @MethodSource("provideParametersForSwapLexemas")
  void givenText_whenSwapLexemas_ThenCorrect(String filename, String expected) throws IOException {
    String text = reader.readFile(filename);
    TextComposite root = new TextCompositeImpl(TextComponentType.TEXT);
    parser.parse(text, root);
    String result = operation.swapFirstAndLastLexemesInSentences(root);
    String expectedResult = reader.readFile(expected);
    assertEquals(expectedResult, result);
  }

  private static Stream<Arguments> provideParametersForSwapLexemas() {
    return Stream.of(
            Arguments.of("data/opTestInput.txt" , "data/opTestInput3.txt")
    );
  }
}
