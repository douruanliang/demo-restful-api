package com.example.demo.comm.result;


import com.example.demo.comm.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 平台通用返回结果
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlatformResult implements Result {

    private Integer code;
    private String message;
    private Object data;

    public static PlatformResult success() {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static PlatformResult success(Object data) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static PlatformResult failure(ResultCode resultCode) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(resultCode);
        return result;
    }

    public static PlatformResult failure(ResultCode resultCode, Object data) {
        PlatformResult result = new PlatformResult();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static PlatformResult failure(String message) {
        PlatformResult result = new PlatformResult();
        result.setCode(ResultCode.PARAM_IS_INVALID.code());
        result.setMessage(message);
        return result;
    }



    public void setResultCode(ResultCode rc) {
        this.code = rc.code();
        this.message = rc.message();

    }


}
