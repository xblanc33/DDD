package fr.ubordeaux.ao.referencemanagement.domain.model;

import java.util.Set;

import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWord;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;

/**
 * Repository 
 * 
 */
public abstract class KeyWordMap {
    public abstract void map(KeyWord keyword, Reference reference);
    public abstract void unmap(KeyWord keyword, Reference reference);
    public abstract Set<Reference> findReferenceByKeyWord(KeyWord keyword);
    public abstract Set<KeyWord> getKeyWordSet();
}