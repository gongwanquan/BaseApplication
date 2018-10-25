package com.dms.base.baseapplication.model;

public class PerpetualCalendar {

    /**
     * avoid : 种植掘沟开井
     * date : 2016-05-01
     * holiday : 劳动节
     * lunar : 三月廿五
     * lunarYear : 丙申
     * suit : 涂泥婚礼出行修道涂泥
     * weekday : 星期日
     * zodiac : 猴
     */

    private String avoid;
    private String date;
    private String holiday;
    private String lunar;
    private String lunarYear;
    private String suit;
    private String weekday;
    private String zodiac;

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getLunarYear() {
        return lunarYear;
    }

    public void setLunarYear(String lunarYear) {
        this.lunarYear = lunarYear;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    @Override
    public String toString() {
        return "忌：" + avoid + '\n' +
                "日期：" + date + '\n' +
                "节假日：" + holiday + '\n' +
                "农历日期：" + lunar + '\n' +
                "农历年：" + lunarYear + '\n' +
                "宜：" + suit + '\n' +
                "星期几：" + weekday + '\n' +
                "生肖：" + zodiac;
    }
}
