package hoangdung.springboot.projecthighlands.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hoangdung.springboot.projecthighlands.model.request.ProductRequestEntity;
import hoangdung.springboot.projecthighlands.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    public static ProductService productService;
//    @GetMapping
//    private ResponseEntity<?> getAllProduct() {
//        return controllerWrapper(productService::getAllProduct);
//    }

//    @GetMapping
//    private ResponseEntity<?> getProductByName(@RequestParam String name) {
//        return controllerWrapper(() -> productService.getProductByName(name));
//    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getProductByID(@PathVariable String id) {
        return controllerWrapper(() -> {
            try {
                return productService.getProductByID(id);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @PostMapping()
    public ResponseEntity<?> createNewProduct(@RequestBody ProductRequestEntity dto) {
        return controllerWrapper(() -> {
            try {
                return productService.createNewProduct(dto);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingProductCatalog(@PathVariable String id,
                                                           @RequestBody ProductRequestEntity dto) {
        return controllerWrapper(() -> {
            try {
                return productService.updateExistingProduct(id, dto);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @DeleteMapping()
    private ResponseEntity<?> deleteProductCatalogByID(@RequestParam String id) {
        return controllerWrapper(() -> {
            try {
                return productService.deleteProductByID(id);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }


    //API CRUD Size Option
    @PutMapping("/{id}/addsizeoption")
    private ResponseEntity<?> addSizeOption(@PathVariable String id,
                                            @RequestParam String size,
                                            @RequestParam int price) {
        return controllerWrapper(() -> {
            try {
                return productService.addSizeOption(id, size, price);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @PutMapping("/{id}/updatesizeoption")
    private ResponseEntity<?> updateSizeOption(@PathVariable String id,
                                               @RequestParam String size,
                                               @RequestParam int newPrice) {
        return controllerWrapper(() -> {
            try {
                return productService.updateSizeOption(id, size, newPrice);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @PutMapping("/{id}/deletesizeoption")
    private ResponseEntity<?> deleteSizeOption(@PathVariable String id,
                                               @RequestParam String size) {
        return controllerWrapper(() -> {
            try {
                return productService.deleteSizeOption(id, size);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }


}

