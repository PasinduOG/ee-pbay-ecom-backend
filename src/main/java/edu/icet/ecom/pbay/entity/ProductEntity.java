package edu.icet.ecom.pbay.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
}
