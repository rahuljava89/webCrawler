package org.paramati.web.crawler.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.io.IOUtils;
import org.paramati.web.crawler.constants.CommonConstants;
import org.paramati.web.crawler.manager.MavenWebCrawlerMgrImpl;
import org.paramati.web.crawler.service.Parser.IParser;
import org.paramati.web.crawler.service.Parser.MavenParser;
import org.paramati.web.crawler.service.dtos.Index;
import org.paramati.web.crawler.service.dtos.Message;
import org.paramati.web.crawler.utils.CrawlerJerseyClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MavenServiceImpl implements IMavenService {

	private static final String ANCHOR_STR = ">";

	private static final String XML = ".xml";

	private final static Logger LOGGER = LoggerFactory.getLogger(MavenWebCrawlerMgrImpl.class);

	private final static IParser mavenParser = new MavenParser();

	@Override
	public List<Message> getMailsList(final String month, final String year) throws IOException {
		LOGGER.debug("getMailsList() method started.");

		final List<Message> mailIDs = new LinkedList<Message>();

		final String yearMon = year + month;

		final String mailURI = CommonConstants.MAILS_PER_MONTH_URL.replace(CommonConstants.YEAR_MON_HOLDER, yearMon);

		final Index index = mavenParser
				.getIndexRes(CrawlerJerseyClient.getResponse(mailURI.replace(CommonConstants.PAGE_HOLDER, "0")));

		final Integer pages = Integer.valueOf(index.getPages());

		addMessagesToList(mailIDs, index, yearMon);

		if (pages > 1) {
			for (int itr2 = 1; itr2 < pages; itr2++) {
				Index index2 = mavenParser.getIndexRes(CrawlerJerseyClient
						.getResponse(mailURI.replace(CommonConstants.PAGE_HOLDER, String.valueOf(itr2))));
				if (null != index.getMessage()) {
					addMessagesToList(mailIDs, index2, yearMon);
				}
			}
		}
		LOGGER.debug("getMailsList() method completed.");
		return mailIDs;
	}

	private void addMessagesToList(final List<Message> mailIDs, final Index index, final String yearMon) {
		Message[] messages = index.getMessage();

		for (int itr3 = 0; itr3 < messages.length; itr3++) {
			messages[itr3].setYearMonth(yearMon);
			mailIDs.add(messages[itr3]);
		}
	}

	@Override
	public void saveFile(final BlockingQueue<Message> queue, final String location, final int size)
			throws IOException, InterruptedException {
		LOGGER.debug("saveFile() method started.");

		int itr = 0;
		while (itr < size) {
			final Message msg = queue.take();
			final String mailID = msg.getId();
			FileOutputStream fos = new FileOutputStream(
					location + mailID.substring(1, mailID.lastIndexOf(ANCHOR_STR)) + XML);
			LOGGER.info("Iteration: " + itr + "mailID ::" + mailID);
			IOUtils.write(msg.getByteArr(), fos);
			fos.close();
			
			itr++;
		}

		LOGGER.debug("saveFile() method completed.");
	}

}
