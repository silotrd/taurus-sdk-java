package ai.silot.taurus.model.invoice;

import lombok.Data;

import java.io.Serializable;

@Data
public class InvoiceVoAvailableEWallet implements Serializable {

    private String bankCode;

    private String name;

}
