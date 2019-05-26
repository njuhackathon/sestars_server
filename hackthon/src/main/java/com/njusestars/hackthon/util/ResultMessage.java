package com.njusestars.hackthon.util;

import lombok.Data;

@Data
public class ResultMessage {
    public String message;
    public boolean success;
    public Object data;
}
