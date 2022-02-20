package priv.jzy.front.enumeration;


import lombok.Getter;

@Getter
public enum ReturnCode {

    SUCCESS(200),
    FAIL(400),
    ERROR(500);

    Integer code;

    ReturnCode(Integer code) {
        this.code = code;
    }
}
