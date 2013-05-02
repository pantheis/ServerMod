package com.matthewprenger.servermod.core;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.matthewprenger.servermod.api.CallHandler;
import com.matthewprenger.servermod.api.provider.PastebinProvider;
import com.matthewprenger.servermod.api.provider.Registry;
import com.matthewprenger.servermod.api.provider.PastebinProvider.PasteException;
import com.matthewprenger.servermod.command.*;
import com.matthewprenger.servermod.provider.*;

import net.minecraft.server.MinecraftServer;


import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION)
public class ServerMod {
	
	@Instance("ServerMod")
	public static ServerMod instance;
	public static MinecraftServer server;
	public static boolean firstStart = true;
	
	public Logger log = Logger.getLogger("ServerMod");
	public Settings settings = new Settings(new File(new File("servermod", "config"), "servermod.cfg"), "ServerMod Core configuration file");
	public PastebinProvider pastebin;
	
	@ServerStarting
	public void onServerStarting(FMLServerStartingEvent event) {
		server = event.getServer();
		log.setParent(FMLLog.getLogger());
		
		CallHandler.instance = new ServerModCallHandler();
		
		if (firstStart) {
			Registry.registerPastebinProvider("pastebin", new PastebinCom());
			Registry.registerPastebinProvider("forge", new PastebinStikked("http://paste.minecraftforge.net/api"));
			Registry.registerPastebinProvider("ubuntu", new PastebinUbuntu());
			Registry.registerPastebinProvider("sprunge", new PastebinSprunge());
		}
		
		event.registerServerCommand(new CommandKill());
		event.registerServerCommand(new CommandTps());
		event.registerServerCommand(new CommandSay());
		event.registerServerCommand(new CommandSmite());
		event.registerServerCommand(new CommandInventory());
		event.registerServerCommand(new CommandDisarm());
		event.registerServerCommand(new CommandHeal());
		event.registerServerCommand(new CommandKillall());
		event.registerServerCommand(new CommandSpawnMob());
		event.registerServerCommand(new CommandSpawn());
		
		settings.addSetting("provider-pastebin", "forge", "Pastebin to use as preferred. Pastebins supported by default: pastebin forge ubuntu sprunge");
		settings.addSetting("require-op-tps", false, "Require op for the /tps command");
		settings.addSetting("require-op-kill-self", false, "Require op for using /kill on yourself");
		settings.addSetting("require-op-spawn", false, "Require op for the /spawn command");
		settings.addSetting("enable-motd", true, "Send a message when users log on");
		
		try {
			settings.load();
		} catch (Throwable e) {
			log.log(Level.WARNING, "[ServerMod] Failed to load the configuration file", e);
		}
		try {
			settings.save();
		} catch (Throwable e) {
			log.log(Level.WARNING, "[ServerMod] Failed to save the configuration file", e);
		}
		
		pastebin = Registry.getPastebinProvider(settings.getString("provider-pastebin"));
		if (pastebin == null) {
			pastebin = Registry.getPastebinProvider("forge");
			log.log(Level.WARNING, "Unknown pastebin provider: "+settings.getString("provider-pastebin"));
		}
		
		if (firstStart && settings.getBoolean("enable-motd")) {
			new Motd();
		}
		
		firstStart = false;
	}
	
	public String paste(String title, String text) throws PasteException {
		List<PastebinProvider> pastebins = Registry.getPastebinProviders();
		for (int i = 0; i < pastebins.size(); i++) { // maintain order
			PastebinProvider pastebin = pastebins.get(i);
			try {
				String result = pastebin.paste(title, text);
				if (result == null) throw new PasteException("Null result");
				else return result;
			} catch (Throwable e) {
				continue;
			}
		}
		
		throw new PasteException("None of the registered pastebins could handle the paste request");
	}
}
