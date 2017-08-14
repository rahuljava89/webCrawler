package org.paramati.web.crawler.service.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "index")
public class Index {

	@JacksonXmlProperty(localName = "page", isAttribute = true)
	private String page;

	@JacksonXmlProperty(localName = "pages", isAttribute = true)
	private String pages;

	@JacksonXmlElementWrapper(localName = "message", useWrapping = false)
	private Message[] message;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public Message[] getMessage() {
		return message;
	}

	public void setMessage(Message[] message) {
		this.message = message;
	}


}
