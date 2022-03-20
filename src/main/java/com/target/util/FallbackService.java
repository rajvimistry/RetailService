package com.target.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.target.model.response.Response;

import feign.FeignException;

@Component
public class FallbackService implements FallbackFactory<FeignServiceUtil>{

	Logger log = LogManager.getLogger(FallbackService.class);
	
	
	@Override
	public FeignServiceUtil create(Throwable cause) {
		log.info("FallbackService Method..." + cause);
		
		String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : "";
	
		if(httpStatus == null || httpStatus.isEmpty()) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.toString();
		}
		
		log.error("error: "+httpStatus);
		if(HttpStatus.NOT_FOUND.toString().contains(httpStatus)) {
			return new FeignServiceUtil() {

				@Override
				public ResponseEntity<Response> getProductDetails(String key, int tcin) {
					return new ResponseEntity<Response>(HttpStatus.NOT_FOUND);
				}
			};
		}
		return new FeignServiceUtil() {

			@Override
			public ResponseEntity<Response> getProductDetails(String key, int tcin) {
				return new ResponseEntity<Response>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		};
	}

}
