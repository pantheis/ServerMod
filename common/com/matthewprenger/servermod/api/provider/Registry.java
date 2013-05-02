package com.matthewprenger.servermod.api.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.matthewprenger.servermod.api.CallHandler;


public class Registry {
	private static final Map<String, PastebinProvider> pastebinProviders = new HashMap<String, PastebinProvider>();

	public static void registerPastebinProvider(String id, PastebinProvider provider) {
		if (pastebinProviders.containsKey(id)) throw new IllegalArgumentException("Pastebin provider "+id+" already registered by "+pastebinProviders.get(id)+" when registering "+provider);
		
		pastebinProviders.put(id, provider);
	}
	
	public static PastebinProvider getPastebinProvider(String id) {
		return pastebinProviders.get(id);
	}
	
	public static List<PastebinProvider> getPastebinProviders() {
		List<PastebinProvider> providers = new ArrayList<PastebinProvider>(pastebinProviders.size());
		providers.add(CallHandler.instance.getPastebin()); // priority over the preferred one
		providers.addAll(pastebinProviders.values());
		
		return providers;
	}
}
