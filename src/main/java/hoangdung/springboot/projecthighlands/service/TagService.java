package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dto.TagDto;
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
                .map(TagResponseEntity::fromTagDto)
                .toList();
    }

    public Tranformable getTagByID(String id) {
        return tagRepository.findById(id).orElseThrow();
    }

    public List<TagResponseEntity> searchTagsByName(String name) {
        return tagRepository.findTagsByTagNameContainingIgnoreCase(name).stream()
                .map(TagResponseEntity::fromTagDto)
                .toList();
    }


    @TranferToResponseEntity
    public Tranformable createNewTag(TagRequestEntity dto) {
        return tagRepository.save(TagDto.builder()
                .tagName(dto.getTagName())
                .tagColor(dto.getTagColor())
                .textDescription(dto.getTextDescription())
                .build());
    }

    @TranferToResponseEntity
    public Tranformable updateExistingTag(String id, TagRequestEntity dto) {
        TagDto loadedTag = tagRepository.findById(id).orElseThrow();

        loadedTag.setTagName(dto.getTagName());
        loadedTag.setTagColor(dto.getTagColor());
        loadedTag.setTextDescription(dto.getTextDescription());

        return tagRepository.save(loadedTag);
    }

    @TranferToResponseEntity
    public Tranformable deleteTagByID(String id) {
        TagDto loadedTag = tagRepository.findById(id).orElseThrow();
        tagRepository.deleteById(id);
        return loadedTag;
    }
}
