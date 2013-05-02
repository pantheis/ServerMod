package com.matthewprenger.servermod.core;

import com.matthewprenger.servermod.api.CallHandler;
import com.matthewprenger.servermod.api.provider.PastebinProvider;


public class ServerModCallHandler extends CallHandler {
	@Override
	public PastebinProvider getPastebin() {
		return ServerMod.instance.pastebin;
	}
}
