package com.matthewprenger.servermod.provider;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.matthewprenger.servermod.api.provider.PastebinProvider;
import com.matthewprenger.servermod.core.Http;


public class PastebinStikked implements PastebinProvider {
	private final String apiRoot;
	
	public PastebinStikked(String apiRoot) {
		this.apiRoot = apiRoot;
	}
	
	@Override
	public String paste(String title, String text) throws PasteException {
		Map<String, String> postvars = new HashMap<String, String>();
		postvars.put("text", text);
		postvars.put("title", title);
		postvars.put("name", "ServerMod");
		postvars.put("private", "1");
		
		try {
			return Http.post(new URL(apiRoot+"/create"), postvars).text.replaceAll("\n", "");
		} catch (Throwable e) {
			throw new PasteException(e);
		}
	}
}
