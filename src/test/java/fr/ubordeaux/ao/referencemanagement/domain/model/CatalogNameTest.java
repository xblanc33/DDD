package fr.ubordeaux.ao.referencemanagement.domain.model;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.type.CatalogName;

public class CatalogNameTest {

    @Test
    public void createCatalogName() {
        CatalogName cn = new CatalogName("tools");
        CatalogName cn2 = new CatalogName("tools");
        assertTrue(cn.equals(cn2));
        try {
            CatalogName cn3 = new CatalogName("My Catalog");
            assertTrue(false);
        } catch (ReferenceManagementException ex) {
            assertTrue(true);
        }
        try {
            CatalogName cn3 = new CatalogName("4Catalog");
            assertTrue(false);
        } catch (ReferenceManagementException ex) {
            assertTrue(true);
        }
    }

}