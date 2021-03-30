package ai.silot.taurus.model.invoice;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class InvoiceVoPaymentChannel implements Serializable {

    private List<InvoiceVoAvailableBank> availableBanks;

    private List<InvoiceVoAvailableEWallet> availableEwallets;

}
