package com.target.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.target.model.response.Data;
import com.target.model.response.Response;
import com.target.service.retailservice.RetailServiceImpl;

@FeignClient (name="redsky", url = "${myretail.api.url}", fallbackFactory = FallbackService.class)
public interface FeignServiceUtil {
	
	@GetMapping
	ResponseEntity<Response> getProductDetails(@RequestParam(name = "key") String key, @RequestParam(name = "tcin") int tcin);
}





