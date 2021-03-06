package com.target.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.target.dao.ProductDetailsRepository;
import com.target.excpetion.RecordNotFoundException;
import com.target.excpetion.ValidationException;
import com.target.model.dto.ProductPrice;
import com.target.model.response.Response;
import com.target.myretail.BaseMvcTest;
import com.target.util.FallbackService;
import com.target.util.FeignServiceUtil;

@AutoConfigureWireMock(stubs = "classpath:mappings", port = 0)
class RetailControllerTest extends BaseMvcTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private ProductDetailsRepository productDetailsRepository;
	
	@Mock
	private FeignServiceUtil util;
	
	private Optional<ProductPrice> productPrice = null;
	
	@BeforeEach
	public void setup() {
		ProductPrice newProductPrice = new ProductPrice(12345,10.99,"USD");
		productPrice = Optional.of(newProductPrice);
		when(productDetailsRepository.findById(13860428)).thenReturn(productPrice);
	}

	@Test
	public void getProductDetailsSuccess() throws Exception {

		ResultActions result = mockMvc.perform(
				MockMvcRequestBuilders.get("/products/13860428")
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
	      .andExpect(content().contentType("application/json"))
	      .andExpect(jsonPath("$.id").value("13860428"))
	      .andExpect(jsonPath("$.name").value("The Big Lebowski (Blu-ray)"))
	      .andExpect(jsonPath("$.current_price").isNotEmpty())
	      .andExpect(jsonPath("$.current_price.value").value("10.99"))
	      .andExpect(jsonPath("$.current_price.currency_code").value("USD"));
	}
	
	@Test
	public void updateProductPriceSuccess() throws Exception {
		ResultActions result = mockMvc.perform(
				MockMvcRequestBuilders.put("/products/13860428")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
			    .andExpect(jsonPath("$.id").value("13860428"))
			    .andExpect(jsonPath("$.name").value("The Big Lebowski (Blu-ray)"))
			    .andExpect(jsonPath("$.current_price").isNotEmpty())
			    .andExpect(jsonPath("$.current_price.value").value("15.49"))
			    .andExpect(jsonPath("$.current_price.currency_code").value("CAD"));;
	}
	
	@Test
	public void updateProductPriceBadRequest() throws Exception {
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.put("/products/13860429")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isBadRequest())
				.andReturn();
		
		Optional<ValidationException> e = Optional.ofNullable((ValidationException)result.getResolvedException());
		e.ifPresent( (ex) -> assertThat(ex.getMessage().equals("Invalid Payload")));
	}
	
	@Test
	public void updateProductPriceRecordNotFound() throws Exception {
		when(productDetailsRepository.findById(13860428)).thenReturn(null);
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.put("/products/13860429")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrong_product_id)
				.accept(MediaType.APPLICATION_JSON)
				).andExpect(status().isNotFound())
				.andReturn();
		
		Optional<RecordNotFoundException> e = Optional.ofNullable((RecordNotFoundException)result.getResolvedException());
		e.ifPresent( (ex) -> assertThat(ex.getMessage().equals("Product 54456118 Not Found")));
	}
	
	private String json = "{\n" + 
			"    \"id\": 13860428,\n" + 
			"    \"name\": \"The Big Lebowski (Blu-ray)\",\n" + 
			"    \"current_price\": {\n" + 
			"        \"value\": 15.49,\n" + 
			"        \"currency_code\": \"CAD\"\n" + 
			"    }\n" + 
			"}";
	
	private String wrong_product_id = "{\n" + 
			"    \"id\": 13860429,\n" + 
			"    \"name\": \"The Big Lebowski (Blu-ray)\",\n" + 
			"    \"current_price\": {\n" + 
			"        \"value\": 15.49,\n" + 
			"        \"currency_code\": \"CAD\"\n" + 
			"    }\n" + 
			"}";
}