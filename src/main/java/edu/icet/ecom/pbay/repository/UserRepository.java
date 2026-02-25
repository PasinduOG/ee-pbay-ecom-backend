package edu.icet.ecom.pbay.repository;

import java.util.List;
import java.util.Optional;

import edu.icet.ecom.pbay.entity.UserEntity;

public interface UserRepository {
    boolean save(UserEntity userEntity);
    boolean update(UserEntity userEntity);
    boolean delete(Integer id);
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Integer id);
    boolean existsById(Integer id);
}
