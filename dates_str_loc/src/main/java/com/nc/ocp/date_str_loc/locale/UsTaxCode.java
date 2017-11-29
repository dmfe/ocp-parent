package com.nc.ocp.date_str_loc.locale;

public class UsTaxCode {
    private int code;

    public UsTaxCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String toString() {
        return String.valueOf(code);
    }
}
