package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.config.aop.MultipleTransferToResponseEntities;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.Tag;
import hoangdung.springboot.projecthighlands.model.request.TagRequestEntity;
import hoangdung.springboot.projecthighlands.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> getAllTags() {
        return tagRepository.findAll();
    }

    @TranferToResponseEntity
    public Transformable getTagByID(String id) {
        return tagRepository.findById(id).orElseThrow();
    }

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> searchTagsByName(String name) {
        return tagRepository.findTagsByTagNameContainingIgnoreCase(name);
    }


    @TranferToResponseEntity
    public Transformable createNewTag(TagRequestEntity entity) {
        return tagRepository.save(Tag.builder()
                .tagName(entity.getTagName())
                .tagColor(entity.getTagColor())
                .textDescription(entity.getTextDescription())
                .build());
    }

    @TranferToResponseEntity
    public Transformable updateExistingTag(String id, TagRequestEntity entity) {
        Tag loadedTag = tagRepository.findById(id).orElseThrow();

        loadedTag.setTagName(entity.getTagName());
        loadedTag.setTagColor(entity.getTagColor());
        loadedTag.setTextDescription(entity.getTextDescription());

        return tagRepository.save(loadedTag);
    }

    @TranferToResponseEntity
    public Transformable deleteTagByID(String id) {
        Tag loadedTag = tagRepository.findById(id).orElseThrow();
        tagRepository.deleteById(id);
        return loadedTag;
    }
}
