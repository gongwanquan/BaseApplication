package com.dms.base.baseapplication.model;

public class IdiomEntity {

    /**
     * name : 狐假虎威
     * pinyin : hújiǎhǔwēi
     * pretation : 假借↑狸假借老虎的威势。比喻依仗别人的势力欺压人。
     * sample : 果然府中来借，怎好不借？只怕被别人～的诓的去，这个却保不得他。
     * sampleFrom : 明·凌濛初《二刻拍案惊奇》卷二十
     * source : 《战国策·楚策一》虎求百兽而食之，得狐。……虎以为然，故遂与之行。兽见之皆走，虎不知兽畏己而走也，以为畏狐也。”
     */

    private String name;
    private String pinyin;
    private String pretation;
    private String sample;
    private String sampleFrom;
    private String source;

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

    public String getPretation() {
        return pretation;
    }

    public void setPretation(String pretation) {
        this.pretation = pretation;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getSampleFrom() {
        return sampleFrom;
    }

    public void setSampleFrom(String sampleFrom) {
        this.sampleFrom = sampleFrom;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return
                "成语：" + name + '\n' + '\n' +
                        "拼音：" + pinyin + '\n' + '\n' +
                        "释义：" + pretation + '\n' + '\n' +
                        "出自：" + sample + '\n' + '\n' +
                        "示例：" + sampleFrom + '\n' + '\n' +
                        "示例出自：" + source + '\n' + '\n';
    }
}
