package com.matthewprenger.servermod.command;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandTpAll extends Command {
    public CommandTpAll(){
        super("tpall");
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public void processCommand(ICommandSender var1, String[] var2) {
        
        double x, y, z;
                
        x = var1.getPlayerCoordinates().posX;
        y = var1.getPlayerCoordinates().posY;
        z = var1.getPlayerCoordinates().posZ;
        
        /*TODO Add multiple dimention support*/
        
        ArrayList playerlist = (ArrayList) MinecraftServer.getServer().getConfigurationManager().playerEntityList;
        Iterator iterator = playerlist.iterator();
        
        int teleported = 0;
        while(iterator.hasNext()){
            EntityPlayerMP player = (EntityPlayerMP) iterator.next();
            player.setPositionAndUpdate(x, y, z);
            teleported++;
        }
        
        notifyAdmins(var1, "Teleported all " + teleported + " players to X: " + x + " Y: " + y + " Z: " + z);
    }
    
    
    @Override
    public String getCommandUsage(ICommandSender var1) {
        return "/"+name+" type";
    }
}
