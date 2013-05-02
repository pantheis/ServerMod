package com.matthewprenger.servermod.provider;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.matthewprenger.servermod.api.provider.PastebinProvider;
import com.matthewprenger.servermod.core.Http;


public class PastebinCom implements PastebinProvider {	
	@Override
	public String paste(String title, String text) throws PasteException {
		Map<String, String> postvars = new HashMap<String, String>();
		postvars.put("api_option", "paste");
		postvars.put("api_dev_key", "4f409719bd70b09270cc9a9b18d6b947");
		postvars.put("api_paste_code", text);
		postvars.put("api_paste_private", "1");
		postvars.put("api_paste_name", title);
		postvars.put("api_paste_expire_date", "N");
		postvars.put("api_paste_format", "text");
		postvars.put("api_user_key", "");
		
		try {
			return Http.post(new URL("http://pastebin.com/api/api_post.php"), postvars).text.replaceAll("\n", "");
		} catch (Throwable e) {
			throw new PasteException(e);
		}
	}
}
