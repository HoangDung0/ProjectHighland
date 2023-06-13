package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dto.ToppingDto;
import hoangdung.springboot.projecthighlands.model.request.ToppingRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.ToppingResponseEntity;
import hoangdung.springboot.projecthighlands.repository.ToppingRepository;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToppingService {
    private final ToppingRepository toppingRepository;

    public List<ToppingResponseEntity> getAllToppings() {
        return toppingRepository.findAll().stream()
                .map(ToppingResponseEntity::fromToppingDto)
                .toList();
    }

    @TranferToResponseEntity
    public Tranformable getToppingById(String id) {
        return toppingRepository.findById(id).orElseThrow();
    }

    public List<ToppingResponseEntity> searchToppingsByName(String name) {
        return toppingRepository.findToppingsByToppingNameContainingIgnoreCase(name).stream()
                .map(ToppingResponseEntity::fromToppingDto)
                .toList();
    }

    @TranferToResponseEntity
    public Tranformable createNewTopping(ToppingRequestEntity dto) {
        return toppingRepository.save(ToppingDto.builder()
                .toppingName(dto.getToppingName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .thumbnailUrl(dto.getThumbnailUrl())
                .build());
    }

    @TranferToResponseEntity
    public Tranformable updateExistingTopping(String id, ToppingRequestEntity dto) {
        ToppingDto loadedTopping = toppingRepository.findById(id).orElseThrow();

        loadedTopping.setToppingName(dto.getToppingName());
        loadedTopping.setPrice(dto.getPrice());
        loadedTopping.setDescription(dto.getDescription());
        loadedTopping.setThumbnailUrl(dto.getThumbnailUrl());

        return toppingRepository.save(loadedTopping);
    }

    @TranferToResponseEntity
    public Tranformable deleteToppingByID(String id) {
        ToppingDto loadedTopping = toppingRepository.findById(id).orElseThrow();
        toppingRepository.deleteById(id);
        return loadedTopping;
    }
}
