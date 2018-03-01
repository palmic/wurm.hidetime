package org.palmic.wurmunlimited.mods.hidetime;

import com.wurmonline.server.WurmCalendar;
import com.wurmonline.server.creatures.Communicator;

import org.gotti.wurmunlimited.modloader.interfaces.*;

import java.util.Properties;

public class HideTime implements WurmServerMod, Configurable, PlayerMessageListener {

    private boolean isEnabled = true;

    @Override
    public void configure(Properties properties) {
        // Check .properties file for configuration values
        isEnabled = Boolean.parseBoolean(properties.getProperty("isEnabled", Boolean.toString(isEnabled)));
    }

    @Override
    public boolean onPlayerMessage(Communicator communicator, String message) {
        if (isEnabled) {
            if (message.startsWith("/time")) {
                communicator.player.getCommunicator().sendNormalServerMessage(WurmCalendar.getDateFor(WurmCalendar.getCurrentTime()));
                return true;
            }
            return false;
        }
        return false;
    }
}
