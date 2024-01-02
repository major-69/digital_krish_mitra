package digi.coders.digitalkrishimitraa.Model;

public class BadgeModal {
    public BadgeModal() {
    }

    String badgeRank, badgeRankName, badgeRankDefinition,salaryPackage,level;

    public BadgeModal(String badgeRank, String badgeRankName, String badgeRankDefinition, String salaryPackage, String level) {
        this.badgeRank = badgeRank;
        this.badgeRankName = badgeRankName;
        this.badgeRankDefinition = badgeRankDefinition;
        this.salaryPackage = salaryPackage;
        this.level = level;
    }



    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBadgeRank() {
        return badgeRank;
    }

    public void setBadgeRank(String badgeRank) {
        this.badgeRank = badgeRank;
    }

    public String getBadgeRankName() {
        return badgeRankName;
    }

    public void setBadgeRankName(String badgeRankName) {
        this.badgeRankName = badgeRankName;
    }

    public String getBadgeRankDefinition() {
        return badgeRankDefinition;
    }

    public void setBadgeRankDefinition(String badgeRankDefinition) {
        this.badgeRankDefinition = badgeRankDefinition;
    }

    public String getSalaryPackage() {
        return salaryPackage;
    }

    public void setSalaryPackage(String salaryPackage) {
        this.salaryPackage = salaryPackage;
    }
}
