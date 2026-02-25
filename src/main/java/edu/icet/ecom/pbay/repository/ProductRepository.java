package edu.icet.ecom.pbay.repository;

import java.util.List;
import java.util.Optional;

import edu.icet.ecom.pbay.entity.ProductEntity;

public interface ProductRepository {
    boolean save(ProductEntity productEntity);
    boolean update(ProductEntity productEntity);
    boolean delete(Integer id);
    List<ProductEntity> findAll();
    Optional<ProductEntity> findById(Integer id);
    boolean existsById(Integer id);
}
