package inna.com.inna_eisen_products_service.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    private String barcode;
    private String name;
    private String  image;
    @ElementCollection
    @CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    private List<String> tags;

    private Double rating;
    private Double price;

    public Product() {
    }

    public Product(String barcode, String name, String  image, List<String> tags, Double rating, Double price) {
        super();
        this.barcode = barcode;
        this.name = name;
        this.image = image;
        this.tags = tags;
        this.rating = rating;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", tags=" + tags +
                ", rating=" + rating +
                ", price=" + price +
                '}';
    }
}
