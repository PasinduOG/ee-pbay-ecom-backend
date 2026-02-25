package edu.icet.ecom.pbay.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.icet.ecom.pbay.entity.ProductEntity;
import edu.icet.ecom.pbay.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcTemplate template;

    @Override
    public boolean save(ProductEntity productEntity) {
        String sql = "INSERT INTO products (name, description, price, stock_quantity, category) VALUES (?,?,?,?,?)";
        return template.update(sql,
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getStock(),
                productEntity.getCategory()) > 0;
    }

    @Override
    public boolean update(ProductEntity productEntity) {
        String sql = "UPDATE products SET name=?, description=?, price=?, stock_quantity=?, category=? WHERE id=?";
        return template.update(sql,
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getStock(),
                productEntity.getCategory(),
                productEntity.getId()) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM products WHERE id=?";
        return template.update(sql, id) > 0;
    }

    @Override
    public List<ProductEntity> findAll() {
        String sql = "SELECT name, description, price, stock_quantity, category";
        return template.query(sql, (rs, rowNum) -> {
            ProductEntity entity = new ProductEntity();
            entity.setId(rs.getInt("id"));
            entity.setName(rs.getString("name"));
            entity.setDescription(rs.getString("description"));
            entity.setPrice(rs.getDouble("price"));
            entity.setStock(rs.getInt("stock_count"));
            entity.setCategory(rs.getString("category"));
            return entity;
        });
    }

    @Override
    public Optional<ProductEntity> findById(Integer id) {
        String sql = "SELECT name, description, price, stock_quantity, category WHERE id=?";
        try {
            return Optional.ofNullable(template.queryForObject(sql, (rs, rowNum) -> {
                ProductEntity entity = new ProductEntity();
                entity.setId(rs.getInt("id"));
                entity.setName(rs.getString("name"));
                entity.setDescription(rs.getString("description"));
                entity.setPrice(rs.getDouble("price"));
                entity.setStock(rs.getInt("stock_count"));
                entity.setCategory(rs.getString("category"));
                return entity;
            }, id));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isEmpty();
    }

}
