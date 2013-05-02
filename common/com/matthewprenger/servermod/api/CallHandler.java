package com.matthewprenger.servermod.api;

import com.matthewprenger.servermod.api.provider.PastebinProvider;

public abstract class CallHandler {
	public static CallHandler instance;
	
	public abstract PastebinProvider getPastebin();
}
