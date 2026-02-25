package edu.icet.ecom.pbay.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.icet.ecom.pbay.entity.UserEntity;
import edu.icet.ecom.pbay.repository.UserRepository;
import edu.icet.ecom.pbay.util.Role;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate template;

    @Override
    public boolean save(UserEntity userEntity) {
        String sql = "INSERT INTO users (first_name, last_name, email, pass, role) VALUES (?,?,?,?,?)";
        return template.update(sql,
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRole().toString()) > 0;
    }

    @Override
    public boolean update(UserEntity userEntity) {
        String sql = "UPDATE users SET first_name=?, last_name=?, email=?, pass=?, role=? WHERE id=?";
        return template.update(sql,
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getRole().toString(),
                userEntity.getId()) > 0;
    }

    @Override
    public List<UserEntity> findAll() {
        String sql = "SELECT id, first_name, last_name, email, pass, role FROM users";
        return template.query(sql, (rs, rowNum) -> {
            UserEntity entity = new UserEntity();
            entity.setId(rs.getInt("id"));
            entity.setFirstName(rs.getString("first_name"));
            entity.setLastName(rs.getString("last_name"));
            entity.setEmail(rs.getString("email"));
            entity.setPassword(rs.getString("pass"));
            entity.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
            return entity;
        });
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        String sql = "SELECT id, first_name, last_name, email, pass, role FROM users WHERE id=?";
        try {
            UserEntity entity = template.queryForObject(sql, (rs, rowNum) -> {
                UserEntity e = new UserEntity();
                e.setId(rs.getInt("id"));
                e.setFirstName(rs.getString("first_name"));
                e.setLastName(rs.getString("last_name"));
                e.setEmail(rs.getString("email"));
                e.setPassword(rs.getString("pass"));
                e.setRole(Role.valueOf(rs.getString("role").toUpperCase()));
                return e;
            }, id);
            return Optional.ofNullable(entity);
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isEmpty();
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM users WHERE id=?";
        return template.update(sql, id) > 0;
    }

}
