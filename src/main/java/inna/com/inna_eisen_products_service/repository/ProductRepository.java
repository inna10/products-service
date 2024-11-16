package inna.com.inna_eisen_products_service.repository;

import java.util.List;
import java.util.UUID;

import inna.com.inna_eisen_products_service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID>{
    //Derived methods can be defined as VERB + attribute defined in an entity: findProductsBy+id
    //https://www.baeldung.com/spring-data-jpa-deleteby
    //@Query("SELECT p FROM Product p WHERE p.rating=:rating")
    List<Product> findProductsByRating(Double rating);
}
