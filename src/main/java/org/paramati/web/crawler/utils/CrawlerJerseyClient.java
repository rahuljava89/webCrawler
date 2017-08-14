package org.paramati.web.crawler.utils;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CrawlerJerseyClient {
	
	private static Client client = Client.create();
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CrawlerJerseyClient.class);

	private CrawlerJerseyClient() {

	}
	
	

	public static byte[] getResponse(String resource) throws IOException {
		LOGGER.info("URL has been executed :: " + resource);
		WebResource webResource = client.resource(resource);

		ClientResponse response = (ClientResponse) webResource.get(ClientResponse.class);

		return IOUtils.toByteArray(response.getEntityInputStream());

	}

}
