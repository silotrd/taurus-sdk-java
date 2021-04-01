package ai.silot.taurus.service;

import ai.silot.taurus.config.Taurus;
import ai.silot.taurus.model.TaurusBaseVo;
import ai.silot.taurus.model.TaurusListVo;
import ai.silot.taurus.model.disbursement.DisbursementVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class DisbursementServiceTest {
    public static final Integer successCode = 200;

    @Before
    public void init() {
        Taurus.apiKey = "dcc99ba6-3b2f-479b-9f85-86a09ccaaacf";
        Taurus.serverUrl = "http://k8s-azure.silot.tech:30331";
    }

    @Test
    public void testCreate() throws IOException {
        String externalId = "1341802490800852001";
        BigDecimal amount = new BigDecimal("10000");
        String bankCode = "014";
        String accountHolderName = "Test";
        String accountNumber = "1234567890";
        String description = "Test Disbursement";

        TaurusBaseVo<DisbursementVo> responseVo = DisbursementService.create(
                externalId, amount, bankCode, accountHolderName, accountNumber, description);
        DisbursementVo disbursementVo = responseVo.getData();
        Assert.assertEquals(responseVo.getCode(), successCode);
        Assert.assertEquals(disbursementVo.getStatus(), "PENDING");
    }

    @Test
    public void testGetById() throws IOException {
        Long disbursementId = 1353544152879497217L;
        TaurusBaseVo<DisbursementVo> responseVo = DisbursementService.getById(disbursementId);
        DisbursementVo disbursementVo = responseVo.getData();
        Assert.assertEquals(responseVo.getCode(), successCode);
        Assert.assertEquals(disbursementVo.getDisbursementId(), disbursementId);

        Long invalidDisbursementId = 1373843417495793666L;
        Integer notFoundCode = 12001;
        TaurusBaseVo<DisbursementVo> notFoundResponseVo = DisbursementService.getById(invalidDisbursementId);
        Assert.assertEquals(notFoundResponseVo.getCode(), notFoundCode);
    }

    @Test
    public void testList() throws IOException {
        int limit = 2;
        TaurusBaseVo<TaurusListVo<DisbursementVo>> responseVo = DisbursementService.list(limit, null, null);
        List<DisbursementVo> list = responseVo.getData().getList();

        Assert.assertEquals(responseVo.getCode(), successCode);
        Assert.assertEquals(limit, list.size());
    }
}