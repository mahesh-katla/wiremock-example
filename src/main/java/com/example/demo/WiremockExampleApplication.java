package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

@SpringBootApplication
public class WiremockExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiremockExampleApplication.class, args);
	}
	
	
	 WireMockServer mWireMockServer;

   	@Bean
    public WireMockServer setup() {
        mWireMockServer = new WireMockServer(8081);
        mWireMockServer.start();
        stubTest();
        return mWireMockServer;
    }
    
    public void stubTest() {


    	mWireMockServer.stubFor(WireMock.get("/test")
    			// .withHeader(HttpHeaders.ACCEPT,
                        // WireMock.equalTo(MediaType.APPLICATION_JSON_VALUE))
                         .willReturn(
                                    WireMock.aResponse()
                                            .withStatus(200)
                                            .withHeader(HttpHeaders.CONTENT_TYPE, 
                                 MediaType.APPLICATION_JSON_VALUE)
                                            .withBodyFile("Test.json")
                            )
    			);
    }

}
