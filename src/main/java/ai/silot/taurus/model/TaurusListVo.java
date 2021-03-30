package ai.silot.taurus.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TaurusListVo<T> implements Serializable {
    public boolean hasNext;
    public List<T> list;
}
