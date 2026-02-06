package by.glesap.task2.composite.impl;

import by.glesap.task2.composite.TextComponent;
import by.glesap.task2.enums.TextComponentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterLeaf implements TextComponent {
  private static final Logger logger = LoggerFactory.getLogger(CharacterLeaf.class);
  private static final int LENGTH = 1;

  public TextComponentType getType() {
    return type;
  }

  private final TextComponentType type;
  private final Character character;

  public CharacterLeaf(TextComponentType type, Character character) {
    this.type = type;
    this.character = character;
  }

  @Override
  public String asText() {
    return character.toString();
  }

  @Override
  public int getCount() {
    return LENGTH;
  }
}
