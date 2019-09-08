package com.luckysite.util;

import com.luckysite.enmu.ResultCode;

import java.util.Objects;

/**
 * @author mahongbin
 * @date 2019/8/27 14:38
 * @Description
 */
public class ResponseResult<T> {
    private int code = -1;
    private String msg = "";
    private String msgKey;
    private T data;
    private long time = System.currentTimeMillis();

    public ResponseResult fail() {
        this.code = -1;
        return this;
    }

    public ResponseResult<T> fail(String msg) {
        this.setCode(ResultCode.ERROR.getCode());
        this.setMsg(msg);
        return this;
    }

    public ResponseResult<T> success() {
        this.setCode(ResultCode.SUCCESS.getCode());
        return this;
    }

    public ResponseResult<T> success(T data) {
        this.setCode(ResultCode.SUCCESS.getCode());
        this.setData(data);
        return this;
    }

    public boolean isSuccess() {
        return Objects.equals(this.code, ResultCode.SUCCESS.getCode());
    }

    public ResponseResult() {
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getMsgKey() {
        return this.msgKey;
    }

    public T getData() {
        return this.data;
    }

    public long getTime() {
        return this.time;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setMsgKey(final String msgKey) {
        this.msgKey = msgKey;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setTime(final long time) {
        this.time = time;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ResponseResult)) {
            return false;
        } else {
            ResponseResult<?> other = (ResponseResult)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label63: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label63;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label63;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$msgKey = this.getMsgKey();
                Object other$msgKey = other.getMsgKey();
                if (this$msgKey == null) {
                    if (other$msgKey != null) {
                        return false;
                    }
                } else if (!this$msgKey.equals(other$msgKey)) {
                    return false;
                }

                label42: {
                    Object this$data = this.getData();
                    Object other$data = other.getData();
                    if (this$data == null) {
                        if (other$data == null) {
                            break label42;
                        }
                    } else if (this$data.equals(other$data)) {
                        break label42;
                    }

                    return false;
                }

                if (this.getTime() != other.getTime()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ResponseResult;
    }

    @Override
    public int hashCode() {
        int PRIME = 1;
        int result = 1;
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        Object $msgKey = this.getMsgKey();
        result = result * 59 + ($msgKey == null ? 43 : $msgKey.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        long $time = this.getTime();
        result = result * 59 + (int)($time >>> 32 ^ $time);
        return result;
    }

    @Override
    public String toString() {
        return "ResponseResult(code=" + this.getCode() + ", msg=" + this.getMsg() + ", msgKey=" + this.getMsgKey() + ", data=" + this.getData() + ", time=" + this.getTime() + ")";
    }
}
