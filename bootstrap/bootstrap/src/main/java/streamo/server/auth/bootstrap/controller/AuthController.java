package streamo.server.auth.bootstrap.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import streamo.server.auth.bootstrap.enums.CountryCodeEnum;
import streamo.server.auth.bootstrap.model.command.*;
import streamo.server.auth.bootstrap.model.request.*;
import streamo.server.auth.bootstrap.model.response.*;
import streamo.server.auth.bootstrap.service.AuthService;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/user/sign-up")
    public SignUpResponse createProfile(@RequestBody SignupRequest signupRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        SignUpCommand command = new SignUpCommand();
        command.setSignupRequest(signupRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
        return authService.userSignup(command);
    }

    @PostMapping("/user/sign-in")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        SignInCommand command = new SignInCommand();
        command.setSignInRequest(signInRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
        return authService.userSignIn(command);
    }

    @PutMapping("/user/update-profile")
    public UpdateProfileResponse updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        UpdateProfileCommand command = new UpdateProfileCommand();
        command.setUpdateProfileRequest(updateProfileRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
        return authService.updateUserProfile(command);
    }

    @PutMapping("/user/update-password")
    public UpdatePasswordResponse updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        UpdatePasswordCommand command = new UpdatePasswordCommand();
        command.setUpdatePasswordRequest(updatePasswordRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
        return authService.updateUserPassword(command);
    }

    @DeleteMapping("/user/delete")
    public DeleteUserResponse deleteProfile(@RequestBody DeleteUserRequest deleteUserRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        DeleteUserCommand command = new DeleteUserCommand();
        command.setDeleteUserRequest(deleteUserRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
        return authService.deleteUser(command);
    }

}