package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.ToppingRequestEntity;
import hoangdung.springboot.projecthighlands.service.ToppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/topping")
public class ToppingController {

    private final ToppingService toppingService;

    public ToppingRequestEntity toppingRequestEntity;


    @GetMapping
    private ResponseEntity getAllToppings(){
        return ResponseEntity.ok(toppingService.getAllToppings());
    }

    @GetMapping("/id")
    private ResponseEntity getToppingById(@RequestParam String id){
        return ResponseEntity.ok(toppingService.getToppingById(id));
    }

    @GetMapping("/search")
    private ResponseEntity searchToppingsByName(@RequestParam String name){
        return ResponseEntity.ok().body(toppingService.searchToppingsByName(name));
    }

    @PostMapping("/create")
    public ResponseEntity createNewUser(@RequestBody ToppingRequestEntity dto) {
        return ResponseEntity.ok().body(toppingService.createNewTopping(dto));
    }

    @PutMapping("/{id}")
    private ResponseEntity updateExistingTopping(@PathVariable String id,
                                                 @RequestBody ToppingRequestEntity dto) {
        return ResponseEntity.ok().body(toppingService.updateExistingTopping(id, dto));
    }

    @DeleteMapping()
    private ResponseEntity deleteToppingByID(@RequestParam String id) {
        return ResponseEntity.ok().body(toppingService.deleteToppingByID(id));
    }
}
