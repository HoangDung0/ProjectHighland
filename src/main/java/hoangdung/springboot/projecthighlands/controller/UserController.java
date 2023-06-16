package hoangdung.springboot.projecthighlands.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hoangdung.springboot.projecthighlands.model.request.AddressesRequestEntity;
import hoangdung.springboot.projecthighlands.model.request.UserRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.UserResponseEntity;
import hoangdung.springboot.projecthighlands.service.AddressesService;
import hoangdung.springboot.projecthighlands.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public final AddressesService addressesService;


    public UserRequestEntity userRequestEntity;

    @GetMapping
    private ResponseEntity<?> getAllUser() throws JsonProcessingException {
        return controllerWrapper(userService::getAllUsers);
//        return  ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getUserByID(@PathVariable String id) {
        return controllerWrapper(() -> userService.getUserById(id));
    }

    @GetMapping("/search")
    private ResponseEntity<?> searchUsersByName(@RequestParam String name)  {
        return controllerWrapper(() -> userService.searchUsersByName(name));
    }

    @PostMapping()
    public ResponseEntity<?> createNewUser(@RequestBody UserRequestEntity dto)  {
        return controllerWrapper(() -> userService.createNewUser(dto));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateExistingUser(@PathVariable String id,
                                                 @RequestBody UserRequestEntity dto) {

        // PUT /users/{id}      => userWithId.update(newUser)
        // PUT /users/{id}/role => userWithId.setRole(newRole)

        return controllerWrapper(() -> userService.updateExistingUser(id, dto));
    }

    @PutMapping("/{id}/role")
    private ResponseEntity<?> updateUserRoleOfExistingUser(@PathVariable String id,
                                                           @RequestBody String newRole) {
        return controllerWrapper(() -> userService.updateUserRoleOfExistingUser(id, newRole));
    }

    @DeleteMapping()
    private ResponseEntity<?> deleteUserByID(@RequestParam String id) {
        return controllerWrapper(() -> userService.deleteUserByID(id));
    }


    // Addresses Controller

    @GetMapping("/{id}/adrresses/{addressID}")
    private ResponseEntity<?> getAddressesById(@PathVariable String id,
                                               @PathVariable String addressID) {
        return controllerWrapper(() -> addressesService.getAddressesById(addressID));
//        return ResponseEntity.ok().body(addressesService.getAddressesById(addressID));
    }

    @GetMapping("/{id}/addresses")
    private ResponseEntity<?> getAllAddressesByUserID(@PathVariable String id) {
        return controllerWrapper(() -> addressesService.getAllAddressesByUserID(id));
    }

    @PostMapping("/{id}/addresses")
    public ResponseEntity<?> createNewAddresses(@PathVariable String id,
                                                @RequestBody AddressesRequestEntity requestEntity) {
        return controllerWrapper(() -> addressesService.createNewAddresses(id, requestEntity));
    }

    @PutMapping("/{id}/addresses/{addressID}")
    private ResponseEntity<?> updateExistingAddresses(@PathVariable String id,
                                                      @PathVariable String addressID,
                                                      @RequestBody AddressesRequestEntity requestEntity) {
        return controllerWrapper(() -> addressesService.updateExistingAddresses(addressID, requestEntity));
    }

    @DeleteMapping("/{id}/addresses/{addressID}")
    private ResponseEntity<?> deleteAddressesByID(@PathVariable String id,
                                                  @PathVariable String addressID) {
        return controllerWrapper(() -> addressesService.deleteAddressesByID(addressID));
    }


}
