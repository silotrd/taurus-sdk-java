package ai.silot.taurus.model.disbursement;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class DisbursementVo {

    private Long disbursementId;

    private String externalId;

    private BigDecimal amount;

    private String currency;

    private String bankCode;

    private String accountHolderName;

    private String accountNumber;

    private String description;

    private String status;

    private String failedReason;

    private Date completeTime;

    private Date createTime;

    private Date modifyTime;

}
