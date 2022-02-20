package cl.andres.catalog;

import java.util.List;

public interface CatalogGenerator {

    /**
     * Returns
     *
     * AWS, Axe, Wood, Small, price
     * ASS, Axe, Stone, Small
     * AWM, Axe, Wood, Medium
     *
     * @return
     */
    List<String> generateCatalog();

    String buildUID(String... elements);

    float calculatePrice(String uid);
}
