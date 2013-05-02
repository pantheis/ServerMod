package com.matthewprenger.servermod.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;

import com.matthewprenger.servermod.ServerMod;

public class CommandSpawn extends Command {
	public CommandSpawn() {
		super("spawn");
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		if (!(var1 instanceof EntityPlayer)) throw new PlayerNotFoundException("This command must be used by a player");
				
		EntityPlayer player = (EntityPlayer)var1;
		ChunkCoordinates point = player.worldObj.getSpawnPoint();
		int dimid = player.dimension;
		
		while(ServerMod.server.worldServerForDimension(dimid).getBlockId(point.posX, point.posY, point.posZ) != 0){
		    point.posY ++;
		}
		
		player.setPositionAndUpdate(point.posX, point.posY, point.posZ);

		var1.sendChatToPlayer("Teleported to the spawn point of X:" + point.posX + " Y:" + point.posY + " Z:" + point.posZ);
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/"+name;
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return ServerMod.instance.settings.getBoolean("require-op-spawn") ? 4 : 0;
	}
}
