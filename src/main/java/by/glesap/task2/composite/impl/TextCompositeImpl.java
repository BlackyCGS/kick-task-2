package by.glesap.task2.composite.impl;

import by.glesap.task2.composite.TextComponent;
import by.glesap.task2.composite.TextComposite;
import by.glesap.task2.enums.TextComponentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TextCompositeImpl implements TextComposite {
  private static final Logger logger = LoggerFactory.getLogger(TextCompositeImpl.class);
  private final TextComponentType type;
  private final List<TextComponent> children = new ArrayList<>();
  private static final String SPACE = " ";
  private static final String NEW_LINE = "\n";

  public TextCompositeImpl(TextComponentType type) {
    this.type = type;
  }

  public TextComponentType getType() {
    return type;
  }

  @Override
  public void add(TextComponent component) {
    logger.info("TextCompositeImpl.add()");
    children.add(component);
  }

  @Override
  public List<TextComponent> getChildren() {
    logger.info("TextCompositeImpl.getChildren()");
    return List.copyOf(children);
  }

  @Override
  public void setChildren(List<TextComponent> children) {
    logger.info("TextCompositeImpl.setChildren()");
    this.children.clear();
    this.children.addAll(children);
  }

  @Override
  public String asText() {
    StringBuilder sb = new StringBuilder();
    for (TextComponent component : children) {
      sb.append(component.asText());
      if ((type == TextComponentType.SENTENCE || type == TextComponentType.PARAGRAPH)
              && !component.equals(children.getLast())) {
        sb.append(SPACE);
      }
      if (type == TextComponentType.TEXT && !component.equals(children.getLast())) {
        sb.append(NEW_LINE);
      }
    }
    return sb.toString();
  }

  @Override
  public int getCount() {
    logger.info("TextCompositeImpl.getCount()");
    int count = 0;
    for (TextComponent component : children) {
      count += component.getCount();
    }
    return count;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof TextCompositeImpl other) {
      if (other.getType() != type) {
        return false;
      }
      return other.asText().equals(asText());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return asText().hashCode();
  }
}
