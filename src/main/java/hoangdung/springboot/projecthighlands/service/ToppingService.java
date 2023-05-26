package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dto.ToppingDto;
import hoangdung.springboot.projecthighlands.model.request.ToppingRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.ToppingResponseEntity;
import hoangdung.springboot.projecthighlands.repository.ToppingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToppingService {
    private final ToppingRepository toppingRepository;

    public List<ToppingResponseEntity> getAllToppings() {
        List<ToppingDto> listToppingDto = toppingRepository.findAll();
        List<ToppingResponseEntity> listToppingResponse = new ArrayList<ToppingResponseEntity>();
        for (ToppingDto dto : listToppingDto ) {
            listToppingResponse.add( ToppingResponseEntity.fromToppingDto(dto));
        }
        return listToppingResponse;
    }

    public ToppingResponseEntity getToppingById(String id) {
        return ToppingResponseEntity.fromToppingDto(toppingRepository.findById(id).orElse(null));
    }

    public List<ToppingResponseEntity> searchToppingsByName(String name) {
        List<ToppingDto> listToppingDto = toppingRepository.findToppingsByToppingNameContainingIgnoreCase(name);

        List<ToppingResponseEntity> listToppingResponse = new ArrayList<>();
        for (ToppingDto dto : listToppingDto ) {
            listToppingResponse.add( ToppingResponseEntity.fromToppingDto(dto));
        }
        return listToppingResponse;

    }


    public ToppingResponseEntity createNewTopping(ToppingRequestEntity dto) {
        ToppingDto preparedTopping = ToppingDto.builder()
                .toppingName(dto.getToppingName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .thumbnailUrl(dto.getThumbnailUrl())
                .build();

        return ToppingResponseEntity.fromToppingDto(toppingRepository.save(preparedTopping));

    }

    public ToppingResponseEntity updateExistingTopping(String id, ToppingRequestEntity dto){
        ToppingDto loadedTopping = toppingRepository.findById(id).orElseThrow();

        loadedTopping.setToppingName(dto.getToppingName());
        loadedTopping.setPrice(dto.getPrice());
        loadedTopping.setDescription(dto.getDescription());
        loadedTopping.setThumbnailUrl(dto.getThumbnailUrl());

        return ToppingResponseEntity.fromToppingDto(toppingRepository.save(loadedTopping));
    }



    public ToppingResponseEntity deleteToppingByID(String id) {
        ToppingDto loadedTopping = toppingRepository.findById(id).orElseThrow();
        toppingRepository.deleteById(id);
        return ToppingResponseEntity.fromToppingDto(loadedTopping);
    }
}
