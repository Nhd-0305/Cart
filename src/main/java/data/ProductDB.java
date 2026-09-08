package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import model.Product;

public class ProductDB {

    private static final String DATA_FILE = "/data/products.csv";

    public static List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();

        try (InputStream in = ProductDB.class.getResourceAsStream(DATA_FILE);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {

            String line = reader.readLine();
            boolean firstLine = true;
            while (line != null) {
                if (firstLine) {
                    firstLine = false;
                } else if (!line.isBlank()) {
                    productList.add(parseLine(line));
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read product data file: " + DATA_FILE, e);
        }

        return productList;
    }

    public static Product getProduct(int id) {
        for (Product product : getProducts()) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    private static Product parseLine(String line) {
        String[] fields = line.split(",", 4);
        int id = Integer.parseInt(fields[0].trim());
        String name = fields[1].trim();
        String description = fields[2].trim();
        double price = Double.parseDouble(fields[3].trim());
        return new Product(id, name, description, price);
    }
}
