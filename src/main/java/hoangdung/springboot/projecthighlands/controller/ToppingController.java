package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.ToppingRequestEntity;
import hoangdung.springboot.projecthighlands.service.ToppingService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/toppings")
public class ToppingController {

    private final ToppingService toppingService;


    @Operation(summary = "Get All Toppings")
    @GetMapping
    public ResponseEntity<?> getAllToppings(){
        return controllerWrapper(toppingService::getAllToppings);
    }

    @Operation(summary = "Get Topping By Topping ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getToppingById(@PathVariable String id){
        return controllerWrapper(() -> toppingService.getToppingById(id));
    }

    @Operation(summary = "Get Topping By Topping Name")
    @GetMapping("/search")
    public ResponseEntity<?> searchToppingsByName(@RequestParam String name){
        return controllerWrapper(() -> toppingService.searchToppingsByName(name));
    }


    @Operation(summary = "Create New Topping")
    @PostMapping()
    public ResponseEntity<?> createNewTopping(@RequestBody ToppingRequestEntity dto) {
        return controllerWrapper(() -> toppingService.createNewTopping(dto));
    }

    @Operation(summary = "Update Existing Topping")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExistingTopping(@PathVariable String id,
                                                 @RequestBody ToppingRequestEntity dto) {
        return controllerWrapper(() -> toppingService.updateExistingTopping(id, dto));
    }

    @Operation(summary = "Delete Existing Topping")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToppingByID(@PathVariable String id) {
        return controllerWrapper(() -> toppingService.deleteToppingByID(id));
    }
}
