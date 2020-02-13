package com.github.qq4492004.Customloredamage.Commands;

import com.github.qq4492004.Customloredamage.Entity.PlayerAttribute;
import com.github.qq4492004.Customloredamage.main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class hl implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§a-----属性插件-----");
            sender.sendMessage("§a/hl info [玩家]    §6查看玩家属性信息");
        }else if (args.length==2 && args[0].equalsIgnoreCase("info")){
            OfflinePlayer offlinePlayer = Bukkit.getServer().getOfflinePlayer(args[1]);
            if (offlinePlayer.isOnline()&&main.playersatt.containsKey(args[1])){
                PlayerAttribute playerAttribute = main.playersatt.get(args[1]);
                sender.sendMessage("§a-----玩家属性属性-----");
                sender.sendMessage("§8血量:"+playerAttribute.getHealth());
                sender.sendMessage("§8攻击:"+playerAttribute.getMinDamage()+"-"+playerAttribute.getMaxDamage());
                sender.sendMessage("§8护甲值:"+playerAttribute.getDefense());
                sender.sendMessage("§8暴击几率:"+playerAttribute.getCritRate()+"%");
                sender.sendMessage("§8暴击倍率:"+playerAttribute.getCritDouble());
                sender.sendMessage("§8暴击抵抗:"+playerAttribute.getCritfense()+"%");
                sender.sendMessage("§8吸血几率:"+playerAttribute.getSuckhealthRate()+"%");
                sender.sendMessage("§8吸血倍率:"+playerAttribute.getSuckhealthDouble());
                sender.sendMessage("§8回血:"+playerAttribute.getLifeSpawn());
                sender.sendMessage("§8伤害反弹:"+playerAttribute.getCritfense()+"%");
                sender.sendMessage("§8移速速度:"+playerAttribute.getSpeed());
                sender.sendMessage("§a----------------");
            }else {
                sender.sendMessage("§a该玩家不在线");
            }
        }else if (args.length==1&&args[0].equalsIgnoreCase("info")){
            OfflinePlayer offlinePlayer = Bukkit.getServer().getOfflinePlayer(sender.getName());
            if (offlinePlayer.isOnline()&&main.playersatt.containsKey(sender.getName())){
                PlayerAttribute playerAttribute = main.playersatt.get(sender.getName());
                sender.sendMessage("§a-----玩家属性属性-----");
                sender.sendMessage("§8血量:"+playerAttribute.getHealth());
                sender.sendMessage("§8攻击:"+playerAttribute.getMinDamage()+"-"+playerAttribute.getMaxDamage());
                sender.sendMessage("§8护甲值:"+playerAttribute.getDefense());
                sender.sendMessage("§8暴击几率:"+playerAttribute.getCritRate()+"%");
                sender.sendMessage("§8暴击倍率:"+playerAttribute.getCritDouble());
                sender.sendMessage("§8暴击抵抗:"+playerAttribute.getCritfense()+"%");
                sender.sendMessage("§8吸血几率:"+playerAttribute.getSuckhealthRate()+"%");
                sender.sendMessage("§8吸血倍率:"+playerAttribute.getSuckhealthDouble());
                sender.sendMessage("§8回血:"+playerAttribute.getLifeSpawn());
                sender.sendMessage("§8伤害反弹:"+playerAttribute.getCritfense()+"%");
                sender.sendMessage("§8移速速度:"+playerAttribute.getSpeed());
                sender.sendMessage("§a----------------");
            }else {
                sender.sendMessage("§a该玩家不在线");
            }
        }
        return false;
    }
}
