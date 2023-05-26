package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dto.TagDto;
import hoangdung.springboot.projecthighlands.model.request.TagRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.TagResponseEntity;
import hoangdung.springboot.projecthighlands.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<TagResponseEntity> getAllTags() {
        List<TagDto> listTagDto = tagRepository.findAll();
        List<TagResponseEntity> listTagResponse = new ArrayList<>();
        for (TagDto dto : listTagDto ) {
            listTagResponse.add(TagResponseEntity.fromTagDto(dto));
        }
        return listTagResponse;
    }

    public TagResponseEntity getTagByID(String id) {
        return TagResponseEntity.fromTagDto(tagRepository.findById(id).orElse(null));
    }

    public List<TagResponseEntity> searchTagsByName(String name) {
        List<TagDto> listTagDto = tagRepository.findTagsByTagNameContainingIgnoreCase(name);

        // đổi thành stream api và optional pattern
        List<TagResponseEntity> listTagResponse = new ArrayList<>();
        for (TagDto dto : listTagDto ) {
            listTagResponse.add( TagResponseEntity.fromTagDto(dto));
        }

        return listTagResponse;

    }


    public TagResponseEntity createNewTag(TagRequestEntity dto) {
        TagDto preparedTag = TagDto.builder()
                .tagName(dto.getTagName())
                .tagColor(dto.getTagColor())
                .textDescription(dto.getTextDescription())
                .build();

        return TagResponseEntity.fromTagDto(tagRepository.save(preparedTag));

    }

    public TagResponseEntity updateExistingTag(String id, TagRequestEntity dto){
        TagDto loadedTag = tagRepository.findById(id).orElseThrow();

        loadedTag.setTagName(dto.getTagName());
        loadedTag.setTagColor(dto.getTagColor());
        loadedTag.setTextDescription(dto.getTextDescription());

        return TagResponseEntity.fromTagDto(tagRepository.save(loadedTag));
    }


    public TagResponseEntity deleteTagByID(String id) {
        TagDto loadedTag = tagRepository.findById(id).orElseThrow();
        tagRepository.deleteById(id);
        return TagResponseEntity.fromTagDto(loadedTag);
    }
}
