package com.dms.base.baseapplication.model;

public class HoroscopeEntity {


    /**
     * horoscope : 金玉出海日，临死，坐支，伤官。能歌善舞笔和墨，犹如白虎戏江水。冲在禄马登科甲，斑竹细雨伤情泪。子月，衰，伤官，无土运，鬼旺，风烛夭贱。丑月，虚名，轻财。寅月，偏财，不禄。卯月，合财，金玉满目。辰月，利路经商。巳月，武职显跃。午月，文官近卫。四季月，印旺富而有名，亥月，漂蓬，僧侣。
     * lunar : 二零一五年腊月初十
     * lunarDate : 乙未年己丑月庚子日壬午时
     * zodiac : 羊
     */

    private String horoscope;
    private String lunar;
    private String lunarDate;
    private String zodiac;

    public String getHoroscope() {
        return horoscope;
    }

    public void setHoroscope(String horoscope) {
        this.horoscope = horoscope;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public String getLunarDate() {
        return lunarDate;
    }

    public void setLunarDate(String lunarDate) {
        this.lunarDate = lunarDate;
    }

    public String getZodiac() {
        return zodiac;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    @Override
    public String toString() {
        return
                "八字信息：" + horoscope + '\n' + '\n' +
                        "阴历日期：" + lunar + '\n' + '\n' +
                        "农历时间：" + lunarDate + '\n' + '\n' +
                        "生肖：" + zodiac;
    }
}
