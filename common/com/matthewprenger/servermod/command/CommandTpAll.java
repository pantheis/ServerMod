package com.matthewprenger.servermod.command;

import net.minecraft.command.ICommandSender;

public class CommandTpAll extends Command {
    public CommandTpAll(){
        super("tpall");
    }
    
    @Override
    public void processCommand(ICommandSender var1, String[] var2) {
        //TODO Add command to teleport all players
    }
    
    
    @Override
    public String getCommandUsage(ICommandSender var1) {
        return "/"+name+" type";
    }
}
