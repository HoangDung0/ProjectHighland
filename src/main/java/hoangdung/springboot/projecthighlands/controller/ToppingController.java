package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.ToppingRequestEntity;
import hoangdung.springboot.projecthighlands.service.ToppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/toppings")
public class ToppingController {

    private final ToppingService toppingService;


    @GetMapping
    private ResponseEntity<?> getAllToppings(){
        return controllerWrapper(toppingService::getAllToppings);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getToppingById(@PathVariable String id){
        return controllerWrapper(() -> toppingService.getToppingById(id));
    }

    @GetMapping("/search")
    private ResponseEntity<?> searchToppingsByName(@RequestParam String name){
        return controllerWrapper(() -> toppingService.searchToppingsByName(name));
    }

    @PostMapping()
    public ResponseEntity<?> createNewUser(@RequestBody ToppingRequestEntity dto) {
        return controllerWrapper(() -> toppingService.createNewTopping(dto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingTopping(@PathVariable String id,
                                                 @RequestBody ToppingRequestEntity dto) {
        return controllerWrapper(() -> toppingService.updateExistingTopping(id, dto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteToppingByID(@PathVariable String id) {
        return controllerWrapper(() -> toppingService.deleteToppingByID(id));
    }
}
