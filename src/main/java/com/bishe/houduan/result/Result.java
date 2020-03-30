package com.bishe.houduan.result;
public class Result {
    private int code;



    public Result(int code)
    {
        this.code = code;
    }

    public Result(int resultCode, String message, Object data) {
    }

    public int getCode()
    {

        return code;
    }
    public  void setCode(int code)
    {
        this.code = code;
    }
}
