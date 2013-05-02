package com.matthewprenger.servermod.command;

import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class ServerModCommands {

    public static void init(FMLServerStartingEvent event){
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
        event.registerServerCommand(new CommandTpAll());
    }
    
}
