package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.TagRequestEntity;
import hoangdung.springboot.projecthighlands.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    @GetMapping
    private ResponseEntity<?> getAllTags() {
        return controllerWrapper(tagService::getAllTags);
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getTagByID(@PathVariable String id) {
        return controllerWrapper(() -> tagService.getTagByID(id));
    }

    @GetMapping("/search")
    private ResponseEntity<?> searchTagsByName(@RequestParam String name) {
        return controllerWrapper(() -> tagService.searchTagsByName(name));
    }

    @PostMapping()
    public ResponseEntity<?> createNewTag(@RequestBody TagRequestEntity dto) {
        return controllerWrapper(() -> tagService.createNewTag(dto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingTag(@PathVariable String id,
                                             @RequestBody TagRequestEntity dto) {
        return controllerWrapper(() -> tagService.updateExistingTag(id, dto));
    }

    @DeleteMapping()
    private ResponseEntity<?> deleteTagByID(@RequestParam String id) {
        return controllerWrapper(() -> tagService.deleteTagByID(id));
    }
}
