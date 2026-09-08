package model;

public class CartItem {
    private CD cd;
    private int quantity;

    public CartItem(CD cd) {
        this.cd = cd;
        this.quantity = 1;
    }

    public CD getCd() {
        return cd;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return cd.getPrice() * quantity;
    }
}
