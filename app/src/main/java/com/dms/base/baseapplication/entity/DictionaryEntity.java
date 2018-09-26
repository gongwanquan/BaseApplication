package com.dms.base.baseapplication.entity;

public class DictionaryEntity {


    /**
     * bihua : 12
     * bihuaWithBushou : 9
     * brief : 游；yóu；人或动物在水里行动：游泳。游水。；不固定：游资。游
     * bushou : 氵
     * detail : 游；yóu；【名】；(形声)；同本义〖flag’sstreamer〗；游,旌旗之流也。——《说文》；为下
     * name : 游
     * pinyin : yóu
     * wubi : iytb
     */

    private int bihua;
    private int bihuaWithBushou;
    private String brief;
    private String bushou;
    private String detail;
    private String name;
    private String pinyin;
    private String wubi;

    public int getBihua() {
        return bihua;
    }

    public void setBihua(int bihua) {
        this.bihua = bihua;
    }

    public int getBihuaWithBushou() {
        return bihuaWithBushou;
    }

    public void setBihuaWithBushou(int bihuaWithBushou) {
        this.bihuaWithBushou = bihuaWithBushou;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getBushou() {
        return bushou;
    }

    public void setBushou(String bushou) {
        this.bushou = bushou;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getWubi() {
        return wubi;
    }

    public void setWubi(String wubi) {
        this.wubi = wubi;
    }

    @Override
    public String toString() {
        return "笔画：" + bihua + '\n' + '\n' +
                "去部首后笔画数：" + bihuaWithBushou + '\n' + '\n' +
                "简介：" + brief + '\n' + '\n' +
                "部首：" + bushou + '\n' + '\n' +
                "明细：" + detail + '\n' + '\n' +
                "汉字：" + name + '\n' + '\n' +
                "拼音：" + pinyin + '\n' + '\n' +
                "五笔：" + wubi;
    }
}
