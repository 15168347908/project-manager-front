package priv.jzy.front.entity;


import lombok.*;
import priv.jzy.front.enumeration.ReturnCode;

import java.io.Serializable;
import java.util.Optional;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> implements Serializable {
    private Integer code;
    private T data;
    private String msg;

    private final static String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    private final static String DEFAULT_FAIL_MESSAGE = "操作失败";

    public static <T> R<T> data(T data, String msg) {
        return R.<T>builder().code(ReturnCode.SUCCESS.getCode())
                .data(data)
                .msg(msg)
                .build();
    }

    public static <T> R<T> data(T data) {
        return data(data, DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> R<T> success(String msg) {
        return R.<T>builder().code(ReturnCode.SUCCESS.getCode())
                .msg(msg)
                .build();
    }

    public static <T> R<T> success() {
        return success(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> R<T> fail(String msg) {
        return R.<T>builder().code(ReturnCode.FAIL.getCode())
                .msg(msg)
                .build();
    }

    public static <T> R<T> fail() {
        return fail(DEFAULT_FAIL_MESSAGE);
    }

    public static <T> R<T> status(Boolean status) {
        return Optional.ofNullable(status).orElse(true) ?
                success(DEFAULT_SUCCESS_MESSAGE) : fail(DEFAULT_FAIL_MESSAGE);
    }
}
