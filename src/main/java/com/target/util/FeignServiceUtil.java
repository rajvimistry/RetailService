package com.target.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.target.model.response.Data;

@FeignClient (value="MyRetailApiFeignClient", url = "${myretail.api.url}")
public interface FeignServiceUtil {
	
	@GetMapping
	Data getProductDetails(@RequestParam(name = "key") String key, @RequestParam(name = "tcin") String tcin);

}
