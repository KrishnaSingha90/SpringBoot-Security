package com.example.SpringSecurity;

import com.example.SpringSecurity.error.ProductNotFoundException;
import com.example.SpringSecurity.error.ProductUnSupportedFieldPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class StoreController {
    @Autowired
    private StoreRepository repository;

    // Find
    @GetMapping("/products")
    List<ProductStore> findAll() {
        return repository.findAll();
    }

    // Save
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    ProductStore newProduct(@Valid @RequestBody ProductStore newProduct) {
        return repository.save(newProduct);
    }

    // Find
    @GetMapping("/products/{id}")
    ProductStore findOne(@PathVariable @Min(1) Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    // Save or update
    @PutMapping("/products/{id}")
    ProductStore saveOrUpdate(@RequestBody ProductStore newProduct, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setProductName(newProduct.getProductName());
                    x.setProductCompany(newProduct.getProductCompany());
                    x.setProductPrice(newProduct.getProductPrice());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
    }

    // update author only
    @PatchMapping("/products/{id}")
    ProductStore patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

                    String produtCompany = update.get("productCompany");
                    if (!StringUtils.isEmpty(produtCompany)) {
                        x.setProductCompany(produtCompany);

                        // better create a custom method to update a value = :newValue where id = :id
                        return repository.save(x);
                    } else {
                        throw new ProductUnSupportedFieldPatchException(update.keySet());
                    }

                })
                .orElseGet(() -> {
                    throw new ProductNotFoundException(id);
                });

    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
