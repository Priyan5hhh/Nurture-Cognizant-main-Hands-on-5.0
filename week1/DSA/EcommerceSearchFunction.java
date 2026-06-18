// Explain Big O notation and how it helps in analyzing algorithms.
// Big O notation is used to represent how the running time of an algorithm increases as we increase the input size.

// ==========

// Describe the best, average, and worst-case scenarios for search operations.
// Best case : When element is find at the beginning i.e. O(1) for linear search and binary search
// Worst case : When an element is found at the end of the array or is not present in the array i.e. O(n) for linear search and O(logn) for binary serach
// Average case : When element is found random, this is average of worst and best, O(n) for linear and (logn) for binary
package week1.DSA;

public class EcommerceSearchFunction {
    public static void main(String[] args) {
        Product[] products = {
            new Product(0, "Laptop", "Electronics"),
            new Product(1, "Bag", "Accessories"),
            new Product(2, "Charger", "Electronics"),
            new Product(3, "Cream", "Make-up"),
            new Product(4, "Shoes", "Clothing"),
            new Product(5, "Shirt", "Clothing")
        };

        int targetId = 5;
        Product result1 = linearSearch(products, targetId);
        System.out.println("This is the output of linear search");
        System.out.println(result1);

        Product result2 = binarySearch(products, targetId);
        System.out.println("This is the output of binary search");
        System.out.println(result2);
    }
    public static Product linearSearch(Product [] product, int x) {
        for(Product i : product) {
            if(i.productId == x) return i;
        } return null;
    }

    public static Product binarySearch(Product[] product, int x) {
        int low = 0;
        int high = product.length - 1;
        while (low <= high) {
            int mid = (low + high)/2;
            if(product[mid].productId == x) return product[mid];
            else if(product[mid].productId > x) high = mid - 1;
            else low = mid + 1;
        } return null;
    }

}

class Product {
    int productId;
    String productName;
    String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String toString() {
        return "Id : " + productId + "; Product Name : " + productName + "; Category : " + category;
    }

}