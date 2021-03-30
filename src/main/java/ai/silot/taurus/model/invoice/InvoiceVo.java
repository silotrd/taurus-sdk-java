package ai.silot.taurus.model.invoice;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoiceVo implements Serializable {

    private Long invoiceId;

    private String externalId;

    private String status;

    private BigDecimal amount;

    private BigDecimal feesPaidAmount;

    private BigDecimal adjustedReceivedAmount;

    private String description;

    private String merchantName;

    private String invoiceUrl;

    private String successRedirectUrl;

    private String currency;

    private Date expiryTime;

    private Date payTime;

    private Date createTime;

    private Date modifyTime;

    private Date settleTime;

    private String paidChannel;

    private String paidMethod;

    private String paymentDestination;

    private String eWalletType;

    private InvoiceVoPaymentChannel paymentChannel;

}
