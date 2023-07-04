package hoangdung.springboot.projecthighlands.service;

import hoangdung.springboot.projecthighlands.config.aop.MultipleTransferToResponseEntities;
import hoangdung.springboot.projecthighlands.config.aop.TranferToResponseEntity;
import hoangdung.springboot.projecthighlands.config.aop.Transformable;
import hoangdung.springboot.projecthighlands.model.dao.User;
import hoangdung.springboot.projecthighlands.model.request.UserRequestEntity;
import hoangdung.springboot.projecthighlands.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    @TranferToResponseEntity
    public Transformable getUserById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> getAllUsers() {
        return userRepository.findAll();
    }

    @MultipleTransferToResponseEntities
    public List<? extends Transformable> searchUsersByName(String name) {
        return userRepository.findUsersByUserNameContainingIgnoreCase(name);
    }


    @TranferToResponseEntity
    public Transformable createNewUser(UserRequestEntity entity) {
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
    public Transformable updateExistingUser(String id, UserRequestEntity entity) {
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

        return userRepository.save(loadedUser);
    }

    @TranferToResponseEntity
    public Transformable updateUserRoleOfExistingUser(String id, String newRole) {
        return userRepository.findById(id)
                .map(loadedUser -> {
                    loadedUser.setRole(User.UserRole.valueOf(newRole.toString()));
                    return userRepository.save(loadedUser);
                }).orElseThrow();
    }

    @TranferToResponseEntity
    public Transformable deleteUserByID(String id) {
        User loadedUser = userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
        return loadedUser;
    }

}

