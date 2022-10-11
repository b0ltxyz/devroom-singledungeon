package me.rin.devroom.gui;

import com.hakan.core.item.ItemBuilder;
import com.hakan.core.ui.inventory.InventoryGui;
import com.hakan.core.ui.inventory.builder.InventoryBuilder;
import me.rin.devroom.stats.DungeonUserStatistics;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class StatisticsGUI {

    private static final DecimalFormat FORMAT = new DecimalFormat("0.00");


    private final Player player;
    private final InventoryGui gui;

    public StatisticsGUI(Player player, DungeonUserStatistics statistics) {
        this.player = player;
        this.gui = new InventoryBuilder("StatisticsGUI").title("Statistics of " + this.player.getDisplayName()).size(1).build();

        this.gui.setItem(1, new ItemBuilder(Material.ZOMBIE_SPAWN_EGG, 1).name(true, "&7Average Kills Per Session").lores(Collections.singletonList(String.valueOf(FORMAT.format((double) statistics.getKillCount() / statistics.getSessionCount())))).build());
        this.gui.setItem(3, new ItemBuilder(Material.GOLDEN_APPLE, 1).name(true, "&7Played Sessions").lores(List.of(String.valueOf(statistics.getSessionCount()))).build());
        this.gui.setItem(5, new ItemBuilder(Material.GOLDEN_SWORD, 1).name(true, "&7Total Zombie Kills").lores(List.of(String.valueOf(statistics.getKillCount()))).build());
        this.gui.setItem(7, new ItemBuilder(Material.BONE, 1).name(true, "&7Total Deaths").lores(List.of(String.valueOf(statistics.getDeathCount()))).build());
    }

    public void open() {
        this.gui.open(this.player);
    }
}