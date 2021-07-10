package me.reed.reedsplugin.events;

import me.reed.reedsplugin.Main;
import me.reed.reedsplugin.commands.DoubleExp;
import me.reed.reedsplugin.commands.ExpTracking;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class ExpMultiplier implements Listener {

    private Main plugin;
    public ExpMultiplier(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onExperienceDrop(EntityTargetEvent event) {
        Entity e = event.getEntity();

        if (e.getType() == EntityType.EXPERIENCE_ORB && event.getTarget() instanceof Player) {
            ExperienceOrb exp = (ExperienceOrb) e;
            // Exp doubling controller
            if (DoubleExp.getDoubleExpStatus() && !exp.hasMetadata("XPDoubled")) {
                exp.setExperience(exp.getExperience() * 2);
                exp.setMetadata("XPDoubled", new FixedMetadataValue(plugin, true));
            }
            // Exp pickup tracking controller
            if (ExpTracking.getExpTrackingStatus()) {
                ExpTracking.getSender().sendMessage(ChatColor.AQUA + "Experience of Target Orb: " + ChatColor.GREEN + exp.getExperience());
            }
        }

    }
}
