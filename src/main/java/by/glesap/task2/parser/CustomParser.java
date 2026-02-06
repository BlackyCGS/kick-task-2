package by.glesap.task2.parser;

import by.glesap.task2.composite.TextComposite;

public interface CustomParser {

  void setNextParser(CustomParser nextParser);
  void parse(String text, TextComposite parent);
}
