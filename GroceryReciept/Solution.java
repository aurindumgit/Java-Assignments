import java.io.*;
import java.util.*;
// import java.util.stream.*;
// import static java.util.stream.Collectors.toList;

class Node {
    public String fruit;
    public int count;

    public Node(String fruit, int count) {
        this.fruit = fruit;
        this.count = count;
    }
}

class Grocery {
    public String fruit;
    public int price;
    public double totalPrice;

    public Grocery(String fruit, int price, double totalPrice) {
        this.fruit = fruit;
        this.price = price;
        this.totalPrice = totalPrice;
    }
}

abstract class GroceryReceiptBase {
    protected Map<String, Double> prices;
    protected Map<String, Integer> discounts;

    public GroceryReceiptBase(Map<String, Double> prices, Map<String, Integer> discounts) {
        this.prices = prices;
        this.discounts = discounts;
    }

    public Map<String, Double> getPrices() {
        return prices;
    }

    public Map<String, Integer> getDiscounts() {
        return discounts;
    }

    public abstract List<Grocery> Calculate(List<Node> shoppingList);
}

class GroceryReceipt extends GroceryReceiptBase {

    public GroceryReceipt(Map<String, Double> prices, Map<String, Integer> discounts) {
        super(prices, discounts);
    }

    @Override
    public List<Grocery> Calculate(List<Node> shoppingList) {
        Map<String, Integer> qtyMap = new HashMap<>();
        for(Node item: shoppingList){
            qtyMap.put(item.fruit, qtyMap.getOrDefault(item.fruit, 0) + item.count);
        }
        List<Grocery> result = new ArrayList<>();

        for(String fruit:qtyMap.keySet()){
            double price = prices.get(fruit);
            int discount = discounts.getOrDefault(fruit, 0);
            int quantity = qtyMap.get(fruit);

            double gross = price * quantity;
            double totalPrice = gross - (gross * discount / 100.0);

            result.add(new Grocery(fruit, (int)price, totalPrice));

        }

        result.sort((a, b) -> a.fruit.compareTo(b.fruit));
        return result;
    }
}

class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<Node> boughtItems = new ArrayList<>();
        Map<String, Double> prices = new HashMap<>();
        Map<String, Integer> discounts = new HashMap<>();

        int priceCount = Integer.parseInt(bufferedReader.readLine().trim());

        for (int i = 0; i < priceCount; i++) {
            String[] priceInput = bufferedReader.readLine().trim().split(" ");
            String fruit = priceInput[0];
            double price = Double.parseDouble(priceInput[1]);
            prices.put(fruit, price);
        }

        int discountCount = Integer.parseInt(bufferedReader.readLine().trim());

        for (int i = 0; i < discountCount; i++) {
            String[] discountInput = bufferedReader.readLine().trim().split(" ");
            String fruit = discountInput[0];
            int discount = Integer.parseInt(discountInput[1]);
            discounts.put(fruit, discount);
        }

        int boughtCount = Integer.parseInt(bufferedReader.readLine().trim());

        for (int i = 0; i < boughtCount; i++) {
            String[] boughtInput = bufferedReader.readLine().trim().split(" ");
            String fruit = boughtInput[0];
            int count = Integer.parseInt(boughtInput[1]);
            boughtItems.add(new Node(fruit, count));
        }

        GroceryReceipt receipt = new GroceryReceipt(prices, discounts);
        List<Grocery> result = receipt.Calculate(boughtItems);

        for (Grocery item : result) {
            bufferedWriter.write(
                item.fruit + " " + item.price + " " + item.totalPrice
            );
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
