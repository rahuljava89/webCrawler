package org.paramati.web.crawler.constants;

public final class CommonConstants {

	// we can move it to configuration files

	public static final String YEAR_MON_HOLDER = "{YEAR_MON}";

	public static final String PAGE_HOLDER = "{PAGE}";

	public static final String ID = "{ID}";

	public final static String MAILS_PER_MONTH_URL = "http://mail-archives.apache.org/mod_mbox/maven-users/"
			+ YEAR_MON_HOLDER + ".mbox/ajax/thread?" + PAGE_HOLDER;

	public final static String MAIL_DATA_URL = "http://mail-archives.apache.org/mod_mbox/maven-users/" + YEAR_MON_HOLDER
			+ ".mbox/ajax/" + ID;

	private CommonConstants() {

	}
}
