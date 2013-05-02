package com.matthewprenger.servermod.command;

import java.util.List;
import java.util.Set;

import com.matthewprenger.servermod.core.ServerMod;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommandKillall extends Command {
	public CommandKillall() {
		super("killall");
	}

	@SuppressWarnings("unchecked")
    @Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (var2.length < 1) throw showUsage(var1);
		
		String name = null;
		String pname = func_82360_a(var1, var2, 0);
		for (String ename : (Set<String>)EntityList.stringToClassMapping.keySet()) {
			if (ename.equalsIgnoreCase(pname)) name = ename;
		}
		
		if (name == null) throw new PlayerNotFoundException("That entity type is unknown");
		
		int removed = 0;
		for (World world : ServerMod.server.worldServers) {
			for (Entity ent : (List<Entity>)world.loadedEntityList) {
				String string = EntityList.getEntityString(ent);
				if (string != null && string.equalsIgnoreCase(name) && !(ent instanceof EntityPlayer)) {
					ent.setDead();
					removed++;
				}
			}
		}
		
		notifyAdmins(var1, "Removed "+removed+" entities of type "+name+" ["+((Class<?>)EntityList.stringToClassMapping.get(name)).getName()+"]");
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/"+name+" type";
	}
	
	@SuppressWarnings("unchecked")
    @Override
	public List<?> addTabCompletionOptions(ICommandSender var1, String[] var2) {
        return var2.length >= 1 ? getListOfStringsMatchingLastWord(var2, (String[])EntityList.classToStringMapping.values().toArray(new String[0])) : null;
    }
}
