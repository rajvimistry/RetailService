package com.target.driver;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.target.model.dto.ProductPrice;

@Repository
public interface ProductDetailsRepository extends CassandraRepository<ProductPrice, Integer>{

}
