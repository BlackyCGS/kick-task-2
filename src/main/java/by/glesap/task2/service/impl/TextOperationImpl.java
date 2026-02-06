package by.glesap.task2.service.impl;

import by.glesap.task2.comparator.LexemeAmountComparator;
import by.glesap.task2.composite.TextComponent;
import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.composite.impl.TextCompositeImpl;
import by.glesap.task2.enums.TextComponentType;
import by.glesap.task2.service.TextOperation;

import java.util.*;

public class TextOperationImpl implements TextOperation {

  @Override
  public String restoreText(TextComposite root) {
    return root.asText();
  }

  @Override
  public Optional<Integer> findMostSentencesWithSameWords(TextComposite root) {
    Map<String, Set<TextComposite>> sentencesWithSameWords = new HashMap<>();
    List<TextComposite> sentences = findAllSentences(root);
    if (sentences.isEmpty()) {
      return Optional.empty();
    }
    for (TextComposite sentence : sentences) {
      findUniqueWordsInSentence(sentence, sentencesWithSameWords);
    }
    int max = 0;
    for (Map.Entry<String, Set<TextComposite>> entry :
            sentencesWithSameWords.entrySet()) {
      if (entry.getValue().size() > max) {
        max = entry.getValue().size();
      }
    }
    return Optional.of(max);
  }

  private void findUniqueWordsInSentence(TextComposite sentence,
                                                       Map<String, Set<TextComposite>> sentencesWithSameWords ) {
    List<TextComponent> children = sentence.getChildren();
    for (TextComponent child : children) {
      List<TextComponent> lexemeChildren = ((TextCompositeImpl) child).getChildren();
      for (TextComponent lexemeChild : lexemeChildren) {
        if (lexemeChild instanceof TextCompositeImpl compositeChild
                && compositeChild.getType() == TextComponentType.WORD) {
          String word = lexemeChild.asText();
          Set<TextComposite> entry = sentencesWithSameWords.get(word);
          if (entry == null) {
            entry = new HashSet<>();
          }
          entry.add(sentence);
          sentencesWithSameWords.put(word, entry);
        }
      }
    }
  }

  @Override
  public List<String> getSentencesWithLexemesAsc(TextComposite root) {
    List<TextComposite> sentences = findAllSentences(root);
    Comparator<TextComposite> lexemeAmountComparator = new LexemeAmountComparator();
    return sentences
            .stream()
            .sorted(lexemeAmountComparator)
            .map(TextComponent::asText)
            .toList();
  }

  @Override
  public String swapFirstAndLastLexemesInSentences(TextComposite root) {
    final List<TextComposite> sentences = findAllSentences(root);
    for (TextComposite sentence : sentences) {
      TextComponent first = sentence.getChildren().getFirst();
      TextComponent last = sentence.getChildren().getLast();
      List<TextComponent> children = new ArrayList<>(List.copyOf(sentence.getChildren()));
      children.set(0, last);
      children.set(children.size() - 1, first);
      sentence.setChildren(children);
    }
    return root.asText();
  }

  private List<TextComposite> findAllSentences(TextComposite root) {
    List<TextComponent> sentences = new ArrayList<>();
    for (TextComponent component : root.getChildren()) {
      if (component instanceof TextComposite textComposite
              && ((TextCompositeImpl) textComposite).getType() == TextComponentType.PARAGRAPH) {
          List<TextComponent> children = textComposite.getChildren();
          sentences.addAll(children);
        }
    }
    List<TextComposite> result = new ArrayList<>();
    for (TextComponent component : sentences) {
      result.add((TextComposite) component);
    }
    return result;
  }
}
