package inna.com.inna_eisen_products_service.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inna.com.inna_eisen_products_service.excetions.ApiResourceNotFoundException;
import inna.com.inna_eisen_products_service.repository.ProductRepository;
import inna.com.inna_eisen_products_service.models.Product;


@Service
public class APIService {
    ProductRepository repo;
    @Autowired
    public APIService(ProductRepository repo) {
        this.repo = repo;
        if(repo.count()>0)
            return;
        List<Product> list = new ArrayList<Product>();

        list.add(new Product("2817839095220", "Essence Mascara Lash Princess",
                "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/1.png",
                Arrays.asList("beauty", "mascara") ,4.94, 9.99));
        list.add(new Product("0516267971277", "Eyeshadow Palette with Mirror",
                "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/1.png",
                Arrays.asList("beauty", "eyeshadow"), 3.28, 19.99));
        list.add(new Product("3212847902461", "Powder Canister",
                "https://cdn.dummyjson.com/products/images/beauty/Eyeshadow%20Palette%20with%20Mirror/1.png",
                Arrays.asList("beauty", "eyeshadow"), 4.01, 14.99));
        list.add(new Product("2210136215089", "Red Lipstick",
                "https://cdn.dummyjson.com/products/images/beauty/Red%20Lipstick/1.png",
                Arrays.asList("beauty", "lipstick"), 2.51, 12.99));
        list.add(new Product("1435582999795", "Red Nail Polish",
                "https://cdn.dummyjson.com/products/images/beauty/Red%20Nail%20Polish/1.png",
                Arrays.asList("beauty", "lipstick"), 2.33, 11.99));
        list.add(new Product("0887083199279", "Calvin Klein CK One",
                "https://cdn.dummyjson.com/products/images/fragrances/Calvin%20Klein%20CK%20One/1.png",
                Arrays.asList("beauty", "lipstick"), 2.76, 129.99));
        list.add(new Product("1939383392674", "Chanel Coco Noir Eau De",
                "https://cdn.dummyjson.com/products/images/fragrances/Chanel%20Coco%20Noir%20Eau%20De/1.png",
                Arrays.asList("beauty", "lipstick"), 3.31, 89.99));
        this.repo.saveAll(list);
    }

    /**
     * Get product data by id
     * @param id
     * @return product
     * @throws ApiResourceNotFoundException
     */
    public Product getProductByRecordId(UUID id) throws ApiResourceNotFoundException {
        try {

            Optional<Product> product = repo.findById(id);
            return product.get();
        } catch (NoSuchElementException e) {
            throw new ApiResourceNotFoundException("Product not found, id=" + id);
        }
    }

    /**
     * Get all products
     * @return List<Product>
     * @throws Exception
     */
    public List<Product> getProducts() throws Exception {
        return repo.findAll();
    }

    /**
     * Delete product by id
     * @param id
     * @throws Exception
     */
    public void deleteProductById(UUID id) throws Exception {
        repo.deleteById(id);
    }

    /**
     * Create product record
     * @param data
     * @return Product
     * @throws Exception
     */
    public Product createProduct(Product data) throws Exception {
        Product createdData =  repo.saveAndFlush(data);
        return createdData;
    }

    /**
     * Update existing product
     * @param data
     * @return Product
     * @throws Exception
     */
    public Product updateProduct(Product data) throws Exception {
        Product createdData =  repo.saveAndFlush(data);
        return createdData;
    }
}
