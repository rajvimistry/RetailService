package com.target.myretail;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.target.driver.MyRetailApplication;

@Tag("integration")
@ActiveProfiles("test")
@ContextConfiguration(classes = { MyRetailApplication.class })
@WebMvcTest
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@PropertySource("classpath:application-test.properties")
public class BaseMvcTest extends BaseUnitTest{
 
}
