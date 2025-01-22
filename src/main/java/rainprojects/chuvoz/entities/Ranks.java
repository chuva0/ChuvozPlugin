package rainprojects.chuvoz.entities;

import org.bukkit.ChatColor;

public enum Ranks {

    UNRANKED("[-]", "Unranked", 0, 0, ChatColor.DARK_GRAY);

    private String prefix;
    private String displayName;
    private int order;
    private double cost;
    private ChatColor color;

    public String getPrefix() {
        return prefix;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getOrder() {
        return order;
    }

    public double getCost() {
        return cost;
    }

    public ChatColor getColor() {
        return color;
    }

    Ranks(String prefix, String displayName, int order, double cost, ChatColor color) {
        this.prefix = prefix;
        this.displayName = displayName;
        this.order = order;
        this.cost = cost;
        this.color = color;
    }
}
