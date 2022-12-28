package streamo.server.auth.bootstrap.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import streamo.server.auth.bootstrap.enums.CountryCodeEnum;
import streamo.server.auth.bootstrap.model.command.*;
import streamo.server.auth.bootstrap.model.request.*;
import streamo.server.auth.bootstrap.model.response.*;
import streamo.server.auth.bootstrap.repository.AuthRepository;
import streamo.server.auth.bootstrap.service.AuthService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    AuthRepository authRepository;

    @ApiOperation(value = "Create profile for user")
    @PostMapping("/user/sign-up")
    public ResponseEntity<SignUpResponse> createProfile(@RequestBody SignupRequest signupRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        SignUpCommand command = new SignUpCommand();
        command.setSignupRequest(signupRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
    return new ResponseEntity<>(authService.userSignup(command), HttpStatus.OK);
    }

    @ApiOperation(value = "User Sign-in")
    @PostMapping("/user/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        SignInCommand command = new SignInCommand();
        command.setSignInRequest(signInRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
        return new ResponseEntity<>(authService.userSignIn(command), HttpStatus.OK);
    }

    @ApiOperation(value = "Update user profile")
    @PutMapping("/user/update-profile")
    public ResponseEntity<UpdateProfileResponse> updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        UpdateProfileCommand command = new UpdateProfileCommand();
        command.setUpdateProfileRequest(updateProfileRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
        return new ResponseEntity<>(authService.updateUserProfile(command), HttpStatus.OK);
    }

    @ApiOperation(value = "update user password")
    @PutMapping("/user/update-password")
    public ResponseEntity<UpdatePasswordResponse> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        UpdatePasswordCommand command = new UpdatePasswordCommand();
        command.setUpdatePasswordRequest(updatePasswordRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
        return new ResponseEntity<>(authService.updateUserPassword(command), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user profile")
    @DeleteMapping("/user/delete")
    public ResponseEntity<DeleteUserResponse> deleteProfile(@RequestBody DeleteUserRequest deleteUserRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        DeleteUserCommand command = new DeleteUserCommand();
        command.setDeleteUserRequest(deleteUserRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
        return new ResponseEntity<>(authService.deleteUser(command), HttpStatus.OK);
    }

}