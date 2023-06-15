package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dao.Topping;
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
                .map(ToppingResponseEntity::fromTopping)
                .toList();
    }

    @TranferToResponseEntity
    public Tranformable getToppingById(String id) {
        return toppingRepository.findById(id).orElseThrow();
    }

    public List<ToppingResponseEntity> searchToppingsByName(String name) {
        return toppingRepository.findToppingsByToppingNameContainingIgnoreCase(name).stream()
                .map(ToppingResponseEntity::fromTopping)
                .toList();
    }

    @TranferToResponseEntity
    public Tranformable createNewTopping(ToppingRequestEntity entity) {
        return toppingRepository.save(Topping.builder()
                .toppingName(entity.getToppingName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .thumbnailUrl(entity.getThumbnailUrl())
                .build());
    }

    @TranferToResponseEntity
    public Tranformable updateExistingTopping(String id, ToppingRequestEntity entity) {
        Topping loadedTopping = toppingRepository.findById(id).orElseThrow();

        loadedTopping.setToppingName(entity.getToppingName());
        loadedTopping.setPrice(entity.getPrice());
        loadedTopping.setDescription(entity.getDescription());
        loadedTopping.setThumbnailUrl(entity.getThumbnailUrl());

        return toppingRepository.save(loadedTopping);
    }

    @TranferToResponseEntity
    public Tranformable deleteToppingByID(String id) {
        Topping loadedTopping = toppingRepository.findById(id).orElseThrow();
        toppingRepository.deleteById(id);
        return loadedTopping;
    }
}
