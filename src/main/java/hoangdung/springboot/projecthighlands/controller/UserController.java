package hoangdung.springboot.projecthighlands.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import hoangdung.springboot.projecthighlands.model.request.AddressesRequestEntity;
import hoangdung.springboot.projecthighlands.model.request.UserRequestEntity;
import hoangdung.springboot.projecthighlands.model.response.UserResponseEntity;
import hoangdung.springboot.projecthighlands.service.AddressesService;
import hoangdung.springboot.projecthighlands.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hoangdung.springboot.projecthighlands.common.CommonUtils.controllerWrapper;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    private final AddressesService addressesService;


    @Operation(summary = "Get All User")
    @GetMapping
    public ResponseEntity<?> getAllUser() throws JsonProcessingException {
        return controllerWrapper(userService::getAllUsers);
//        return  ResponseEntity.ok().body(userService.getAllUsers());
    }

    @Operation(summary = "Get User By UserID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable String id) {
        return controllerWrapper(() -> userService.getUserById(id));
    }

    @Operation(summary = "Search User By User Name")
    @GetMapping("/search")
    public ResponseEntity<?> searchUsersByName(@RequestParam String name)  {
        return controllerWrapper(() -> userService.searchUsersByName(name));
    }

    @Operation(summary = "Create New User")
    @PostMapping()
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserRequestEntity dto)  {
        return controllerWrapper(() -> userService.createNewUser(dto));
    }

    @Operation(summary = "Update Existing User By User ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExistingUser(@PathVariable String id,
                                                 @RequestBody UserRequestEntity dto) {

        // PUT /users/{id}      => userWithId.update(newUser)
        // PUT /users/{id}/role => userWithId.setRole(newRole)

        return controllerWrapper(() -> userService.updateExistingUser(id, dto));
    }

    @Operation(summary = "Update User Role By User ID")
    @PutMapping("/{id}/role")
    public ResponseEntity<?> updateUserRoleOfExistingUser(@PathVariable String id,
                                                           @RequestParam String newRole) {
        return controllerWrapper(() -> userService.updateUserRoleOfExistingUser(id, newRole));
    }

    @Operation(summary = "Delete User By User ID")
    @DeleteMapping()
    public ResponseEntity<?> deleteUserByID(@RequestParam String id) {
        return controllerWrapper(() -> userService.deleteUserByID(id));
    }


    // Addresses Controller

    @Operation(summary = "Get Addresses By Addresses ID")
    @GetMapping("/{id}/adrresses/{addressID}")
    public ResponseEntity<?> getAddressesById(@PathVariable String id,
                                               @PathVariable String addressID) {
        return controllerWrapper(() -> addressesService.getAddressesById(addressID));
//        return ResponseEntity.ok().body(addressesService.getAddressesById(addressID));
    }

    @Operation(summary = "Get All Addresses By User ID")
    @GetMapping("/{id}/addresses")
    public ResponseEntity<?> getAllAddressesByUserID(@PathVariable String id) {
        return controllerWrapper(() -> addressesService.getAllAddressesByUserID(id));
    }

    @Operation(summary = "Create New Addresses")
    @PostMapping("/{id}/addresses")
    public ResponseEntity<?> createNewAddresses(@PathVariable String id,
                                                @RequestBody AddressesRequestEntity requestEntity) {
        return controllerWrapper(() -> addressesService.createNewAddresses(id, requestEntity));
    }

    @Operation(summary = "Update Existing Addresses By Addresses ID")
    @PutMapping("/{id}/addresses/{addressID}")
    public ResponseEntity<?> updateExistingAddresses(@PathVariable String id,
                                                      @PathVariable String addressID,
                                                      @RequestBody AddressesRequestEntity requestEntity) {
        return controllerWrapper(() -> addressesService.updateExistingAddresses(addressID, requestEntity));
    }

    @Operation(summary = "Delete Existing Addresses By Addresses ID")
    @DeleteMapping("/{id}/addresses/{addressID}")
    public ResponseEntity<?> deleteAddressesByID(@PathVariable String id,
                                                  @PathVariable String addressID) {
        return controllerWrapper(() -> addressesService.deleteAddressesByID(addressID));
    }


}
