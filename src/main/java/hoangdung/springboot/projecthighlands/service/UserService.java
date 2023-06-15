package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.model.dao.User;
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
                .map(UserResponseEntity::fromUser)
                .toList();
    }


    public List<UserResponseEntity> searchUsersByName(String name) {
        return userRepository.findUsersByUserNameContainingIgnoreCase(name).stream()
                .map(UserResponseEntity::fromUser)
                .toList();
    }


    @TranferToResponseEntity
    public Tranformable createNewUser(UserRequestEntity entity) {
        return userRepository.save(User.builder()
                .userName(entity.getUserName())
                .password(entity.getPassword())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .dayOfBirth(entity.getDayOfBirth())
                .role(User.UserRole.valueOf(entity.getRole()))
                .sex(entity.isSex())
                .createDate(entity.getCreateDate())
                .activated(entity.isActivated())
                .build());
    }

    @TranferToResponseEntity
    public Tranformable updateExistingUser(String id, UserRequestEntity entity) {
        User loadedUser = userRepository.findById(id).orElseThrow();

        loadedUser.setUserName(entity.getUserName());
        loadedUser.setPassword(entity.getPassword());
        loadedUser.setPhone(entity.getPhone());
        loadedUser.setEmail(entity.getEmail());
        loadedUser.setDayOfBirth(entity.getDayOfBirth());
        loadedUser.setRole(User.UserRole.valueOf(entity.getRole()));
        loadedUser.setSex(entity.isSex());
        loadedUser.setCreateDate(entity.getCreateDate());
        loadedUser.setActivated(entity.isActivated());

        return loadedUser;
    }

    @TranferToResponseEntity
    public Tranformable updateUserRoleOfExistingUser(String id, String newRole) {
        return userRepository.findById(id)
                .map(loadedUser -> {
                    loadedUser.setRole(User.UserRole.valueOf(newRole));
                    return loadedUser;
                }).orElseThrow();
    }

    @TranferToResponseEntity
    public Tranformable deleteUserByID(String id) {
        User loadedUser = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return loadedUser;
    }

}

