package com.target.driver;

import org.springframework.data.cassandra.repository.CassandraRepository;
import com.target.model.dto.ProductPrice;

public interface ProductDetailsRepository extends CassandraRepository<ProductPrice, Integer>{
	
}