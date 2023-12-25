package digi.coders.digitalkrishimitraa.Model;

public class BadgeModal {
    public BadgeModal() {
    }

    String BadgeRank, BadgeRankName, BadgeRankDefinition;

    public BadgeModal(String badgeRank, String badgeRankName, String badgeRankDefinition) {
        BadgeRank = badgeRank;
        BadgeRankName = badgeRankName;
        BadgeRankDefinition = badgeRankDefinition;
    }

    public String getBadgeRank() {
        return BadgeRank;
    }

    public void setBadgeRank(String badgeRank) {
        BadgeRank = badgeRank;
    }

    public String getBadgeRankName() {
        return BadgeRankName;
    }

    public void setBadgeRankName(String badgeRankName) {
        BadgeRankName = badgeRankName;
    }

    public String getBadgeRankDefinition() {
        return BadgeRankDefinition;
    }

    public void setBadgeRankDefinition(String badgeRankDefinition) {
        BadgeRankDefinition = badgeRankDefinition;
    }
}
