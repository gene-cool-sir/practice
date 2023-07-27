package com.example;

import java.io.Serializable;
import java.util.List;

public class WechatStepResult implements Serializable {

    private List<WechatStepInfo> stepInfoList;

    private Watermark watermark;

    public List<WechatStepInfo> getStepInfoList() {
        return stepInfoList;
    }

    public void setStepInfoList(List<WechatStepInfo> stepInfoList) {
        this.stepInfoList = stepInfoList;
    }

    public Watermark getWatermark() {
        return watermark;
    }

    public void setWatermark(Watermark watermark) {
        this.watermark = watermark;
    }
}