package veinminer;
import necesse.engine.input.Control;
//import necesse.engine.localization.message.LocalMessage;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.PacketRegistry;
import veinminer.objects.Config;
import veinminer.packets.PacketObjectsDestroyed;
import veinminer.utils.ConfigParser;

import java.awt.event.KeyEvent;
import java.io.IOException;
//import java.lang.reflect.Array;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Method;
import java.util.HashSet;

import static veinminer.utils.ModMisc.getModVersion;

@ModEntry
public class AnotherVeinMiner {

    public static Control SPEED_MINE;
    public static Config modConfig;
    public static boolean configReadAttempted = false;
    public static HashSet<String> oreIDs;
    public static int radius;

    public AnotherVeinMiner() {
        readConfigFile();
        configureMiningControl();
    }

    public static void readConfigFile() {
        ConfigParser configReader = new ConfigParser();
        configReadAttempted = true;
        try {
            modConfig = configReader.parseConfig();
            oreIDs = modConfig.getOreIDs();
            radius = modConfig.get_radius();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void configureMiningControl() {
        try {
            //get the mining key to use
            int miningCharKeyCode = KeyEvent.getExtendedKeyCodeForChar(modConfig.get_mining_key());

            SPEED_MINE = Control.addModControl(new Control(miningCharKeyCode, "speedmine"));
        } catch (Exception e) {
            System.out.println("Could not create fast mine key bind!");
        }
    }

    public void init() {
        PacketRegistry.registerPacket(PacketObjectsDestroyed.class);
        new AnotherVeinMiner();
        System.out.printf("AnotherVeinMiner - Fixed, (version %s) by Trihardest, fixed by Venelder: Loaded!\n", getModVersion());
    }

}
