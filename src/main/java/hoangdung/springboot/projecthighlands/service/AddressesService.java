package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dto.AddressesDto;
import hoangdung.springboot.projecthighlands.model.request.AddressesRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.AddressesResponseEntity;
import hoangdung.springboot.projecthighlands.repository.AddressesRepository;
import hoangdung.springboot.projecthighlands.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressesService {

    private static AddressesRepository addressesRepository;

    private static UserRepository userRepository;

//    public static ObjectMapper objectMapper = new ObjectMapper();
//
//    public static List<AddressesDto> convertListAddressesIDToListAddresses(String listAddressesID) throws JsonProcessingException {
//        return (List<AddressesDto>) objectMapper.readValue(listAddressesID, List.class)
//                .stream()
//                .map( s -> addressesRepository.findById(s.toString()))
//                .toList();
//    }
//
//    public String convertListAddressesToListAddressesID(List<AddressesDto> listAddressDto) throws JsonProcessingException {
//        List<String> listAddressID = listAddressDto
//                .stream()
//                .map( addresses -> addresses.getAddressesID())
//                .toList();
//        return objectMapper.writeValueAsString(listAddressID);
//    }

    public AddressesResponseEntity getAddressesById(String id) {
        return AddressesResponseEntity.fromAddressesDto(addressesRepository.findById(id).orElseThrow());
    }

    public List<AddressesResponseEntity> getAllAddressesByUserID(String id) {
//        String listAddressesID = addressesRepository.getListAddressesID(id);
        List<AddressesDto> listAddressesDto = addressesRepository.getListAddressesByUserID(id);
        List<AddressesResponseEntity> listAddressesResponseEntity = new ArrayList<>();
        for (AddressesDto addressDto : listAddressesDto) {
           listAddressesResponseEntity.add(AddressesResponseEntity.fromAddressesDto(addressDto));
        }
        return listAddressesResponseEntity;

    }

    public AddressesResponseEntity createNewAddresses(String userID, AddressesRequestEntity requestEntity) {
        AddressesDto preparedAddresses = AddressesDto.builder()
                .addressesName(requestEntity.getAddressesName())
                .address1(requestEntity.getAddress1())
                .address2(requestEntity.getAddress2())
                .address3(requestEntity.getAddress3())
                .address4(requestEntity.getAddress4())
                .phoneNumber(requestEntity.getPhoneNumber())
                .userDto(userRepository.findById(requestEntity.getUserID()).orElseThrow())
                .build();

        return AddressesResponseEntity.fromAddressesDto(addressesRepository.save(preparedAddresses));

    }

    public AddressesResponseEntity updateExistingAddresses(String id, AddressesRequestEntity requestEntity) {
        AddressesDto loadedAddresses = addressesRepository.findById(id).orElseThrow();

        loadedAddresses.setAddressesName(requestEntity.getAddressesName());
        loadedAddresses.setAddress1(requestEntity.getAddress1());
        loadedAddresses.setAddress2(requestEntity.getAddress2());
        loadedAddresses.setAddress3(requestEntity.getAddress3());
        loadedAddresses.setAddress4(requestEntity.getAddress4());
        loadedAddresses.setUserDto(userRepository.findById(requestEntity.getUserID()).orElseThrow());
        loadedAddresses.setPhoneNumber(requestEntity.getPhoneNumber());

        return AddressesResponseEntity.fromAddressesDto(addressesRepository.save(loadedAddresses));
    }

    public AddressesResponseEntity deleteAddressesByID(String id) {
        AddressesDto loadedAddresses = addressesRepository.findById(id).orElseThrow();
        addressesRepository.deleteById(id);
        return AddressesResponseEntity.fromAddressesDto(loadedAddresses);
    }
}
