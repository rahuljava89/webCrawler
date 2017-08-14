package org.paramati.web.crawler.manager;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.paramati.web.crawler.CrawlerServiceMain;
import org.paramati.web.crawler.service.IMavenService;
import org.paramati.web.crawler.service.MavenServiceImpl;
import org.paramati.web.crawler.service.dtos.Message;
import org.paramati.web.crawler.service.job.MailStreamJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MavenWebCrawlerMgrImpl implements IWebCrawlerManager {

	final static Logger LOGGER = LoggerFactory.getLogger(MavenWebCrawlerMgrImpl.class);

	private IMavenService mavenService = new MavenServiceImpl();

	@Override
	public void crawlMailYearWise(final String year, final String location) throws IOException, InterruptedException {

		final Set<Message> messages = new HashSet<Message>();

		final BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();

		getAllMessages(year, messages);

		LOGGER.info("Total mail for given year is :: " + messages.size());

		final ExecutorService executor = Executors.newFixedThreadPool(10);

		for (Message message : messages) {
			executor.submit(new MailStreamJob(message, queue));
		}

		LOGGER.info("All tasks has been submitted.");

		mavenService.saveFile(queue, location, messages.size());

	}

	private void getAllMessages(final String year, final Set<Message> messages) throws IOException {
		for (int itr = 1; itr < 13; itr++) {
			messages.addAll(mavenService.getMailsList(String.format("%02d", itr), year));
		}
	}

}
