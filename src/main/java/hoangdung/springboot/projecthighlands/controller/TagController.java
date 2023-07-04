package hoangdung.springboot.projecthighlands.controller;

import hoangdung.springboot.projecthighlands.model.request.TagRequestEntity;
import hoangdung.springboot.projecthighlands.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    @Operation(summary = "Get All Tags")
    @GetMapping
    public ResponseEntity<?> getAllTags() {
        return controllerWrapper(tagService::getAllTags);
    }

    @Operation(summary = "Get Tag By Tag ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTagByID(@PathVariable String id) {
        return controllerWrapper(() -> tagService.getTagByID(id));
    }

    @Operation(summary = "Get Tag By Tag Name")
    @GetMapping("/search")
    public ResponseEntity<?> searchTagsByName(@RequestParam String name) {
        return controllerWrapper(() -> tagService.searchTagsByName(name));
    }

    @Operation(summary = "Create New Tag")
    @PostMapping()
    public ResponseEntity<?> createNewTag(@RequestBody TagRequestEntity dto) {
        return controllerWrapper(() -> tagService.createNewTag(dto));
    }

    @Operation(summary = "Update Existing Tag")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExistingTag(@PathVariable String id,
                                             @RequestBody TagRequestEntity dto) {
        return controllerWrapper(() -> tagService.updateExistingTag(id, dto));
    }

    @Operation(summary = "Delete Existing Tag")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTagByID(@PathVariable String id) {
        return controllerWrapper(() -> tagService.deleteTagByID(id));
    }
}
