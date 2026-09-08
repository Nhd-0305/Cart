package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable {
    private Map<String, CartItem> items = new LinkedHashMap<>();

    public void add(CD cd) {
        CartItem item = items.get(cd.getId());
        if (item == null) {
            items.put(cd.getId(), new CartItem(cd));
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public void update(String id, int quantity) {
        if (quantity <= 0) {
            remove(id);
            return;
        }
        CartItem item = items.get(id);
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    public void remove(String id) {
        items.remove(id);
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.getTotal();
        }
        return total;
    }
}
