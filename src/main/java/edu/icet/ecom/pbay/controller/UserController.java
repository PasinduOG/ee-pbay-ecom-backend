package edu.icet.ecom.pbay.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.icet.ecom.pbay.dto.UserDto;
import edu.icet.ecom.pbay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class UserController {
    private final UserService service;

    @GetMapping
    ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PostMapping
    ResponseEntity<String> createUser(@RequestBody UserDto userDto){
        return ResponseEntity.status(201).body(service.createUser(userDto));
    }

    @PutMapping("/{id}")
    ResponseEntity<String> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id) {
        return ResponseEntity.ok(service.updateUser(userDto, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteUser(@PathVariable Integer id){
        return ResponseEntity.ok(service.deleteUser(id));
    }
    
}
