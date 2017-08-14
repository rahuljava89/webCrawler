package org.paramati.web.crawler.manager;

import java.io.IOException;

public interface IWebCrawlerManager {
	
	void crawlMailYearWise(final String year, final String location) throws IOException, InterruptedException;

}
