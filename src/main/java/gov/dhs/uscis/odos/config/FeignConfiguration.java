package gov.dhs.uscis.odos.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "gov.dhs.uscis.odos")
public class FeignConfiguration {

}
