package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.config.aop.MultipleTransferToResponseEntities;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import hoangdung.springboot.projecthighlands.model.dto.AddressesDto;
import hoangdung.springboot.projecthighlands.model.request.AddressesRequestEntity;
import hoangdung.springboot.projecthighlands.repository.AddressesRepository;
import hoangdung.springboot.projecthighlands.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressesService {

    private final AddressesRepository addressesRepository;

    private final UserRepository userRepository;

    @TranferToResponseEntity
    public Tranformable getAddressesById(String id) {
        return addressesRepository.findById(id).orElseThrow();
    }

    @MultipleTransferToResponseEntities
    public List<Tranformable> getAllAddressesByUserID(String id) {
//        return addressesRepository.getListAddressesByUserID(id)
//                .stream()
//                .map(AddressesResponseEntity::fromAddressesDto)
//                .toList();
        return addressesRepository.getListAddressesByUserID(id);

    }

    @TranferToResponseEntity
    public Tranformable createNewAddresses(String userID, AddressesRequestEntity requestEntity) {
        return addressesRepository.save(AddressesDto.builder()
                .addressesName(requestEntity.getAddressesName())
                .address1(requestEntity.getAddress1())
                .address2(requestEntity.getAddress2())
                .address3(requestEntity.getAddress3())
                .address4(requestEntity.getAddress4())
                .phoneNumber(requestEntity.getPhoneNumber())
                .userDto(userRepository.findById(requestEntity.getUserID()).orElseThrow())
                .build());
    }

    @TranferToResponseEntity
    public Tranformable updateExistingAddresses(String id, AddressesRequestEntity requestEntity) {
        AddressesDto loadedAddresses = addressesRepository.findById(id).orElseThrow();

        loadedAddresses.setAddressesName(requestEntity.getAddressesName());
        loadedAddresses.setAddress1(requestEntity.getAddress1());
        loadedAddresses.setAddress2(requestEntity.getAddress2());
        loadedAddresses.setAddress3(requestEntity.getAddress3());
        loadedAddresses.setAddress4(requestEntity.getAddress4());
        loadedAddresses.setUserDto(userRepository.findById(requestEntity.getUserID()).orElseThrow());
        loadedAddresses.setPhoneNumber(requestEntity.getPhoneNumber());

        return addressesRepository.save(loadedAddresses);
    }

    @TranferToResponseEntity
    public Tranformable deleteAddressesByID(String id) {
        AddressesDto loadedAddresses = addressesRepository.findById(id).orElseThrow();
        addressesRepository.deleteById(id);
        return loadedAddresses;
    }
}
