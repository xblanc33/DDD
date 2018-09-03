package fr.ubordeaux.ao.referencemanagement.domain.model;

import java.util.Set;

import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWord;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;

/**
 * Repository 
 * 
 */
public interface KeyWordMap {
    public void map(KeyWord keyword, Reference reference);
    public void unmap(KeyWord keyword, Reference reference);
    public Set<Reference> findReferenceByKeyWord(KeyWord keyword);
    public Set<KeyWord> getKeyWords();
}