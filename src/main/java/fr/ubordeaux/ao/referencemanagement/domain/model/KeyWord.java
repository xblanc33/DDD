package fr.ubordeaux.ao.referencemanagement.domain.model;

import java.util.Objects;

/**
 * Value Object 
 * 
 */
public class KeyWord {
    private String value;

    public KeyWord(String value) {
        this.setValue(value);
    }

    private void setValue(String value)  {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof KeyWord) {
            KeyWord otherkeyWord = (KeyWord)other;
            boolean equals = this.value.compareTo(otherkeyWord.value)==0;
			return equals;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return this.value;
    }
}