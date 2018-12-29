package com.cyb.web.woremock;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.removeAllMappings;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import ch.qos.logback.core.util.FileUtil;


public class MockServer {

	public static void main(String[] args) throws IOException {
		configureFor(8062);
		removeAllMappings();
		mock("/order/1" , "01");
		mock("/order/2" , "02");
	}

	private static void mock(String string, String name) throws IOException {
		ClassPathResource resource = new ClassPathResource("mock/response/"+name+".txt");
		String content = StringUtils.join(FileUtils.readLines(resource.getFile() , "UTF-8") ,"\n");
		stubFor(get(urlPathEqualTo(string)).willReturn(aResponse().withBody(content).withStatus(200)));
		
	}
}