package fr.ubordeaux.ao.referencemanagement.domain.type;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;

public class CatalogName {
    private String value;

    public CatalogName(String value) {
        this.setValue(value);
    }

    private void setValue(String value)  {
        Pattern pattern = Pattern.compile("[a-zA-Z]{1,10}");
        Matcher matcher = pattern.matcher(value);
        if (!matcher.matches()) throw new ReferenceManagementException("A catalog name should have less than 10 alphabetical chars");
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof CatalogName)) return false;
        return this.value.compareTo(((CatalogName)other).value) == 0;
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