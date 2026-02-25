package edu.icet.ecom.pbay.service;

import java.util.List;

import edu.icet.ecom.pbay.dto.ProductDto;

public interface ProductService {
    ProductDto getUserById(Integer id);
    List<ProductDto> getAll();
    String createUser(ProductDto userDto);
    String updateUser(ProductDto userDto, Integer id);
    String deleteUser(Integer id);
}
