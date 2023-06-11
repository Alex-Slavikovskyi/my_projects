package app.facade;

import app.dto.rq.UserRequestDTO;
import app.dto.rs.UserResponseDTO;
import app.exceptions.authError.UserAlreadyRegisteredException;
import app.model.UserModel;
import app.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class UserFacade extends GeneralFacade<UserModel, UserRequestDTO, UserResponseDTO> {

  @Autowired
  private UserService userService;

  @PostConstruct
  public void init() {
    super.getMm().typeMap(UserModel.class, UserResponseDTO.class)
      .addMapping(UserModel::getCountFollowers, UserResponseDTO::setCountUserFollowers)
      .addMapping(UserModel::getCountFollowings, UserResponseDTO::setCountUserFollowings)
      .addMapping(UserModel::getCountTweets, UserResponseDTO::setCountUserTweets);
  }

  public UserResponseDTO getUserById(Long userId) {
    return convertToDto(userService.getUser(userId));
  }

  public UserResponseDTO updateUser(Long userId, UserRequestDTO userRequestDTO) {
    userService.getUserByTagO(userRequestDTO.getUserTag())
      .ifPresent(u -> {
        if (!u.getId().equals(userId))
          throw new UserAlreadyRegisteredException("tag: " + userRequestDTO.getUserTag());
      });
    return save(mapToEntity(userRequestDTO, userService.getUser(userId)));
  }

  public Map<String, String> uploadAvatarImg(Long userId, MultipartFile file) {
    return new HashMap<>() {{
      put("avatar_url", userService.uploadAvatarImg(userId, file).getAvatarImgUrl());
    }};
  }

  public Map<String, String> uploadHeaderImg(Long userId, MultipartFile file) {
    return new HashMap<>() {{
      put("header_url", userService.uploadHeaderImg(userId, file).getHeaderImgUrl());
    }};
  }

  public UserResponseDTO subscribe(Long userId, Long userIdToFollowing) {
    return convertToDto(userService.subscribe(userId, userIdToFollowing));
  }

  public UserResponseDTO unsubscribe(Long userId, Long userIdToUnFollowing) {
    return convertToDto(userService.unsubscribe(userId, userIdToUnFollowing));
  }

  public Page<UserResponseDTO> getFollowers(Long userId, int page, int size) {
    return userService.getFollowers(userId, page, size).map(this::convertToDto);
  }

  public Page<UserResponseDTO> getFollowings(Long userId, int page, int size) {
    return userService.getFollowings(userId, page, size).map(this::convertToDto);
  }

  public Page<UserResponseDTO> getOfferFollowings(Long userId, int page, int size) {
    return userService.getOfferFollowings(userId, page, size).map(this::convertToDto);
  }

  public Page<UserResponseDTO> findUser(Long userId, String searchString, int page, int size) {
    return userService.searchUsers(userId, searchString, page, size).map(this::convertToDto);
  }
}