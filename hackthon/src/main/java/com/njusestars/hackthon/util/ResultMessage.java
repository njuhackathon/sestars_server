package com.njusestars.hackthon.util;

import lombok.Data;

@Data
public class ResultMessage {
    public String message;
    public boolean success;
    public Object data;

    public ResultMessage() {
    }

    public ResultMessage(String message, boolean success, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }
}
