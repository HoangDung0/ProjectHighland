package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.ProductCatalogRequestEntity;
import hoangdung.springboot.projecthighlands.service.ProductCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/productcatalogs")
public class ProductCatalogController {

    private final ProductCatalogService catalogService;

    @Operation(summary = "Get All Product Catalogs")
    @GetMapping
    public ResponseEntity<?> getAllProductCatalogs() {
        return controllerWrapper(catalogService::getAllProductCatalogs);
    }

    @Operation(summary = "Get Product Catalog By ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductCatalogById(@PathVariable String id) {
        return controllerWrapper(() -> catalogService.getProductCatalogById(id));
    }

    @Operation(summary = "Get Product Catalog By Name")
    @GetMapping("/search")
    public ResponseEntity<?> searchProductCatalogsByName(@RequestParam String name) {
        return controllerWrapper(() -> catalogService.searchProductCatalogsByName(name));
    }

    @Operation(summary = "Create Product Catalog")
    @PostMapping()
    public ResponseEntity<?> createNewProductCatalog(@RequestBody ProductCatalogRequestEntity dto) {
        return controllerWrapper(() -> catalogService.createNewProductCatalog(dto));
    }

    @Operation(summary = "Update Product Catalog")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExistingProductCatalog(@PathVariable String id,
                                             @RequestBody ProductCatalogRequestEntity dto) {
        return controllerWrapper(() -> catalogService.updateExistingProductCatalog(id, dto));
    }

    @Operation(summary = "Delete Product Catalog")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductCatalogByID(@PathVariable String id) {
        return controllerWrapper(() -> catalogService.deleteProductCatalogByID(id));
    }
}
