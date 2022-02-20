package cl.andres.catalog;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogGeneratorTest {

    CatalogGeneratorImpl catalogGenerator = new CatalogGeneratorImpl();

    @Test
    public void generatorCatalogTest() {
        List<String> csvElements = catalogGenerator.generateCatalog();
        int expectedSize = catalogGenerator.weaponTypes.size()
                * catalogGenerator.sizes.size()
                * catalogGenerator.materials.size();

        assertNotNull(csvElements);
        assertEquals(expectedSize, csvElements.size());
    }

    @Test
    public void buildUIDTest() {
        assertEquals("AWS", catalogGenerator.buildUID("Axe", "Wood", "Small"));
    }

    @Test
    public void calculatePrice() {
        assertTrue(catalogGenerator.calculatePrice("HMC") > 0);
    }
}
