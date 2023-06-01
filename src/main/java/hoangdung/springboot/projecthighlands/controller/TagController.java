package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.TagRequestEntity;
import hoangdung.springboot.projecthighlands.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    @GetMapping
    private ResponseEntity<?> getAllTags() {
        return ResponseEntity.ok().body(tagService.getAllTags());
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getTagByID(@PathVariable String id) {
        return ResponseEntity.ok().body(tagService.getTagByID(id));
    }

    @GetMapping("/search")
    private ResponseEntity<?> searchTagsByName(@RequestParam String name) {
        return ResponseEntity.ok().body(tagService.searchTagsByName(name));
    }

    @PostMapping()
    public ResponseEntity<?> createNewTag(@RequestBody TagRequestEntity dto) {
        return ResponseEntity.ok().body(tagService.createNewTag(dto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingTag(@PathVariable String id,
                                             @RequestBody TagRequestEntity dto) {
        return ResponseEntity.ok().body(tagService.updateExistingTag(id, dto));
    }

    @DeleteMapping()
    private ResponseEntity<?> deleteTagByID(@RequestParam String id) {
        return ResponseEntity.ok().body(tagService.deleteTagByID(id));
    }
}
