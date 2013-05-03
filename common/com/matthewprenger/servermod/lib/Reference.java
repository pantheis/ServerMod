package com.matthewprenger.servermod.lib;

public class Reference {

    public static final String MODID = "ServerMod";
    public static final String MODNAME = Reference.MODID;
    public static final String VERSION = "1.0.0";
    public static final String DEPENDENCIES = "required-after:Forge@[7.8.0.684,)";
    
    public static final String MINECRAFT_VERSION = "1.5.2";
    
    public static final String DEFAULT_MOTD = "Hello, $PLAYER$!\n"+
            "This is a default installation of ServerMod. In order to change\n"+
            "this message, edit servermod/motd.txt or disable it by setting\n" +
            "enable-motd to false in servermod/config/servermod.cfg\n"+
            "Happy playing!";
    
}
