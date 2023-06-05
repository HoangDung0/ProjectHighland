package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.ProductCatalogRequestEntity;
import hoangdung.springboot.projecthighlands.service.ProductCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/productcatalogs")
public class ProductCatalogController {

    private final ProductCatalogService catalogService;

    @GetMapping
    private ResponseEntity<?> getAllProductCatalogs() {
        return controllerWrapper(catalogService::getAllProductCatalogs);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getProductCatalogById(@PathVariable String id) {
        return controllerWrapper(() -> catalogService.getProductCatalogById(id));
    }

    @GetMapping("/search")
    private ResponseEntity<?> searchProductCatalogsByName(@RequestParam String name) {
        return controllerWrapper(() -> catalogService.searchProductCatalogsByName(name));
    }

    @PostMapping()
    public ResponseEntity<?> createNewProductCatalog(@RequestBody ProductCatalogRequestEntity dto) {
        return controllerWrapper(() -> catalogService.createNewProductCatalog(dto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingProductCatalog(@PathVariable String id,
                                             @RequestBody ProductCatalogRequestEntity dto) {
        return controllerWrapper(() -> catalogService.updateExistingProductCatalog(id, dto));
    }

    @DeleteMapping()
    private ResponseEntity<?> deleteProductCatalogByID(@RequestParam String id) {
        return controllerWrapper(() -> catalogService.deleteProductCatalogByID(id));
    }
}
