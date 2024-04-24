package com.dm.content.model.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Cache implements Serializable {
    private static final long serialVersionUID = 1L;
    public String visitCount;
    public String messageCount;
    public String userCount;
    public String AMessageCount;
    public String BMessageCount;
    public String CMessageCount;
    public String DMessageCount;
    public String cxjsMessageCount;
    public String dzdwMessageCount;
    public String gtzyMessageCount;
    public String hjbhMessageCount;
    public String jjjcMessageCount;
    public String jtysMessageCount;
    public String jywtMessageCount;
    public String jjglMessageCount;
    public String kjxxMessageCount;
    public String ndshMessageCount;
    public String mzMessageCount;
    public String nyncMessageCount;
    public String smlyMessageCount;
    public String wsjsMessageCount;
    public String zfMessageCount;
    public String wzMessageCount;
    public List<Integer> dataCount;
    public List<Message> solveMes;
    public List<Message> solvingMes;
    public List<Message> solvedMes;
    public List<HotTopic> hotTopic;


    public Cache() {
    }

    public Cache(String visitCount, String messageCount, String userCount, String AMessageCount, String BMessageCount, String CMessageCount, String DMessageCount, String cxjsMessageCount, String dzdwMessageCount, String gtzyMessageCount, String hjbhMessageCount, String jjjcMessageCount, String jtysMessageCount, String jywtMessageCount, String jjglMessageCount, String kjxxMessageCount, String ndshMessageCount, String mzMessageCount, String nyncMessageCount, String smlyMessageCount, String wsjsMessageCount, String zfMessageCount, String wzMessageCount, List<Integer> dataCount) {
        this.visitCount = visitCount;
        this.messageCount = messageCount;
        this.userCount = userCount;
        this.AMessageCount = AMessageCount;
        this.BMessageCount = BMessageCount;
        this.CMessageCount = CMessageCount;
        this.DMessageCount = DMessageCount;
        this.cxjsMessageCount = cxjsMessageCount;
        this.dzdwMessageCount = dzdwMessageCount;
        this.gtzyMessageCount = gtzyMessageCount;
        this.hjbhMessageCount = hjbhMessageCount;
        this.jjjcMessageCount = jjjcMessageCount;
        this.jtysMessageCount = jtysMessageCount;
        this.jywtMessageCount = jywtMessageCount;
        this.jjglMessageCount = jjglMessageCount;
        this.kjxxMessageCount = kjxxMessageCount;
        this.ndshMessageCount = ndshMessageCount;
        this.mzMessageCount = mzMessageCount;
        this.nyncMessageCount = nyncMessageCount;
        this.smlyMessageCount = smlyMessageCount;
        this.wsjsMessageCount = wsjsMessageCount;
        this.zfMessageCount = zfMessageCount;
        this.wzMessageCount = wzMessageCount;
        this.dataCount = dataCount;
    }

    public Cache(String visitCount, String messageCount, String userCount, String AMessageCount, String BMessageCount, String CMessageCount, String DMessageCount, String cxjsMessageCount, String dzdwMessageCount, String gtzyMessageCount, String hjbhMessageCount, String jjjcMessageCount, String jtysMessageCount, String jywtMessageCount, String jjglMessageCount, String kjxxMessageCount, String ndshMessageCount, String mzMessageCount, String nyncMessageCount, String smlyMessageCount, String wsjsMessageCount, String zfMessageCount, String wzMessageCount, List<Integer> dataCount, List<Message> solveMes, List<Message> solvingMes, List<Message> solvedMes, List<HotTopic> hotTopic) {
        this.visitCount = visitCount;
        this.messageCount = messageCount;
        this.userCount = userCount;
        this.AMessageCount = AMessageCount;
        this.BMessageCount = BMessageCount;
        this.CMessageCount = CMessageCount;
        this.DMessageCount = DMessageCount;
        this.cxjsMessageCount = cxjsMessageCount;
        this.dzdwMessageCount = dzdwMessageCount;
        this.gtzyMessageCount = gtzyMessageCount;
        this.hjbhMessageCount = hjbhMessageCount;
        this.jjjcMessageCount = jjjcMessageCount;
        this.jtysMessageCount = jtysMessageCount;
        this.jywtMessageCount = jywtMessageCount;
        this.jjglMessageCount = jjglMessageCount;
        this.kjxxMessageCount = kjxxMessageCount;
        this.ndshMessageCount = ndshMessageCount;
        this.mzMessageCount = mzMessageCount;
        this.nyncMessageCount = nyncMessageCount;
        this.smlyMessageCount = smlyMessageCount;
        this.wsjsMessageCount = wsjsMessageCount;
        this.zfMessageCount = zfMessageCount;
        this.wzMessageCount = wzMessageCount;
        this.dataCount = dataCount;
        this.solveMes = solveMes;
        this.solvingMes = solvingMes;
        this.solvedMes = solvedMes;
        this.hotTopic = hotTopic;
    }

    public Cache(List<Message> solveMes, List<Message> solvingMes, List<Message> solvedMes, List<HotTopic> hotTopic) {
        this.solveMes = solveMes;
        this.solvingMes = solvingMes;
        this.solvedMes = solvedMes;
        this.hotTopic = hotTopic;
    }
}