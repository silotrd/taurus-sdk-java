package ai.silot.taurus.model.invoice;

import lombok.Data;

import java.io.Serializable;

@Data
public class InvoiceVoAvailableBank implements Serializable {

    private String accountHolderName;

    private String bankCode;

    private String virtualAccountNumber;

}
