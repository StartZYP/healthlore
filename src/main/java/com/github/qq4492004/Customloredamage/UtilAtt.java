package com.github.qq4492004.Customloredamage;

import com.github.qq4492004.Customloredamage.Entity.ConfigAttribute;
import com.github.qq4492004.Customloredamage.Entity.PlayerAttribute;
import com.github.qq4492004.Customloredamage.Entity.TempAttribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class UtilAtt {
    public static PlayerAttribute GetPlayerItemAttribute(Player player,int i){
        PlayerAttribute playerAttribute = new PlayerAttribute(2, 1, 1, 0,0,0,0,0,0,0,0,0);
        TempAttribute tempAttribute =null;
        if (player.getInventory().getItem(i) != null) {
            tempAttribute = getEquipStates(player.getInventory().getItem(i));
            playerAttribute.setTempAttribute(tempAttribute);
            playerAttribute = playerAttribute.GetAllAttr();
            //System.out.println(playerAttribute);
        }

        ItemStack[] itemStacks = player.getInventory().getArmorContents();
        ItemStack[] arr = itemStacks;
        int len$ = itemStacks.length;
        for (int i$ = 0; i$ < len$; ++i$) {
            ItemStack itemStack = arr[i$];
            if (itemStack != null) {
                TempAttribute tempAtt = getEquipStates(itemStack);
                if (tempAtt != null) {
                    playerAttribute.setTempAttribute(tempAtt);
                    playerAttribute = playerAttribute.GetAllAttr();
                }
            }
        }
        return playerAttribute;
    }


    public static PlayerAttribute GetPlayerAttribute(Player player){
        PlayerAttribute playerAttribute =new PlayerAttribute(2, 1, 1, 0,0,0,0,0,0,0,0,0);
        TempAttribute tempAttribute =null;
        if (player.getInventory().getItemInMainHand() != null) {
            //System.out.println("手上有物品"+player.getItemInHand().getType());
            tempAttribute = getEquipStates(player.getInventory().getItemInMainHand());
            playerAttribute.setTempAttribute(tempAttribute);
            playerAttribute = playerAttribute.GetAllAttr();
            //System.out.println(playerAttribute);
        }
        if (player.getInventory().getItemInOffHand() != null) {
            //System.out.println("手上有物品"+player.getItemInHand().getType());
            tempAttribute = getEquipStates(player.getInventory().getItemInOffHand());
            playerAttribute.setTempAttribute(tempAttribute);
            playerAttribute = playerAttribute.GetAllAttr();
            //System.out.println(playerAttribute);
        }

        ItemStack[] itemStacks = player.getInventory().getArmorContents();
        ItemStack[] arr = itemStacks;
        int len$ = itemStacks.length;
        for (int i$ = 0; i$ < len$; ++i$) {
            ItemStack itemStack = arr[i$];
            if (itemStack != null) {
                TempAttribute tempAtt = getEquipStates(itemStack);
                if (tempAtt != null) {
                    playerAttribute.setTempAttribute(tempAtt);
                    playerAttribute = playerAttribute.GetAllAttr();
                }
            }
        }
        return playerAttribute;
    }

    private static TempAttribute getEquipStates(ItemStack itemStack){
        if (itemStack.getItemMeta() == null) {
            return null;
        } else if (itemStack.getItemMeta().getLore() == null) {
            return null;
        } else if (itemStack.getItemMeta().getLore().size() == 0) {
            return null;
        } else {
            double MaxDamage=0;
            double MinDamage=0;
            double Defense=0;
            double CritRate=0;
            double CritDouble=0;
            double Health=0;
            double Speed=0;
            double Critfense=0;
            double LifeSpawn=0;
            double SuckhealthRate=0;
            double SuckhealthDouble=0;
            double Damagerebound=0;
            Iterator Is = itemStack.getItemMeta().getLore().iterator();
            while (Is.hasNext()){
                String lore = (String) Is.next();
                MaxDamage += getMaxDamage(lore);
                MinDamage += getMinDamage(lore);
                Defense += getDenfense(lore);
                CritRate += getCritRate(lore);
                CritDouble += getCritDouble(lore);
                SuckhealthDouble += getSuckhealthDouble(lore);
                SuckhealthRate += getSuckhealthRate(lore);
                LifeSpawn += getLifeSpawn(lore);
                Damagerebound += getDamagerebound(lore);
                Speed += getSpeed(lore);
                Critfense += getCritfense(lore);
                Health += getHealth(lore);
            }
            return new TempAttribute(MaxDamage,MinDamage,Defense,CritRate,CritDouble,Health,Speed,Critfense,LifeSpawn,SuckhealthRate,SuckhealthDouble,Damagerebound);
        }
    }

    private static double getMaxDamage(String lore){
        if (lore.contains(ConfigAttribute.Damage)) {
            String[] s = lore.split(ConfigAttribute.split);
            if (s[1].contains("-")){
                String[] split = s[1].split("-");
                return Double.parseDouble(split[1]);
            }
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }

    private static double getMinDamage(String lore){
        if (lore.contains(ConfigAttribute.Damage)) {
            String[] s = lore.split(ConfigAttribute.split);
            if (s[1].contains("-")){
                String[] split = s[1].split("-");
                return Double.parseDouble(split[0]);
            }
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }

    private static double getDenfense(String lore){
        if (lore.contains(ConfigAttribute.Defense)) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }

    private static double getCritfense(String lore){
        if (lore.contains(ConfigAttribute.Critfense)) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }
    private static double getDamagerebound(String lore){
        if (lore.contains(ConfigAttribute.Damagerebound)&&lore.contains("%")) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1].substring(0,s[1].length()-1));
        } else {
            return 0;
        }
    }
    private static double getLifeSpawn(String lore){
        if (lore.contains(ConfigAttribute.LifeSpawn)) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }
    private static double getSuckhealthRate(String lore){
        if (lore.contains(ConfigAttribute.SuckhealthRate)&&lore.contains("%")) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1].substring(0,s[1].length()-1));
        } else {
            return 0;
        }
    }


    private static double getSuckhealthDouble(String lore){
        if (lore.contains(ConfigAttribute.SuckhealthDouble)) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }

    private static double getSpeed(String lore){
        if (lore.contains(ConfigAttribute.Speed)&&lore.contains("%")) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1].substring(0,s[1].length()-1));
        } else {
            return 0;
        }
    }

    private static double getCritRate(String lore){
        if (lore.contains(ConfigAttribute.CritRate)&&lore.contains("%")) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1].substring(0,s[1].length()-1));
        } else {
            return 0;
        }
    }

    private static double getCritDouble(String lore){
        if (lore.contains(ConfigAttribute.CritDouble)&&lore.contains("%")) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1].substring(0,s[1].length()-1));
        } else {
            return 0;
        }
    }

    private static double getHealth(String lore){
        if (lore.contains(ConfigAttribute.Health)) {
            String[] s = lore.split(ConfigAttribute.split);
            return Double.parseDouble(s[1]);
        } else {
            return 0;
        }
    }


}
