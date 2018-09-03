package fr.ubordeaux.ao.referencemanagement.domain.model;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import fr.ubordeaux.ao.referencemanagement.domain.model.Catalog;
import fr.ubordeaux.ao.referencemanagement.domain.model.Reference;
import fr.ubordeaux.ao.referencemanagement.domain.type.CatalogName;
import fr.ubordeaux.ao.referencemanagement.domain.type.Price;
import fr.ubordeaux.ao.referencemanagement.infrastructure.persistence.inmemory.CatalogImpl;

public class InMemoryCatalogTest {
    Catalog catalog;

    @Before
    public void createCatalog() {
        catalog = new CatalogImpl(new CatalogName("root"));
    }

    @Test
    public void testAddReferenceInCatalog() { 
        //final int MAX = 150000000;//150 Millions
        final int MAX = 50000;
        for (int i=0 ; i < MAX ; i++) {
            Reference reference = new Reference("#"+i, "test", "test", new Price(2000));
            catalog.add(reference);
        }
        assertEquals(MAX, catalog.size());
    }

    @Test
    public void testSubCatalog() {
        final int MAX = 50000;
        for (int i=0 ; i < MAX ; i++) {
            Reference reference = new Reference("#"+i, "test", "test", new Price(2000));
            catalog.add(reference);
        }
        
        Catalog sub = catalog.createSubCatalog(new CatalogName("sub"));
        for (int i=0 ; i < MAX ; i++) {
            Reference reference = new Reference("#"+i, "test", "test", new Price(2000));
            sub.add(reference);
        }

        assertEquals(MAX, sub.size());
        assertEquals(2*MAX, catalog.size());
    }

    @Test
    public void getReferences() {
        final int MAX = 1000;
        for (int i=0 ; i < MAX ; i++) {
            Reference reference = new Reference("#"+i, "test", "test", new Price(2000));
            catalog.add(reference);
        }
        Set<Reference> references = catalog.getReferences();
        assertEquals(MAX, references.size());
    }

    @Test
    public void findCatalogByName() {
        Catalog src = catalog.createSubCatalog(new CatalogName("src"));
        Catalog test = catalog.createSubCatalog(new CatalogName("test"));
        Catalog java = src.createSubCatalog(new CatalogName("java"));
        Catalog java_ = catalog.getSubCatalogByName(new CatalogName("java"));
        assertEquals(java, java_);
    }
}