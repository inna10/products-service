package inna.com.inna_eisen_products_service.controllers;

import inna.com.inna_eisen_products_service.excetions.ApiBusinessException;
import inna.com.inna_eisen_products_service.services.APIService;
import inna.com.inna_eisen_products_service.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController()
@RequestMapping("/api")
public class APIController {
    @Autowired
    APIService apiService;
    Logger logger = Logger.getLogger(this.getClass().getPackageName());

    @GetMapping("/products")
    public List<Product> getProducts()
            throws Exception {
        logger.log(Level.FINE, String.format("Get products was called"));

        return apiService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductByRecordId(@PathVariable("id") UUID id) throws Exception {
        logger.log(Level.FINE, String.format("Get records was called, id=%s", id));
        return apiService.getProductByRecordId(id);
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product data) throws Exception {
        logger.log(Level.FINE, String.format("Add record was called, id=%s", data.getId()));
        return apiService.createProduct(data);
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product data) throws Exception {
        logger.log(Level.FINE, String.format("Update record was called, recordId=%s", data.getId()));
        return apiService.updateProduct(data);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProductById(@PathVariable("id") UUID id) throws Exception {
        logger.log(Level.FINE, String.format("Delete record was called, id=%s", id));
        apiService.deleteProductById(id);
        return "deleted";
    }

    /**
     * Exception handler. Used for proper exceptions handling, thrown from server's code
     * @param ex
     * @return
     */
    @ExceptionHandler({ ApiBusinessException.class, })
    public ResponseEntity<String> commandExceptionHandler(ApiBusinessException ex) {
        return new ResponseEntity<String>(ex.getMessage(), ex.getHttpStatus());
    }
}
