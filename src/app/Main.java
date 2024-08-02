package app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0),
                new Product("Smartphone", "Electronics", 800.0),
                new Product("Toaster", "Appliances", 30.0)
        );

        // Групування продуктів за категоріями
        Map<String, List<Product>> groupedByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("Продукти згруповані за категоріями:");
        groupedByCategory.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

        // Знаходження середньої ціни продуктів в кожній категорії
        Map<String, Double> averagePriceByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));

        System.out.println("\nСередня ціна продуктів за категоріями:");
        averagePriceByCategory.forEach((category, avgPrice) -> {
            System.out.println(category + ": " + String.format("%.2f", avgPrice));
        });

        // Знаходження категорії з найвищою середньою ціною
        String categoryWithHighestAvgPrice = averagePriceByCategory.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");

        System.out.println("\nКатегорія з найвищою середньою ціною: " + categoryWithHighestAvgPrice);
    }
}