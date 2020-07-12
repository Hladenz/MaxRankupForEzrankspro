package xyz.crystillizedprison.maxrankup.commands;

import com.google.common.collect.Lists;
import me.clip.ezrankspro.EZAPI;
import me.clip.ezrankspro.EZRanksPro;
import me.clip.ezrankspro.rankdata.Rankup;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.crystillizedprison.maxrankup.MaxRankUp;
import me.clip.ezrankspro.multipliers.CostHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxRankup implements CommandExecutor {

    public MaxRankup(MaxRankUp main) {
        this.main = main;
    }
    List<String> alphabet = Arrays.asList(new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"});

    MaxRankUp main;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("Maxrankup.MaxRankup")) {


                if (EZRanksPro.getAPI().isLastRank(p) == true) {
                    p.sendMessage(ChatColor.AQUA + "You Have Ranked Up to Z, Now do /prestige");
                    return true;
                }

                while (!EZRanksPro.getAPI().isLastRank(p)){

                    if (EZRanksPro.getAPI().getRankupProgress(p) == 100){
                        main.getEcon().withdrawPlayer(p, EZRanksPro.getAPI().getRankupCost(p) * CostHandler.getMultiplier(p,1));
                        Rankup Rankup = EZRanksPro.getAPI().getCurrentRankup(p);
                        EZRanksPro.getInstance().getActionHandler().executeRankupActions(p,Rankup);
                        EZRanksPro.getInstance().getActionHandler().executeRankupActions(p,Rankup);


                    }else{
                        break;
                    }

                }


                if (EZRanksPro.getAPI().isLastRank(p) == true) {
                    p.sendMessage(ChatColor.AQUA + "You Have Ranked Up to Z, Now do /prestige");
                }
                return true;
            }else {
                p.sendMessage(ChatColor.AQUA +"You Dont have permissions");
                return true;
            }
        }


        return false;
    }
}
