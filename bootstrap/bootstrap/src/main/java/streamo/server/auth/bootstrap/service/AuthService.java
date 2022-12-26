package streamo.server.auth.bootstrap.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import streamo.server.auth.bootstrap.exceptions.PasswordNotMatchingException;
import streamo.server.auth.bootstrap.exceptions.UserNameAlreadyExistsException;
import streamo.server.auth.bootstrap.exceptions.UserNotFoundException;
import streamo.server.auth.bootstrap.model.command.*;
import streamo.server.auth.bootstrap.model.entity.AuthEntity;
import streamo.server.auth.bootstrap.model.response.*;
import streamo.server.auth.bootstrap.repository.AuthRepository;
import streamo.server.auth.bootstrap.util.JwtTokenUtil;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthService {
    @Autowired
    JwtTokenUtil util;
    private final AuthRepository authRepository;

    AuthService(AuthRepository authRepository){
        this.authRepository = authRepository;
    }

  public SignUpResponse userSignup(SignUpCommand command) {
    AuthEntity authEntity = authRepository.getByUserName(command.getSignupRequest().getUserName());
    if (authEntity == null) {
      AuthEntity newAuthEntity = new AuthEntity();
      BeanUtils.copyProperties(command.getSignupRequest(), newAuthEntity);
      newAuthEntity.setUserCountry(command.getUserCountry().name());
      newAuthEntity.setUserGender(command.getSignupRequest().getUserGender().name());
      newAuthEntity.setUuid(command.getUuid());
      newAuthEntity.setCreatedBy(command.getSignupRequest().getUserName());
      newAuthEntity.setCreatedTime(LocalDateTime.now());
      newAuthEntity.setLastUpdatedBy(command.getSignupRequest().getUserName());
      newAuthEntity.setLastUpdatedTime(LocalDateTime.now());
      authRepository.save(newAuthEntity);
      return new SignUpResponse( newAuthEntity.getUserName());
    }
    else {
      throw new UserNameAlreadyExistsException();
    }
  }

  public SignInResponse userSignIn(SignInCommand command) {
      AuthEntity authEntity = authRepository.getByUserName(command.getSignInRequest().getUserName());
      if(authEntity != null){
          assert false;
          if(authEntity.getUserPassword().equals(command.getSignInRequest().getUserPassword())){

            return new SignInResponse(util.generateToken(authEntity));
        } else{
            throw new PasswordNotMatchingException();
        }
      }else{
          throw new UserNotFoundException();
      }
  }

  public UpdateProfileResponse updateUserProfile(UpdateProfileCommand command) {
        AuthEntity authEntity = authRepository.getByUserName(command.getUpdateProfileRequest().getUserName());
        if(authEntity != null){
            assert false;
            if(authEntity.getUserPassword().equals(command.getUpdateProfileRequest().getUserPassword())){
                AuthEntity newAuthEntity = new AuthEntity();
                String newUsername = StringUtils.isNotBlank(command.getUpdateProfileRequest().getNewUsername()) ? command.getUpdateProfileRequest().getNewUsername() : authEntity.getUserName();
                if((authRepository.getByUserName(command.getUpdateProfileRequest().getNewUsername()) == null) || authEntity.getUserName().equals(newUsername)){
                    newAuthEntity.setUserName(newUsername);
                    newAuthEntity.setUserAge(command.getUpdateProfileRequest().getUserAge());
                    newAuthEntity.setUserGender(command.getUpdateProfileRequest().getUserGender().name());
                    String newUserMail = StringUtils.isNotBlank(command.getUpdateProfileRequest().getUserMail()) ? command.getUpdateProfileRequest().getUserMail() : authEntity.getUserMail();
                    newAuthEntity.setUserMail(newUserMail);
                    String newUserPhoneNo = StringUtils.isNotBlank(command.getUpdateProfileRequest().getUserPhoneNo()) ? command.getUpdateProfileRequest().getUserPhoneNo() : authEntity.getUserPhoneNo();
                    newAuthEntity.setUserPhoneNo(newUserPhoneNo);
                    newAuthEntity.setUserCountry(command.getUserCountry().name());
                    newAuthEntity.setUuid(command.getUuid());
                    newAuthEntity.setUserPassword(authEntity.getUserPassword());
                    newAuthEntity.setCreatedBy(authEntity.getCreatedBy());
                    newAuthEntity.setCreatedTime(authEntity.getCreatedTime());
                    newAuthEntity.setLastUpdatedBy(newUsername);
                    newAuthEntity.setLastUpdatedTime(LocalDateTime.now());
                    newAuthEntity.setId(authEntity.getId());
                    authRepository.save(newAuthEntity);
                    return new UpdateProfileResponse(newAuthEntity.getUserName());
                }else{
                    throw new UserNameAlreadyExistsException();
                }
            } else{
                throw new PasswordNotMatchingException();
            }
        }else{
            throw new UserNotFoundException();
        }
    }

    public UpdatePasswordResponse updateUserPassword(UpdatePasswordCommand command) {
        AuthEntity authEntity = authRepository.getByUserName(command.getUpdatePasswordRequest().getUserName());
        if(authEntity != null){
            assert false;
            if(authEntity.getUserPassword().equals(command.getUpdatePasswordRequest().getUserPassword())){
                AuthEntity newAuthEntity = new AuthEntity();
                BeanUtils.copyProperties(authEntity,newAuthEntity);
                newAuthEntity.setUserPassword(command.getUpdatePasswordRequest().getNewUserPassword());
                authRepository.save(newAuthEntity);
                return new UpdatePasswordResponse(newAuthEntity.getUserName());
            } else{
                throw new PasswordNotMatchingException();
            }
        }else{
            throw new UserNotFoundException();
        }
    }

    public DeleteUserResponse deleteUser(DeleteUserCommand command) {
        AuthEntity authEntity = authRepository.getByUserName(command.getDeleteUserRequest().getUserName());
        if(authEntity != null){
            assert false;
            if(authEntity.getUserPassword().equals(command.getDeleteUserRequest().getUserPassword())){
                authRepository.deleteByUserName(command.getDeleteUserRequest().getUserName());
                return new DeleteUserResponse(authEntity.getUserName());
            } else{
                throw new PasswordNotMatchingException();
            }
        }else{
            throw new UserNotFoundException();
        }
    }

}