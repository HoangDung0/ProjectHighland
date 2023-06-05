package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.ToppingRequestEntity;
import hoangdung.springboot.projecthighlands.service.ToppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/topping")
public class ToppingController {

    private final ToppingService toppingService;

    public ToppingRequestEntity toppingRequestEntity;


    @GetMapping
    private ResponseEntity<?> getAllToppings(){
        return controllerWrapper(toppingService::getAllToppings);
    }

    @GetMapping("/id")
    private ResponseEntity<?> getToppingById(@RequestParam String id){
        return controllerWrapper(() -> toppingService.getToppingById(id));
    }

    @GetMapping("/search")
    private ResponseEntity<?> searchToppingsByName(@RequestParam String name){
        return controllerWrapper(() -> toppingService.searchToppingsByName(name));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewUser(@RequestBody ToppingRequestEntity dto) {
        return controllerWrapper(() -> toppingService.createNewTopping(dto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingTopping(@PathVariable String id,
                                                 @RequestBody ToppingRequestEntity dto) {
        return controllerWrapper(() -> toppingService.updateExistingTopping(id, dto));
    }

    @DeleteMapping()
    private ResponseEntity<?> deleteToppingByID(@RequestParam String id) {
        return controllerWrapper(() -> toppingService.deleteToppingByID(id));
    }
}
