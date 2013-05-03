package com.matthewprenger.servermod.command;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandTpAll extends Command {
    public CommandTpAll() {
        super("tpall");
    }

    @Override
    public void processCommand(ICommandSender var1, String[] var2) {

        double x, y, z;
        int dimid;

        EntityPlayerMP sender = (EntityPlayerMP) var1;

        x = sender.posX;
        y = sender.posY;
        z = sender.posZ;
        dimid = sender.dimension;

        ArrayList<?> playerlist = (ArrayList<?>) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
        Iterator<?> iterator = playerlist.iterator();

        int teleported = 0;
        while (iterator.hasNext()) {
            EntityPlayerMP player = (EntityPlayerMP) iterator.next();
            if (player.dimension == dimid) {
                player.setPositionAndUpdate(x, y, z);
                teleported++;
            }
        }
        NumberFormat formatter = new DecimalFormat("#0");

        notifyAdmins(var1, "Teleported all " + teleported + " players to X: "
                + formatter.format(x) + " Y: " + formatter.format(y) + " Z: "
                + formatter.format(z) + " In Dim: " + dimid);
    }

    @Override
    public String getCommandUsage(ICommandSender var1) {
        return "/" + name + " type";
    }
}
