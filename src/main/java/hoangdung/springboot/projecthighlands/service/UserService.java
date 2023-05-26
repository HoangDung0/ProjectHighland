package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dto.UserDto;
import hoangdung.springboot.projecthighlands.model.request.UserRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.UserResponseEntity;
import hoangdung.springboot.projecthighlands.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserRepository userRepository;

    public List<UserResponseEntity> getAllUsers() {
        List<UserDto> listUserDto = userRepository.findAll();
        List<UserResponseEntity> listUserResponse = new ArrayList<UserResponseEntity>();
        for (UserDto dto : listUserDto ) {
            listUserResponse.add( UserResponseEntity.fromUserDto(dto));
        }
        return listUserResponse;
    }

    public UserResponseEntity getUserById(String id) {
        return UserResponseEntity.fromUserDto(userRepository.findById(id).orElseThrow());
    }

    public List<UserResponseEntity> searchUsersByName(String name) {
        List<UserDto> listUserDto = userRepository.findUsersByUserNameContainingIgnoreCase(name);

        List<UserResponseEntity> listUserResponse = new ArrayList<UserResponseEntity>();
        for (UserDto dto : listUserDto ) {
            listUserResponse.add( UserResponseEntity.fromUserDto(dto));
        }
        return listUserResponse;

    }


    public UserResponseEntity createNewUser(UserRequestEntity dto) {
        UserDto preparedUser = UserDto.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .dayOfBirth(dto.getDayOfBirth())
                .role(UserDto.UserRole.valueOf(dto.getRole()))
                .sex(dto.isSex())
                .createDate(dto.getCreateDate())
                .activated(dto.isActivated())
                .build();

       return UserResponseEntity.fromUserDto(userRepository.save(preparedUser));

    }

    public UserResponseEntity updateExistingUser(String id, UserRequestEntity dto) {
        UserDto loadedUser = userRepository.findById(id).orElseThrow();

        loadedUser.setUserName(dto.getUserName());
        loadedUser.setPassword(dto.getPassword());
        loadedUser.setPhone(dto.getPhone());
        loadedUser.setEmail(dto.getEmail());
        loadedUser.setDayOfBirth(dto.getDayOfBirth());
        loadedUser.setRole(UserDto.UserRole.valueOf(dto.getRole()));
        loadedUser.setSex(dto.isSex());
        loadedUser.setCreateDate(dto.getCreateDate());
        loadedUser.setActivated(dto.isActivated());

        return UserResponseEntity.fromUserDto(userRepository.save(loadedUser));
    }


    public UserResponseEntity updateUserRoleOfExistingUser(String id, String newRole){
        UserDto preparedUser = userRepository.findById(id)
                .map( loadedUser -> {
                    loadedUser.setRole(UserDto.UserRole.valueOf(newRole));
                    return loadedUser;
                }).orElseThrow();

        return UserResponseEntity.fromUserDto(userRepository.save(preparedUser));

    }

    public UserResponseEntity deleteUserByID(String id) {
        UserDto loadedUser = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return UserResponseEntity.fromUserDto(loadedUser);
    }

//    public UserResponseEntity updateListAddressesUser(String id, UserRequestEntity dto, String listAddresses) throws JsonProcessingException{
//        UserDto loadedUser = userRepository.findById(id).orElseThrow();
//        loadedUser.setUserName(dto.getUserName());
//        loadedUser.setPassword(dto.getPassword());
//        loadedUser.setPhone(dto.getPhone());
//        loadedUser.setEmail(dto.getEmail());
//        loadedUser.setDayOfBirth(dto.getDayOfBirth());
//        loadedUser.setRole(UserDto.UserRole.valueOf(dto.getRole()));
//        loadedUser.setSex(dto.isSex());
//        loadedUser.setCreateDate(dto.getCreateDate());
//        loadedUser.setActivated(dto.isActivated());
//
//        return UserResponseEntity.fromUserDto(userRepository.save(loadedUser));
//    }


}

