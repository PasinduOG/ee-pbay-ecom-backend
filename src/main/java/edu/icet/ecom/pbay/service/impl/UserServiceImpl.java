package edu.icet.ecom.pbay.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.icet.ecom.pbay.dto.UserDto;
import edu.icet.ecom.pbay.exception.NotFoundException;
import edu.icet.ecom.pbay.exception.UnexpectedException;
import edu.icet.ecom.pbay.mapper.UserMapper;
import edu.icet.ecom.pbay.repository.UserRepository;
import edu.icet.ecom.pbay.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDto getUserById(Integer id) {
        return mapper.toDto(repository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found with ID: " + id)));
    }

    @Override
    public List<UserDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public String createUser(UserDto userDto) {
        boolean b = repository.save(mapper.toEntity(userDto));
        if (!b) throw new UnexpectedException("Something went wrong. Please contact technical support");
        return "User created successfully";
    }

    @Override
    public String updateUser(UserDto userDto, Integer id) {
        if (!repository.existsById(id)) throw new NotFoundException("User not found with ID: " + id);
        userDto.setId(id);
        boolean b = repository.update(mapper.toEntity(userDto));
        if (!b) throw new UnexpectedException("Something went wrong. Please contact technical support");
        return "User updated successfully";
    }

    @Override
    public String deleteUser(Integer id) {
        if (!repository.existsById(id)) throw new NotFoundException("User not found with ID: " + id);
        boolean b = repository.delete(id);
        if (!b) throw new UnexpectedException("Something went wrong. Please contact technical support");
        return "User removed successfully";
    }

}
