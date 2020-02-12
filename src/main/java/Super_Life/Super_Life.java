//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Super_Life;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.github.qq4492004.Customloredamage.Entity.PlayerAttribute;
import com.github.qq4492004.Customloredamage.UtilAtt;
import com.github.qq4492004.Customloredamage.main;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Super_Life{
    public static int RewardChance;
    public static int RewardMinHealth;
    public static int RewardMaxHealth;
    public static boolean PunishmentDemotion;
    public static int PunishmentChance;
    public static int PunishmentMinHealth;
    public static int PunishmentMaxHealth;
    public static boolean EatEffectEnable;
    public static int EatEffectType;
    public static boolean EatMessageEnable;
    public static String EatMessageString;
    public static int PlayerMaxHealth;
    public static boolean NearEnable;
    public static int NearX;
    public static int NearY;
    public static int NearZ;

    public Super_Life() {
    }

    public void onEnable() {
        main.plugin.getLogger().info("[SuperLife] 插件已加载");
        if (!main.plugin.getDataFolder().exists()) {
            File file1 = new File(main.plugin.getDataFolder(), "config.yml");
            main.plugin.getDataFolder().mkdir();
            new File(main.plugin.getDataFolder(), "players.yml");
            main.plugin.getDataFolder().mkdir();
            FileConfiguration config1 = load(file1);
            config1.set("Config.EatAscension.Reward.Chance", 100);
            config1.set("Config.EatAscension.Reward.MinHealth", 1);
            config1.set("Config.EatAscension.Reward.MaxHealth", 1);
            config1.set("Config.EatAscension.Punishment.Demotion", false);
            config1.set("Config.EatAscension.Punishment.Chance", 100);
            config1.set("Config.EatAscension.Punishment.MinHealth", 1);
            config1.set("Config.EatAscension.Punishment.MaxHealth", 1);
            config1.set("Config.Eat.PlayerMaxHealth", 40);
            config1.set("Config.Eat.Effect.Enable", true);
            config1.set("Config.Eat.Effect.Type", 1);
            config1.set("Config.Eat.Message.Enable", true);
            config1.set("Config.Eat.Message.Message", "&b[&a生命果&b] 玩家<player>吃掉了一个生命果,生命获得提升");
            config1.set("Config.RestoreNearMobs.Enable", true);
            config1.set("Config.RestoreNearMobs.NearX", 10);
            config1.set("Config.RestoreNearMobs.NearY", 10);
            config1.set("Config.RestoreNearMobs.NearZ", 10);

            try {
                config1.save(file1);
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }
        LoadConfig();
    }

    public void SaveAllPlayer(String P) {
        File file1 = new File(main.plugin.getDataFolder(), "players.yml");
        main.plugin.getDataFolder().mkdir();
        FileConfiguration config1 = load(file1);
        Player p = main.plugin.getServer().getPlayerExact(P);
        config1.set("Players.List." + P + ".MaxHealth", p.getMaxHealth());
        config1.set("Players.List." + P + ".Health", p.getHealth());

        try {
            config1.save(file1);
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        try {
            config1.save(file1);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void LoadAllPlayres() {
        File file1 = new File(main.plugin.getDataFolder(), "players.yml");
        FileConfiguration config1 = load(file1);

        String var3;
        for(Iterator var4 = config1.getConfigurationSection("Players.List").getKeys(false).iterator(); var4.hasNext(); var3 = (String)var4.next()) {
        }

    }

    public static int MathRandom(int x, int y) {
        Random random = new Random();
        int s = random.nextInt(y) % (y - x + 1) + x;
        return s;
    }

    public static void LoadConfig() {
        File file1 = new File(main.plugin.getDataFolder(), "config.yml");
        FileConfiguration config1 = main.load(file1);
        RewardChance = config1.getInt("Config.EatAscension.Reward.Chance");
        RewardMinHealth = config1.getInt("Config.EatAscension.Reward.MinHealth");
        RewardMaxHealth = config1.getInt("Config.EatAscension.Reward.MaxHealth");
        PunishmentDemotion = config1.getBoolean("Config.EatAscension.Punishment.Demotion");
        PunishmentChance = config1.getInt("Config.EatAscension.Punishment.Chance");
        PunishmentMinHealth = config1.getInt("Config.EatAscension.Punishment.MinHealth");
        PunishmentMaxHealth = config1.getInt("Config.EatAscension.Punishment.MaxHealth");
        EatEffectEnable = config1.getBoolean("Config.Eat.Effect.Enable");
        EatEffectType = config1.getInt("Config.Eat.Effect.Type");
        EatMessageEnable = config1.getBoolean("Config.Eat.Message.Enable");
        EatMessageString = config1.getString("Config.Eat.Message.Message");
        PlayerMaxHealth = config1.getInt("Config.Eat.PlayerMaxHealth");
        NearEnable = config1.getBoolean("Config.RestoreNearMobs.Enable");
        NearX = config1.getInt("Config.RestoreNearMobs.NearX");
        NearY = config1.getInt("Config.RestoreNearMobs.NearY");
        NearZ = config1.getInt("Config.RestoreNearMobs.NearZ");
    }

    public static void EatEffect(Player p) {
        if (EatEffectType == 1) {
            p.getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
        } else if (EatEffectType == 2) {
            p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, 1);
        } else if (EatEffectType == 2) {
            p.getWorld().playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1);
        } else if (EatEffectType == 2) {
            p.getWorld().playEffect(p.getLocation(), Effect.POTION_BREAK, 1);
        }

    }


    public FileConfiguration load(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

        return YamlConfiguration.loadConfiguration(file);
    }

    public static void NearHeal(Player p) {
        List<Entity> Es = p.getNearbyEntities((double)NearX, (double)NearY, (double)NearZ);
        Iterator var4 = Es.iterator();

        while(var4.hasNext()) {
            Entity E = (Entity)var4.next();
            if (E.getType() == EntityType.PIG) {
                Pig e = (Pig)E;
                e.setHealth(e.getMaxHealth());
            } else if (E.getType() == EntityType.SHEEP) {
                Sheep e = (Sheep)E;
                e.setHealth(e.getMaxHealth());
            } else if (E.getType() == EntityType.COW) {
                Cow e = (Cow)E;
                e.setHealth(e.getMaxHealth());
            } else if (E.getType() == EntityType.CHICKEN) {
                Chicken e = (Chicken)E;
                e.setHealth(e.getMaxHealth());
            } else if (E.getType() == EntityType.PLAYER) {
                Player p2 = (Player)E;
                p2.setHealth(p2.getMaxHealth());
            }
        }

    }

}
