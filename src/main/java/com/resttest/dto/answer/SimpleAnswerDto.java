package com.resttest.dto.answer;

/**
 * Created by Владислав on 27.03.2017.
 */
public class SimpleAnswerDto {

    private int serialNumber;

    private String answer;

    private String type;

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
