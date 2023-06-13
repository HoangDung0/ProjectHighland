package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dto.UserDto;
import hoangdung.springboot.projecthighlands.model.request.UserRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.UserResponseEntity;
import hoangdung.springboot.projecthighlands.repository.UserRepository;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Tranformable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @TranferToResponseEntity
    public Tranformable getUserById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    public List<UserResponseEntity> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseEntity::fromUserDto)
                .toList();
    }


    public List<UserResponseEntity> searchUsersByName(String name) {
        return userRepository.findUsersByUserNameContainingIgnoreCase(name).stream()
                .map(UserResponseEntity::fromUserDto)
                .toList();
    }


    @TranferToResponseEntity
    public Tranformable createNewUser(UserRequestEntity dto) {
        return userRepository.save(UserDto.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .dayOfBirth(dto.getDayOfBirth())
                .role(UserDto.UserRole.valueOf(dto.getRole()))
                .sex(dto.isSex())
                .createDate(dto.getCreateDate())
                .activated(dto.isActivated())
                .build());
    }

    @TranferToResponseEntity
    public Tranformable updateExistingUser(String id, UserRequestEntity dto) {
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

        return loadedUser;
    }

    @TranferToResponseEntity
    public Tranformable updateUserRoleOfExistingUser(String id, String newRole) {
        return userRepository.findById(id)
                .map(loadedUser -> {
                    loadedUser.setRole(UserDto.UserRole.valueOf(newRole));
                    return loadedUser;
                }).orElseThrow();
    }

    @TranferToResponseEntity
    public Tranformable deleteUserByID(String id) {
        UserDto loadedUser = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return loadedUser;
    }

}

