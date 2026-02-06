package by.glesap.task2.composite;

import java.util.List;

public interface TextComposite extends TextComponent {
  void add(TextComponent component);
  List<TextComponent> getChildren();
  void setChildren(List<TextComponent> children);
}
