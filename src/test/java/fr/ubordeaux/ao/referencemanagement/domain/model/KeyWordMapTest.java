package fr.ubordeaux.ao.referencemanagement.domain.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWordMap;
import fr.ubordeaux.ao.referencemanagement.domain.model.KeyWord;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.type.Price;
import fr.ubordeaux.ao.referencemanagement.infrastructure.persistence.inmemory.KeyWordMapImpl;



public class KeyWordMapTest {
    KeyWordMap map;

    @Before
    public void createMap() {
        map = new KeyWordMapImpl();
    }

    @Test
    public void putKeyWordProductLink() {
        map.map(new KeyWord("test"), new Reference("id#1", "book","super book", new Price(20000)));
        assertEquals(1, map.findReferenceByKeyWord(new KeyWord("test")).size());
    }

}