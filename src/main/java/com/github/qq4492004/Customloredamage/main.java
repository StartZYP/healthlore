package com.github.qq4492004.Customloredamage;

import Super_Life.Super_Life;
import com.github.qq4492004.Customloredamage.Commands.hl;
import com.github.qq4492004.Customloredamage.Entity.ConfigAttribute;
import com.github.qq4492004.Customloredamage.Entity.PlayerAttribute;
import com.github.qq4492004.Customloredamage.Listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class main extends JavaPlugin implements Listener {
    public static Map<String, PlayerAttribute> playersatt = new HashMap<>();
    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin =this;
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()){
            saveDefaultConfig();
        }
        ConfigReload();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(),this);
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        Bukkit.getServer().getPluginCommand("hl").setExecutor(new hl());
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player:Bukkit.getOnlinePlayers()){
                    PlayerAttribute playerAttribute = main.playersatt.get(player.getName());
                    double lifeSpawn = playerAttribute.getLifeSpawn();
                    if (player.getHealth() <= playerAttribute.getHealth()){
                        player.setHealth(player.getHealth()+lifeSpawn);
                    }
                    player.setHealth(player.getHealth());
                }
            }
        }.runTaskTimer(this,20L,10L*10);
        super.onEnable();
    }

    public static FileConfiguration load(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var3) {
                var3.printStackTrace();
            }
        }

        return YamlConfiguration.loadConfiguration(file);
    }



    private void ConfigReload(){
        reloadConfig();
        ConfigAttribute.Damage = getConfig().getString("state.damage");
        ConfigAttribute.Defense = getConfig().getString("state.denfense");
        ConfigAttribute.CritRate = getConfig().getString("state.CritRate");
        ConfigAttribute.CritDouble = getConfig().getString("state.CritDouble");
        ConfigAttribute.Health = getConfig().getString("state.health");
        ConfigAttribute.SuckhealthDouble = getConfig().getString("state.SuckhealthDouble");
        ConfigAttribute.SuckhealthRate = getConfig().getString("state.SuckhealthRate");
        ConfigAttribute.Speed = getConfig().getString("state.Speed");
        ConfigAttribute.Critfense = getConfig().getString("state.Critfense");
        ConfigAttribute.LifeSpawn = getConfig().getString("state.lifespawn");
        ConfigAttribute.Damagerebound = getConfig().getString("state.Damagerebound");
        ConfigAttribute.split = getConfig().getString("split");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("SuperLife")) {
            if (!sender.hasPermission("SuperLife.Admin")) {
                return true;
            }

            if (args.length == 0) {
                sender.sendMessage("§a-----生命果插件-----");
                sender.sendMessage("§a/SuperLife HealInfo [玩家]    §6查看玩家血量信息");
                sender.sendMessage("§a/SuperLife AddHeal [玩家] [血量]    §6增加玩家血量");
                sender.sendMessage("§a/SuperLife RemoveHeal [玩家] [血量]    §6减少玩家血量");
                sender.sendMessage("§a/SuperLife SetHeal [玩家] [血量]    §6设置玩家血量");
                sender.sendMessage("§a/SuperLife Give [数量]    §6给自己生命果");
                sender.sendMessage("§a/SuperLife Reload    §6重新加载插件");
                return true;
            }

            if (args[0].equalsIgnoreCase("SetHeal")) {
                if (args.length == 3) {
                    double Y2 = Double.valueOf(args[2]);
                    if (main.plugin.getServer().getPlayer(args[1]) != null) {
                        main.plugin.getServer().getPlayer(args[1]).setMaxHealth(Y2);
                    }

                    sender.sendMessage("§a玩家" + args[1] + "的血量上限设置为" + Y2);
                } else {
                    sender.sendMessage("§a/SuperLife SetHeal [玩家] [血量]    §6设置玩家血量");
                }

                return true;
            }

            File file1;
            FileConfiguration config1;
            double Y;
            double Y2;
            if (args[0].equalsIgnoreCase("AddHeal")) {
                if (args.length == 3) {
                    file1 = new File(main.plugin.getDataFolder(), "players.yml");
                    config1 = load(file1);
                    Y = 0.0D;
                    if (config1.contains("Players.List." + args[1] + ".MaxHealth")) {
                        Y = config1.getDouble("Players.List." + args[1] + ".MaxHealth");
                    } else {
                        Y = 20.0D;
                    }

                    Y2 = Double.valueOf(args[2]);
                    config1.set("Players.List." + args[1] + ".MaxHealth", Y + Y2);
                    config1.set("Players.List." + args[1] + ".Health", Y + Y2);

                    try {
                        config1.save(file1);
                    } catch (IOException var12) {
                        var12.printStackTrace();
                    }

                    if (main.plugin.getServer().getPlayer(args[1]) != null) {
                        main.plugin.getServer().getPlayer(args[1]).setMaxHealth(Y + Y2);
                    }

                    sender.sendMessage("§a玩家" + args[1] + "的血量上限从" + Y + "提升到" + (Y + Y2));
                } else {
                    sender.sendMessage("§a/SuperLife AddHeal [玩家] [血量]    §6增加玩家血量");
                }

                return true;
            }

            if (args[0].equalsIgnoreCase("RemoveHeal")) {
                if (args.length == 3) {
                    file1 = new File(main.plugin.getDataFolder(), "players.yml");
                    config1 = load(file1);
                    Y = 0.0D;
                    if (config1.contains("Players.List." + args[1] + ".MaxHealth")) {
                        Y = config1.getDouble("Players.List." + args[1] + ".MaxHealth");
                    } else {
                        Y = 20.0D;
                    }

                    Y2 = Double.valueOf(args[2]);
                    config1.set("Players.List." + args[1] + ".MaxHealth", Y - Y2);
                    config1.set("Players.List." + args[1] + ".Health", Y - Y2);

                    try {
                        config1.save(file1);
                    } catch (IOException var13) {
                        var13.printStackTrace();
                    }

                    if (main.plugin.getServer().getPlayer(args[1]) != null) {
                        main.plugin.getServer().getPlayer(args[1]).setMaxHealth(Y - Y2);
                    }

                    sender.sendMessage("§a玩家" + args[1] + "的血量上限从" + Y + "降低到" + (Y - Y2));
                } else {
                    sender.sendMessage("§a/SuperLife RemoveHeal [玩家] [血量]    §6减少玩家血量");
                }

                return true;
            }

            Player p;
            if (args[0].equalsIgnoreCase("HealInfo")) {
                if (args.length == 2) {
                    if (main.plugin.getServer().getPlayer(args[1]) == null) {
                        file1 = new File(main.plugin.getDataFolder(), "players.yml");
                        config1 = load(file1);
                        if (config1.contains("Players.List." + args[1])) {
                            sender.sendMessage("§a---------");
                            sender.sendMessage("§a玩家:" + args[1]);
                            sender.sendMessage("§a血量上限:" + config1.getDouble("Players.List." + args[1] + ".MaxHealth"));
                            sender.sendMessage("§a当前血量:" + config1.getDouble("Players.List." + args[1] + ".Health"));
                        } else {
                            sender.sendMessage("§a[§b生命果§a] §6找不到玩家" + args[1] + "的血量信息");
                        }
                    } else {
                        p = main.plugin.getServer().getPlayer(args[1]);
                        sender.sendMessage("§a---------");
                        sender.sendMessage("§a玩家:" + args[1]);
                        sender.sendMessage("§a血量上限:" + p.getMaxHealth());
                        sender.sendMessage("§a当前血量:" + p.getHealth());
                    }
                } else {
                    sender.sendMessage("§a/SuperLife HealInfo [玩家]    §6查看玩家血量信息");
                }

                return true;
            }

            if (args[0].equalsIgnoreCase("Give")) {
                if (sender instanceof Player) {
                    p = (Player)sender;
                    ItemStack Item = new ItemStack(322, 1, (short)0);
                    if (args.length == 2) {
                        Item.setAmount(Integer.parseInt(args[1]));
                    }

                    PlayerInventory I = p.getInventory();
                    ItemMeta meta = Item.getItemMeta();
                    meta.setDisplayName("§a§l生命果");
                    ArrayList<String> lore = new ArrayList();
                    lore.add("§b§l生命果§6§l-§a§l吃下去可以增强生命");
                    meta.setLore(lore);
                    Item.setItemMeta(meta);
                    I.addItem(new ItemStack[]{Item});
                } else {
                    sender.sendMessage("[生命果] 该命令不能由控制台执行");
                }

                return true;
            }

            if (args[0].equalsIgnoreCase("Reload")) {
                if (args.length == 1) {
                    Super_Life.LoadConfig();
                    sender.sendMessage("§b[§a生命果§b] 插件已重新加载");
                }

                return true;
            }
        }

        return false;
    }

//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (args.length==1&&sender.isOp()){
//            if (args[0].equalsIgnoreCase("reload")){
//                ConfigReload();
//                sender.sendMessage("重载ok");
//            }else if (args[0].equalsIgnoreCase("info")){
//                sender.sendMessage(playersatt.get(sender.getName()).toString());
//            }
//        }
//        return super.onCommand(sender, command, label, args);
//    }
}
