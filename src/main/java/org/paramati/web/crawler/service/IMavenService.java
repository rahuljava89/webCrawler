package org.paramati.web.crawler.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.paramati.web.crawler.service.dtos.Message;

public interface IMavenService {
	
	List<Message> getMailsList(final String month, final String year) throws IOException;


	void saveFile(BlockingQueue<Message> queue, String location, int size) throws IOException, InterruptedException;

}
