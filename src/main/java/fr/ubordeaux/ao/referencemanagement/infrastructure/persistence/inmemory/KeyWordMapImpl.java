package fr.ubordeaux.ao.referencemanagement.infrastructure.persistence.inmemory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWordMap;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWord;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;


public class KeyWordMapImpl extends KeyWordMap {
    Map<KeyWord, Set<Reference>> map;

    public KeyWordMapImpl() {
        map = new HashMap<>();
    }

    @Override
	public void map(KeyWord keyword, Reference reference) {
        if (map.containsKey(keyword)) {
            map.get(keyword).add(reference);
        }
        
        if ( ! map.containsKey(keyword)) {
            Set<Reference> references = new HashSet<Reference>();
            references.add(reference);
            map.put(keyword, references);
        }
	}

    @Override
	public void unmap(KeyWord keyword, Reference reference) {    
        if (map.containsKey(keyword)) {
            map.get(keyword).remove(reference);
        }
    }
    
    @Override
    public Set<Reference> findReferenceByKeyWord(KeyWord keyword) {
		if (map.containsKey(keyword)) {
            Set<Reference> result = new HashSet<Reference>();
            result.addAll(map.get(keyword));
            return result;
        }
		return new HashSet<Reference>();
	}

	@Override
	public Set<KeyWord> getKeyWordSet() {
        Set<KeyWord> result = new HashSet<KeyWord>();
        result.addAll(map.keySet());
		return result;
	}

}