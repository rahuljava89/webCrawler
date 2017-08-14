package org.paramati.web.crawler.service.Parser;

import java.io.IOException;

import org.paramati.web.crawler.service.dtos.Index;

public interface IParser {
	
	Index getIndexRes(final byte[] byteArr) throws IOException ;

}
