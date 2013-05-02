package com.matthewprenger.servermod.command;

import com.matthewprenger.servermod.command.CommandKill.EntityDummy;
import com.matthewprenger.servermod.command.CommandKill.ForcedDamageSource;
import com.matthewprenger.servermod.core.ServerMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;

public class CommandHeal extends Command {
	public CommandHeal() {
		super("heal");
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		 EntityPlayer player = null;
		 int amount = Integer.MIN_VALUE;
		 
		 if (var2.length == 0) {
			 player = getCommandSenderAsPlayer(var1);
		 } else if (var2.length == 1) {
			 try {
				 player = getCommandSenderAsPlayer(var1);
				 amount = Integer.parseInt(var2[0]);
			 } catch (NumberFormatException e) {
				 player = ServerMod.server.getConfigurationManager().getPlayerForUsername(var2[0]);
			 }
		 } else if (var2.length > 1) {
			 player = ServerMod.server.getConfigurationManager().getPlayerForUsername(var2[0]);
			 amount = parseInt(var1, var2[1]);
		 }
		 
		 if (player == null) throw new PlayerNotFoundException();
		 if (amount == Integer.MIN_VALUE) amount = player.getMaxHealth() - player.getHealth();
		 
		 player.heal(amount);
		 if (player.getHealth() < 1) {
			 if (var1 == player) player.onDeath(ForcedDamageSource.forced);
			 else player.onDeath(ForcedDamageSource.causeEntityDamage(var1 instanceof Entity ? (Entity)var1 : new EntityDummy(var1.getCommandSenderName())));
		 }
		 
		 notifyAdmins(var1, "Healed "+player.username+" by "+amount);
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/"+name+" [player] [amount]";
	}
}
