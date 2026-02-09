package by.glesap.task2.composite.impl;

import by.glesap.task2.composite.TextComponent;
import by.glesap.task2.enums.TextComponentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterLeaf implements TextComponent {
  private static final Logger logger = LoggerFactory.getLogger(CharacterLeaf.class);
  private static final int LENGTH = 1;
  private final TextComponentType type;
  private final Character character;

  public CharacterLeaf(TextComponentType type, Character character) {
    this.type = type;
    this.character = character;
  }

  public TextComponentType getType() {
    return type;
  }

  @Override
  public String asText() {
    logger.info("CharacterLeaf.asText()");
    return character.toString();
  }

  @Override
  public int getCount() {
    return LENGTH;
  }

  @Override
  public  boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this.getClass() == obj.getClass()) {
      CharacterLeaf other = (CharacterLeaf) obj;
      if (this.getType() != other.getType()) {
        return false;
      }
      return this.asText().equals(other.asText());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return character.hashCode();
  }
}
