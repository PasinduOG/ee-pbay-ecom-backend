package edu.icet.ecom.pbay.service;

import java.util.List;

import edu.icet.ecom.pbay.dto.UserDto;

public interface UserService {
    UserDto getUserById(Integer id);
    List<UserDto> getAll();
    String createUser(UserDto userDto);
    String updateUser(UserDto userDto, Integer id);
    String deleteUser(Integer id);
}