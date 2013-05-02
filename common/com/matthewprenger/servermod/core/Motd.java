package com.matthewprenger.servermod.core;

import java.io.File;
import java.io.PrintWriter;
import java.util.logging.Level;

import com.matthewprenger.servermod.ServerMod;
import com.matthewprenger.servermod.command.CommandMotd;
import com.matthewprenger.servermod.lib.Reference;
import com.matthewprenger.servermod.lib.Util;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.registry.GameRegistry;

public class Motd implements IPlayerTracker {
	
	private String motd;
	
	public Motd() {
		GameRegistry.registerPlayerTracker(this);
		
		((ServerCommandManager)ServerMod.server.getCommandManager()).registerCommand(new CommandMotd(this));
		
		try {
			File file = new File("servermod", "motd.txt");
			
			if (!file.exists()) {
				PrintWriter pw = new PrintWriter(file);
				pw.print(Reference.DEFAULT_MOTD);
				pw.close();
			}
			
			motd = Util.readFileToString(file);
		} catch (Throwable e) {
			ServerMod.instance.log.log(Level.WARNING, "Unable to read the MOTD", e);
		}
	}
	
	@Override
	public void onPlayerLogin(EntityPlayer player) {
		serveMotd(player);
	}
	
	@Override
	public void onPlayerLogout(EntityPlayer player) {}
	
	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {}
	
	@Override
	public void onPlayerRespawn(EntityPlayer player) {}
	
	public void serveMotd(ICommandSender sender) {
		for (String line : motd.split("\n")) {
			sender.sendChatToPlayer("\u00a77"+line.replace("\r", "").replace("$PLAYER$", sender.getCommandSenderName()));
		}
	}
}
