package org.paramati.web.crawler.service.job;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;

import org.paramati.web.crawler.contants.CommonConstants;
import org.paramati.web.crawler.service.dtos.Message;
import org.paramati.web.crawler.utils.CrawlerJerseyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailStreamJob implements Runnable {

	private final static Logger LOGGER = LoggerFactory.getLogger(MailStreamJob.class);

	private Message message;

	private BlockingQueue<Message> queue;

	public MailStreamJob(Message message, BlockingQueue<Message> queue) {
		super();
		this.message = message;
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			saveFile(message);
		} catch (IOException exc) {
			LOGGER.error("saving file to queue got exception :: " + exc.getMessage() + exc);
		}

	}

	public void saveFile(final Message message) throws IOException {
		String mailID = message.getId();
		String url = CommonConstants.MAIL_DATA_URL.replace(CommonConstants.YEAR_MON_HOLDER, message.getYearMonth())
				.replace(CommonConstants.ID, URLEncoder.encode(mailID, StandardCharsets.UTF_8.toString()));
		message.setByteArr(CrawlerJerseyClient.getResponse(url));
		LOGGER.info(Thread.currentThread().getName() + "processed mail id" + mailID);
		queue.add(message);
	}
}
