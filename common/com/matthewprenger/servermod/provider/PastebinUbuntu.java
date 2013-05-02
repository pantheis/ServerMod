package com.matthewprenger.servermod.provider;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.matthewprenger.servermod.api.provider.PastebinProvider;
import com.matthewprenger.servermod.core.Http;


public class PastebinUbuntu implements PastebinProvider {
	@Override
	public String paste(String title, String text) throws PasteException {
		Map<String, String> postvars = new HashMap<String, String>();
		postvars.put("syntax", "text");
		postvars.put("poster", "ServerMod");
		postvars.put("content", text);
		
		try {
			return Http.post(new URL("http://paste.ubuntu.com"), postvars).headers.get("Location");
		} catch (Throwable e) {
			throw new PasteException(e);
		}
	}
}
