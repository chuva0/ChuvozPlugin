package rainprojects.chuvoz.entities;

import org.bukkit.ChatColor;

public enum Group {

    MEMBER(ChatColor.GRAY, "Membro", "[M]", 0, false, false),
    VIP(ChatColor.GREEN, "VIP", "[VIP]", 1, false, false),
    MVP(ChatColor.DARK_AQUA, "MVP", "[MVP]", 2, false, false),
    CHUVOZ(ChatColor.GOLD, "Chuvoz", "[Chuvoz]", 3, true, true),
    AJUDANTE(ChatColor.YELLOW, "Ajudante", "[Ajudante]", 4, false, false),
    MODERADOR(ChatColor.DARK_GREEN, "Moderador", "[Moderador]", 5, false, false),
    ADMINISTRADOR(ChatColor.RED, "Administrador", "[Admin]", 6, true, false),
    GERENTE(ChatColor.DARK_PURPLE, "Gerente", "[Gerente]", 7, true, true),
    DONO(ChatColor.DARK_RED, "Dono", "[Dono]", 20, true, true);

    private ChatColor color;
    private String displayName;
    private String displayGame;
    private int weight;
    private boolean isWhite;
    private boolean newLine;

    Group(ChatColor color, String displayName, String displayGame, int weight, boolean isWhite, boolean newLine) {
        this.color = color;
        this.displayName = displayName;
        this.displayGame = displayGame;
        this.weight = weight;
        this.isWhite = isWhite;
        this.newLine = newLine;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDisplayGame() {
        return displayGame;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isWhite() {
        return isWhite;
    }
    public boolean newLine() {
        return newLine;
    }
}
