// Implement an OrderInvoice class that extends OrderInvoiceBase.

// Given:

// • Product price list
// • Product tax percentages
// • Items purchased (duplicates allowed)

// Task:

// Generate invoice:

// product, basePrice, taxAmount, finalPrice

// Where:

// taxAmount = quantity × price × tax%
// finalPrice = base + tax

// Sort by product name.


import java.util.*;

class Item {
    public String product;
    public int quantity;

    public Item(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

class Invoice {
    public String product;
    public int price;
    public double tax;
    public double finalPrice;

    public Invoice(String product, int price, double tax, double finalPrice) {
        this.product = product;
        this.price = price;
        this.tax = tax;
        this.finalPrice = finalPrice;
    }
}

abstract class OrderInvoiceBase {
    protected Map<String, Integer> prices;
    protected Map<String, Double> taxes;

    public OrderInvoiceBase(Map<String, Integer> prices, Map<String, Double> taxes) {
        this.prices = prices;
        this.taxes = taxes;
    }

    public abstract List<Invoice> generate(List<Item> items);
}

class OrderInvoice extends OrderInvoiceBase {

    public OrderInvoice(Map<String, Integer> prices, Map<String, Double> taxes) {
        super(prices, taxes);
    }

    @Override
    public List<Invoice> generate(List<Item> items) {
        // Write your logic here
        Map<String, Integer> qtyMap = new HashMap<>();
        for(Item item: items){
            qtyMap.put(item.product, qtyMap.getOrDefault(item.product, 0) + item.quantity);
        }

        List<Invoice> result = new ArrayList<>();

        for (String product : qtyMap.keySet()) {
            int quantity = qtyMap.get(product);
            int price = prices.getOrDefault(product, 0);
            double taxPercent = taxes.getOrDefault(product, 0.0);
            double taxAmount = quantity * price * taxPercent / 100.0;
            double finalPrice = quantity * price + taxAmount;
            result.add(new Invoice(product, price, taxAmount, finalPrice));
        }

        result.sort((a, b) -> a.product.compareTo(b.product));

        return result;
    }
}

class Solution {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, Integer> prices = new HashMap<>();
        Map<String, Double> taxes = new HashMap<>();
        List<Item> items = new ArrayList<>();

        int priceCount = sc.nextInt();
        for (int i = 0; i < priceCount; i++) {
            String product = sc.next();
            int price = sc.nextInt();
            prices.put(product, price);
        }

        int taxCount = sc.nextInt();
        for (int i = 0; i < taxCount; i++) {
            String product = sc.next();
            double tax = sc.nextDouble();
            taxes.put(product, tax);
        }

        int itemCount = sc.nextInt();
        for (int i = 0; i < itemCount; i++) {
            String product = sc.next();
            int quantity = sc.nextInt();
            items.add(new Item(product, quantity));
        }

        OrderInvoice invoice = new OrderInvoice(prices, taxes);
        List<Invoice> result = invoice.generate(items);

        for (Invoice inv : result) {
            System.out.println(inv.product + " " + inv.price + " " + inv.tax + " " + inv.finalPrice);
        }

        sc.close();
    }
}
