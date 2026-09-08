package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private List<LineItem> items = new ArrayList<>();

    public void add(int productId) {
        for (LineItem item : items) {
            if (item.getProductId() == productId) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new LineItem(productId, 1));
    }

    public void update(int productId, int quantity) {
        if (quantity <= 0) {
            remove(productId);
            return;
        }
        for (LineItem item : items) {
            if (item.getProductId() == productId) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public void remove(int productId) {
        items.removeIf(item -> item.getProductId() == productId);
    }

    public List<LineItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
