package org.javadv.controller;

import org.javadv.domain.User;
import org.javadv.dto.UserRequestDto;
import org.javadv.dto.UserResponseDto;
import org.javadv.impl.exception.UserServiceException;
import org.javadv.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    private final ModelMapper mapper;
    private final UserService userService;

    public UserController(ModelMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return convertToResponseDto(userService.createUser(convertToModel(userRequestDto)));
    }

    @PutMapping("/update")
    public UserResponseDto updateUser(@RequestBody UserRequestDto userRequestDto) throws UserServiceException {
        return convertToResponseDto(userService.updateUser(convertToModel(userRequestDto)));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/get")
    public UserResponseDto getUser(@RequestParam Long userId) throws UserServiceException {
        return convertToResponseDto(userService.getUser(userId));
    }

    @GetMapping("/getAll")
    public List<UserResponseDto> getAllUser() {
        return userService.getAllUsers().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private UserResponseDto convertToResponseDto(User user) {
        return mapper.map(user, UserResponseDto.class);
    }

    private User convertToModel(UserRequestDto userRequestDto) {
        return mapper.map(userRequestDto, User.class);
    }
}
