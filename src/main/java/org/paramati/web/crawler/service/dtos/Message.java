package org.paramati.web.crawler.service.dtos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Message {

	@JacksonXmlProperty(localName = "id", isAttribute = true)
	private String id;
	
	@JacksonXmlProperty(localName = "linked", isAttribute = true)
	private String linked;
	
	@JacksonXmlProperty(localName = "depth", isAttribute = true)
	private String depth;
	
	@JacksonXmlProperty(localName = "from")
	private String from;
	
	@JacksonXmlProperty(localName = "date")
	private String date;
	
	@JacksonXmlProperty(localName = "subject")
	private String subject;
	
	private String yearMonth;
	
	private byte[] byteArr; 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLinked() {
		return linked;
	}
	public void setLinked(String linked) {
		this.linked = linked;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public byte[] getByteArr() {
		return byteArr;
	}
	public void setByteArr(byte[] byteArr) {
		this.byteArr = byteArr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
