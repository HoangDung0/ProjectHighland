package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dao.Tag;
import hoangdung.springboot.projecthighlands.model.request.TagRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.TagResponseEntity;
import hoangdung.springboot.projecthighlands.repository.TagRepository;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<TagResponseEntity> getAllTags() {
        return tagRepository.findAll().stream()
                .map(TagResponseEntity::fromTag)
                .toList();
    }

    public Tranformable getTagByID(String id) {
        return tagRepository.findById(id).orElseThrow();
    }

    public List<TagResponseEntity> searchTagsByName(String name) {
        return tagRepository.findTagsByTagNameContainingIgnoreCase(name).stream()
                .map(TagResponseEntity::fromTag)
                .toList();
    }


    @TranferToResponseEntity
    public Tranformable createNewTag(TagRequestEntity entity) {
        return tagRepository.save(Tag.builder()
                .tagName(entity.getTagName())
                .tagColor(entity.getTagColor())
                .textDescription(entity.getTextDescription())
                .build());
    }

    @TranferToResponseEntity
    public Tranformable updateExistingTag(String id, TagRequestEntity entity) {
        Tag loadedTag = tagRepository.findById(id).orElseThrow();

        loadedTag.setTagName(entity.getTagName());
        loadedTag.setTagColor(entity.getTagColor());
        loadedTag.setTextDescription(entity.getTextDescription());

        return tagRepository.save(loadedTag);
    }

    @TranferToResponseEntity
    public Tranformable deleteTagByID(String id) {
        Tag loadedTag = tagRepository.findById(id).orElseThrow();
        tagRepository.deleteById(id);
        return loadedTag;
    }
}
