package org.paramati.web.crawler;

import java.io.IOException;

import org.paramati.web.crawler.manager.IWebCrawlerManager;
import org.paramati.web.crawler.manager.MavenWebCrawlerMgrImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerServiceMain {

	private static final String FILE_LOCATION = "D:\\rahul\\";
	
	private static final String YEAR = "2015";
	
	final static Logger LOGGER = LoggerFactory.getLogger(CrawlerServiceMain.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LOGGER.info("Crawler Application started");

		IWebCrawlerManager mavenWebCrawler = new MavenWebCrawlerMgrImpl();

		try {
			
			mavenWebCrawler.crawlMailYearWise(YEAR, FILE_LOCATION);
			
		} catch (IOException | InterruptedException exc) {
			LOGGER.error("Application failed due to exception : " + exc.getMessage(), exc);
		}

		LOGGER.info("Crawler Application completed");
	}

}
