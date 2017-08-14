package org.paramati.web.crawler.service.Parser;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.paramati.web.crawler.service.dtos.Index;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class MavenParser implements IParser{

	@Override
	public Index getIndexRes(final byte[] byteArr) throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
        Index index = objectMapper.readValue(
                StringUtils.toEncodedString(byteArr, StandardCharsets.UTF_8),
                Index.class);
        return index;
    }
}
