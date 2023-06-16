package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.config.aop.MultipleTransferToResponseEntities;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.Topping;
import hoangdung.springboot.projecthighlands.model.request.ToppingRequestEntity;
import hoangdung.springboot.projecthighlands.repository.ToppingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToppingService {
    private final ToppingRepository toppingRepository;

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> getAllToppings() {
        return toppingRepository.findAll();
    }

    @TranferToResponseEntity
    public Transformable getToppingById(String id) {
        return toppingRepository.findById(id).orElseThrow();
    }

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> searchToppingsByName(String name) {
        return toppingRepository.findToppingsByToppingNameContainingIgnoreCase(name);
    }


    @TranferToResponseEntity
    public Transformable createNewTopping(ToppingRequestEntity entity) {
        return toppingRepository.save(Topping.builder()
                .toppingName(entity.getToppingName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .thumbnailUrl(entity.getThumbnailUrl())
                .build());
    }

    @TranferToResponseEntity
    public Transformable updateExistingTopping(String id, ToppingRequestEntity entity) {
        Topping loadedTopping = toppingRepository.findById(id).orElseThrow();

        loadedTopping.setToppingName(entity.getToppingName());
        loadedTopping.setPrice(entity.getPrice());
        loadedTopping.setDescription(entity.getDescription());
        loadedTopping.setThumbnailUrl(entity.getThumbnailUrl());

        return toppingRepository.save(loadedTopping);
    }

    @TranferToResponseEntity
    public Transformable deleteToppingByID(String id) {
        Topping loadedTopping = toppingRepository.findById(id).orElseThrow();
        toppingRepository.deleteById(id);
        return loadedTopping;
    }
}
