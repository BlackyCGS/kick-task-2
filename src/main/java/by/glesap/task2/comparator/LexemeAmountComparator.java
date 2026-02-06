package by.glesap.task2.comparator;

import by.glesap.task2.composite.TextComposite;

import java.util.Comparator;

public class LexemeAmountComparator implements Comparator<TextComposite> {

  @Override
  public int compare(TextComposite sentence1, TextComposite sentence2) {
    Integer lexemeAmount1 = sentence1.getChildren().size();
    Integer lexemeAmount2 = sentence2.getChildren().size();
    return lexemeAmount1.compareTo(lexemeAmount2);
  }
}
