package by.glesap.task2.service;

import by.glesap.task2.composite.TextComposite;
import java.util.List;
import java.util.Optional;

public interface TextOperation {

  String restoreText(TextComposite root);

  Optional<Integer> findMostSentencesWithSameWords(TextComposite root);

  List<String> getSentencesWithLexemesAsc(TextComposite root);

  String swapFirstAndLastLexemesInSentences(TextComposite root);
}
