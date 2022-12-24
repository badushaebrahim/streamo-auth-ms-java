package streamo.server.auth.bootstrap.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import streamo.server.auth.bootstrap.enums.CountryCodeEnum;
import streamo.server.auth.bootstrap.model.command.DeleteUserCommand;
import streamo.server.auth.bootstrap.model.command.SignInCommand;
import streamo.server.auth.bootstrap.model.command.SignUpCommand;
import streamo.server.auth.bootstrap.model.command.UpdateProfileCommand;
import streamo.server.auth.bootstrap.model.request.DeleteUserRequest;
import streamo.server.auth.bootstrap.model.request.SignInRequest;
import streamo.server.auth.bootstrap.model.request.SignupRequest;
import streamo.server.auth.bootstrap.model.request.UpdateProfileRequest;
import streamo.server.auth.bootstrap.model.response.DeleteUserResponse;
import streamo.server.auth.bootstrap.model.response.SignInResponse;
import streamo.server.auth.bootstrap.model.response.SignUpResponse;
import streamo.server.auth.bootstrap.model.response.UpdateProfileResponse;
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

    @PutMapping("/user/update")
    public UpdateProfileResponse updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest, @RequestHeader CountryCodeEnum userCountry, @RequestHeader String uuid){
        UpdateProfileCommand command = new UpdateProfileCommand();
        command.setUpdateProfileRequest(updateProfileRequest);
        command.setUserCountry(userCountry);
        command.setUuid(uuid);
        return authService.updateUserProfile(command);
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