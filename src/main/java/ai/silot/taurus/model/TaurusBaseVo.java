package ai.silot.taurus.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaurusBaseVo<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;
}
