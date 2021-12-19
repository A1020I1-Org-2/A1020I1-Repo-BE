package codegym.vn.dto;

public class StatisticInterestDto {
    private String month;
    private String money;

    public StatisticInterestDto() {
    }

    public StatisticInterestDto(String month, String money) {
        this.month = month;
        this.money = money;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
