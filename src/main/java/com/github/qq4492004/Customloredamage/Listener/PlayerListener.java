package com.github.qq4492004.Customloredamage.Listener;

import Super_Life.Super_Life;
import com.github.qq4492004.Customloredamage.Entity.PlayerAttribute;
import com.github.qq4492004.Customloredamage.UtilAtt;
import com.github.qq4492004.Customloredamage.main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PlayerListener implements Listener {

    @EventHandler
    public void PlayerQuitGame(PlayerQuitEvent event){
        main.playersatt.remove(event.getPlayer().getName());
    }

    @EventHandler
    public void PlayerDamgeEntity(EntityDamageByEntityEvent event){
        double finalDamge;
        double finalCrit = 0;
        if (event.getDamager() instanceof Player){
            //System.out.println("攻击者是玩家");
            Player player= (Player) event.getDamager();
            PlayerAttribute playerAttribute = main.playersatt.get(player.getName());
            double maxdamage1 = playerAttribute.getMaxDamage();
            double minDamage = playerAttribute.getMinDamage();
            double critRate = playerAttribute.getCritRate();
            double critDouble = playerAttribute.getCritDouble();
            double suckhealthRate = playerAttribute.getSuckhealthRate();
            double suckhealthDouble = playerAttribute.getSuckhealthDouble();
            finalDamge = new Random().nextInt((int)maxdamage1) % (int)(maxdamage1-minDamage+1) + (int)minDamage;
            //System.out.println("攻击者是玩家伤害为:"+finalDamge);
            if(Math.random()<suckhealthRate/100){
                double health = player.getHealth();
                double tmpsuckhealth = finalDamge*(suckhealthDouble/100);

                if (playerAttribute.getHealth()<=(tmpsuckhealth+health)){
                    player.setHealth(playerAttribute.getHealth());
                    player.sendMessage("触发吸血已吸满");
                }else {
                    player.setHealth(player.getHealth()+tmpsuckhealth);
                    player.sendMessage("触发吸血"+tmpsuckhealth);
                }
            }
            if(Math.random()<critRate/100){
                finalCrit = finalDamge*(critDouble/100);
                finalDamge += finalCrit;
                player.sendMessage("触发暴击"+finalDamge);
            }
        }else {
            finalDamge = event.getFinalDamage();
            //System.out.println("攻击者是怪物");
        }

        if(event.getEntity() instanceof Player){
            Player player = ((Player) event.getEntity()).getPlayer();
            PlayerAttribute playerAttribute1 = main.playersatt.get(player.getName());
            double critfense = playerAttribute1.getCritfense();
            double damagerebound = playerAttribute1.getDamagerebound();
            double defense = playerAttribute1.getDefense();
            double pluginDamage = finalDamge/playerAttribute1.getDefense();
            //System.out.println("被攻击者是玩家防御力"+defense+"除得"+pluginDamage);
            if(event.getDamager() instanceof Player){
                if(Math.random()<critfense/100){
                    pluginDamage -= finalCrit;
                    player.sendMessage("暴击抵抗"+finalCrit);
                }
                if(Math.random()<damagerebound/100){
                    double rebound = pluginDamage * (damagerebound / 100);
                    ((Player) event.getDamager()).setHealth(((Player) event.getDamager()).getHealth()+rebound);
                    player.sendMessage("触发伤害反弹"+rebound);
                }
            }
            if (pluginDamage>=0){
                event.setDamage(event.getDamage()+pluginDamage);
            }else {
               event.setCancelled(true);
                player.sendMessage("攻击被抵抗"+finalDamge);
            }
            event.setDamage(pluginDamage);
        }else {
            event.setDamage(finalDamge);
            //System.out.println("被攻击者是怪物");
            //event.setDamage(finalDamge);
        }
    }


    @EventHandler
    public void Prepr(PlayerItemConsumeEvent event) {
        ItemStack Item = event.getItem();
        Player p = event.getPlayer();
        if (Item.hasItemMeta()) {
            if (Item.getItemMeta().hasLore()) {
                if (Item.getTypeId() == 322) {
                    if (!Item.hasItemMeta()) {
                        return;
                    }

                    if (!Item.getItemMeta().hasLore()) {
                        return;
                    }

                    ArrayList<String> lore = (ArrayList)Item.getItemMeta().getLore();
                    Iterator var6 = lore.iterator();

                    while(var6.hasNext()) {
                        String l = (String)var6.next();
                        int A;
                        if (l.equalsIgnoreCase("§b§l生命果§6§l-§a§l吃下去可以增强生命")) {
                            if (p.getMaxHealth() == (double)Super_Life.PlayerMaxHealth) {
                                p.sendMessage("§b[§a生命果§b] §6您已经到达血量上限,无法继续提升");
                                event.setCancelled(true);
                                return;
                            }

                            if (Super_Life.RewardChance >= Super_Life.MathRandom(1, 100)) {
                                A = Super_Life.MathRandom(Super_Life.RewardMinHealth, Super_Life.RewardMaxHealth);
                                File file1;
                                FileConfiguration config1;
                                if (p.getMaxHealth() + (double)A > (double)Super_Life.PlayerMaxHealth) {
                                    p.setMaxHealth((double)Super_Life.PlayerMaxHealth);
                                    file1 = new File(main.plugin.getDataFolder(), "players.yml");
                                    config1 = main.load(file1);
                                    config1.set("Players.List." + p.getName() + ".MaxHealth", p.getMaxHealth());

                                    try {
                                        config1.save(file1);
                                    } catch (IOException var12) {
                                        var12.printStackTrace();
                                    }
                                } else {
                                    p.setMaxHealth(p.getMaxHealth() + (double)A);
                                    file1 = new File(main.plugin.getDataFolder(), "players.yml");
                                    config1 = main.load(file1);
                                    config1.set("Players.List." + p.getName() + ".MaxHealth", p.getMaxHealth());

                                    try {
                                        config1.save(file1);
                                    } catch (IOException var11) {
                                        var11.printStackTrace();
                                    }
                                }

                                if (Super_Life.EatEffectEnable) {
                                    Super_Life.EatEffect(p);
                                }

                                p.sendMessage("§b[§a生命果§b] §6您成功提升了自己的生命");
                                if (Super_Life.EatMessageEnable) {
                                    String S = Super_Life.EatMessageString;
                                    S = S.replaceAll("&", "§");
                                    S = S.replaceAll("<player>", p.getName());
                                    Bukkit.broadcastMessage(S);
                                }

                                if (Super_Life.NearEnable) {
                                    Super_Life.NearHeal(p);
                                }
                            }
                        } else if (!Super_Life.PunishmentDemotion) {
                            p.sendMessage("§b[§a生命果§b] §6提升生命失败");
                        } else if (Super_Life.PunishmentChance >= Super_Life.MathRandom(1, 100)) {
                            A = Super_Life.MathRandom(Super_Life.PunishmentMinHealth, Super_Life.PunishmentMaxHealth);
                            if (p.getMaxHealth() - (double)A < 20.0D) {
                                p.setMaxHealth(20.0D);
                                p.sendMessage("§b[§a生命果§b] §6提升生命失败,您的生命降低为了原始生命");
                            } else {
                                p.setMaxHealth(p.getMaxHealth() - (double)A);
                                p.sendMessage("§b[§a生命果§b] §6提升生命失败,您的生命降低了" + A + "点");
                            }
                        }
                    }
                }

            }
        }
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void Leave(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        File file1 = new File(main.plugin.getDataFolder(), "players.yml");
        FileConfiguration config1 = main.load(file1);
        config1.set("Players.List." + p.getName() + ".Health", p.getHealth());

        try {
            config1.save(file1);
        } catch (IOException var6) {
            var6.printStackTrace();
        }

        main.plugin.getServer().getPlayerExact(p.getName()).setMaxHealth(20.0D);
    }

    public void setPlayerHealth(PlayerAttribute attr, Player player) {
        File file1 = new File(main.plugin.getDataFolder(), "players.yml");
        FileConfiguration config1 = main.load(file1);
        if (config1.contains("Players.List." + player.getName())) {
            player.setMaxHealth(config1.getDouble("Players.List." + player.getName() + ".MaxHealth")+attr.getHealth());
            //player.setHealth(config1.getDouble("Players.List." + player.getName() + ".Health"));
        }else {
            player.setMaxHealth(attr.getHealth() + 20.0D);
        }
        //player.setHealthScale(20);
    }

    public void setPlayerSpeed(PlayerAttribute attr, Player player){
        double speed = attr.getSpeed();
        if (speed>0){
            player.setWalkSpeed(0.2f+(float) (attr.getSpeed()/100));
        }else {
            player.setWalkSpeed(0.2f);
        }
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerAttribute attr = UtilAtt.GetPlayerAttribute(player);
        main.playersatt.put(player.getName(), attr);
        setPlayerHealth(attr,player);
    }

    @EventHandler
    public void onchangeweapon(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int newSlot = event.getNewSlot();
        PlayerAttribute attr = UtilAtt.GetPlayerItemAttribute(player,newSlot);
        main.playersatt.put(player.getName(), attr);
        setPlayerHealth(attr,player);
        setPlayerSpeed(attr,player);
    }


    @EventHandler
    public void dropEvent(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        PlayerAttribute attr = UtilAtt.GetPlayerAttribute(player);
        main.playersatt.put(player.getName(), attr);
        setPlayerHealth(attr,player);
        setPlayerSpeed(attr,player);
    }

    @EventHandler
    public void breakEvent(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        PlayerAttribute attr = UtilAtt.GetPlayerAttribute(player);
        main.playersatt.put(player.getName(), attr);
    }

    @EventHandler
    public void closeInv(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        PlayerAttribute attr = UtilAtt.GetPlayerAttribute(player);
        main.playersatt.put(player.getName(), attr);
        setPlayerHealth(attr,player);
        setPlayerSpeed(attr,player);
    }



}
