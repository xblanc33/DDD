package fr.ubordeaux.ao.referencemanagement.domain.model;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.ubordeaux.ao.referencemanagement.domain.exception.ReferenceManagementException;
import fr.ubordeaux.ao.referencemanagement.domain.type.Price;

public class PriceTest {

    @Test
    public void createPrice() {
        Price price = new Price(13);
        assertEquals(13,price.getPrice(),0);
        try {
            Price price2 = new Price(-1);
            assertTrue(false);
        } catch (ReferenceManagementException ex) {
            assertTrue(true);
        }

    }

}