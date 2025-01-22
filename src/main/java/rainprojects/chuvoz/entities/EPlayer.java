package rainprojects.chuvoz.entities;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EPlayer implements ConfigurationSerializable {

    private double money;
    private double cash;

    private Ranks rank;
    private Group group;

    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public EPlayer(double money, double cash, Ranks rank, Group group, UUID uuid) {
        this.money = money;
        this.cash = cash;
        this.rank = rank;
        this.group = group;
        this.uuid = uuid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        data.put("money", money);
        data.put("cash", cash);
        data.put("rank", rank.toString());
        data.put("group", group.toString());
        data.put("uuid", uuid.toString());
        return data;
    }

    public static EPlayer deserialize(Map<String, Object> data) {
        double money = (double) data.get("money");
        double cash = (double) data.get("cash");
        Ranks rank = Ranks.valueOf((String) data.get("rank"));
        Group group = Group.valueOf((String) data.get("group"));
        UUID uuid = UUID.fromString((String) data.get("uuid"));
        return new EPlayer(money, cash, rank, group, uuid);
    }

}
