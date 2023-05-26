package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.ProductCatalogRequestEntity;
import hoangdung.springboot.projecthighlands.service.ProductCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/productcatalogs")
public class ProductCatalogController {

    private final ProductCatalogService catalogService;

    @GetMapping
    private ResponseEntity getAllProductCatalogs() {
        return ResponseEntity.ok().body(catalogService.getAllProductCatalogs());
    }

    @GetMapping("/{id}")
    private ResponseEntity getProductCatalogById(@PathVariable String id) {
        return ResponseEntity.ok().body(catalogService.getProductCatalogById(id));
    }

    @GetMapping("/search")
    private ResponseEntity searchProductCatalogsByName(@RequestParam String name) {
        return ResponseEntity.ok().body(catalogService.searchProductCatalogsByName(name));
    }

    @PostMapping()
    public ResponseEntity createNewProductCatalog(@RequestBody ProductCatalogRequestEntity dto) {
        return ResponseEntity.ok().body(catalogService.createNewProductCatalog(dto));
    }

    @PutMapping("/{id}")
    private ResponseEntity updateExistingProductCatalog(@PathVariable String id,
                                             @RequestBody ProductCatalogRequestEntity dto) {
        return ResponseEntity.ok().body(catalogService.updateExistingProductCatalog(id, dto));
    }

    @DeleteMapping()
    private ResponseEntity deleteProductCatalogByID(@RequestParam String id) {
        return ResponseEntity.ok().body(catalogService.deleteProductCatalogByID(id));
    }
}
