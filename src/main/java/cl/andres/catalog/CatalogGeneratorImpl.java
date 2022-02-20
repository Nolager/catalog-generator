package cl.andres.catalog;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CatalogGeneratorImpl implements CatalogGenerator {

    public List<String> weaponTypes = Arrays.asList("Axe", "Speer", "Sword");
    public List<String> materials = Arrays.asList("Wood", "Stone", "Steel");
    public List<String> sizes = Arrays.asList("Small", "Medium", "Large", "Extra Large");

    public static void main(String[] args) {
        List<String> catalog = new CatalogGeneratorImpl().buildCsv();

        catalog.stream().forEach(System.out::println);

        Path path = Paths.get("weapon-catalog.csv");
        try {
            Files.write(path, catalog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> buildCsv() {
        List<String> weapons = generateCatalog();
        return weapons.stream().map(this::buildCsvRow).collect(Collectors.toList());
    }

    private String buildCsvRow(String csv) {
        String uid = buildUID(csv.split(","));
        String price = String.valueOf(calculatePrice(uid));

        return Arrays.asList(uid, csv, price).stream().collect(Collectors.joining(","));
    }

    @Override
    public List<String> generateCatalog() {
        List<List<String>> all = Arrays.asList(materials, sizes);

        return all.stream().reduce(weaponTypes, this::combine);
    }

    private List<String> combine(List<String> list1, List<String> list2) {
        return list1.stream()
                .flatMap(str1 -> list2.stream().map(str2 -> str1.concat(",").concat(str2)))
                .collect(Collectors.toList());
    }

    @Override
    public String buildUID(String... elements) {
        return Stream.of(elements).reduce("", (uid, element) -> uid + element.charAt(0));
    }

    @Override
    public float calculatePrice(String uid) {
        return uid.chars().sum();
    }
}
