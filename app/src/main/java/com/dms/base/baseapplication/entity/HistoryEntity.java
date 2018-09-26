package com.dms.base.baseapplication.entity;

/**
 * Created by 龚万全 on 2016/9/27.
 * Description:
 */

public class HistoryEntity {

    /**
     * date : 15400927
     * day : 27
     * event : 　　16世纪，由德国发生的宗教改革运动蔓延到瑞士、英、法、荷兰等国。天主教会为了抵制新教，于1534年组织了耶稣会。西班牙贵族伊格纳蒂·罗问拉是耶稣会的创立者，他曾在萨拉曼加大学和巴黎大学研究了多年神学，他的《精神锻炼》一书阐述了耶稣会组织纲领原则。耶稣会以总会为最高权力机关，下设咨议会，实际权力集中于总管罗耀拉手中。
     　　1540年9月27日，耶稣会经罗马教皇正式批准。耶稣会教士四出传教，他们居世俗生活中心，按天主教会利益和耶稣会指令来影响社会生活。耶稣会始终与教庭密切联系，在天主教反对新教中起了不可低估的作用；同时，耶稣会重视海外的播道活动，客观上为西方科技的传播作了些有益的贡献。

     * id : 569881b6590146d407332d12
     * month : 9
     * title : 罗马教皇正式批准耶稣会
     */

    private String date;
    private int day;
    private String event;
    private String id;
    private int month;
    private String title;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}