import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  
  public static void main(String[] args) {
    // code here
  }
  
  public interface ProductService {

    static ProductService getInstance() {
      return new InMemoryProductService();
    }

    List<String> getCountries();

    List<UUID> getProducts();

    List<ProductSales> getProductSales(String country);

  }

  public static class ProductSales {

    public UUID product;
    public int amount;

    public ProductSales(UUID product, int amount) {
      this.product = product;
      this.amount = amount;
    }

    @Override
    public String toString() {
      return "ProductSales{" + "product=" + product + ", amount=" + amount + "}";
    }
  }

  /**
  * This is only provided to have test data for the exercice
  */
  public static class InMemoryProductService implements ProductService {

    private static final List<UUID> products = IntStream.range(0, 100).mapToObj(i -> UUID.randomUUID()).collect(Collectors.toList());
    private static final List<String> countries = Arrays.asList("FR", "UK", "ES", "DE");
    
    private static final Map<String, List<ProductSales>> productSalesPerCountry = countries.stream()
        .collect(Collectors.toMap(Function.identity(), country -> products.stream()
            .map(uuid -> new ProductSales(uuid, (int) (Math.random() * 1000)))
            .collect(Collectors.toList())));

    @Override
    public List<String> getCountries() {
      return countries;
    }

    @Override
    public List<UUID> getProducts() {
      return products;
    }

    @Override
    public List<ProductSales> getProductSales(String country) {
      return new ArrayList<>(productSalesPerCountry.get(country));
    }
  }
}