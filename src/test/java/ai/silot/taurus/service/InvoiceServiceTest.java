package ai.silot.taurus.service;

import ai.silot.taurus.config.Taurus;
import ai.silot.taurus.model.TaurusBaseVo;
import ai.silot.taurus.model.TaurusListVo;
import ai.silot.taurus.model.invoice.InvoiceVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class InvoiceServiceTest {
    public static final Integer successCode = 200;

    @Before
    public void init() {
        Taurus.apiKey = "dcc99ba6-3b2f-479b-9f85-86a09ccaaacf";
        Taurus.serverUrl = "http://k8s-azure.silot.tech:30331";
    }

    @Test
    public void testCreate() throws IOException {
        String externalId = "523452303483";
        BigDecimal amount = new BigDecimal(16566);
        String description = "test create";
        Integer invoiceDuration = 86400;
        String successRedirectUrl = "http://localhost:8080";

        TaurusBaseVo<InvoiceVo> responseVo = InvoiceService.create(
                externalId, amount, description, invoiceDuration, successRedirectUrl);
        InvoiceVo invoiceVo = responseVo.getData();
        Assert.assertEquals(responseVo.getCode(), successCode);
        Assert.assertEquals(invoiceVo.getStatus(), "UNPAID");
    }

    @Test
    public void testGetById() throws IOException {
        Long invoiceId = 1373843417495793665L;
        TaurusBaseVo<InvoiceVo> responseVo = InvoiceService.getById(invoiceId);
        InvoiceVo invoiceVo = responseVo.getData();
        Assert.assertEquals(responseVo.getCode(), successCode);
        Assert.assertEquals(invoiceVo.getInvoiceId(), invoiceId);

        Long invalidInvoiceId = 1373843417495793666L;
        Integer notFoundCode = 11001;
        TaurusBaseVo<InvoiceVo> notFoundResponseVo = InvoiceService.getById(invalidInvoiceId);
        Assert.assertEquals(notFoundResponseVo.getCode(), notFoundCode);
    }

    @Test
    public void testList() throws IOException {
        int limit = 2;
        TaurusBaseVo<TaurusListVo<InvoiceVo>> responseVo = InvoiceService.list(limit, null, null);
        List<InvoiceVo> list = responseVo.getData().getList();

        Assert.assertEquals(responseVo.getCode(), successCode);
        Assert.assertEquals(limit, list.size());
    }

    @Test
    public void testExpire() throws IOException {
        String externalId = "523452303484";
        BigDecimal amount = new BigDecimal(16567);
        String description = "test create";
        Integer invoiceDuration = 86400;
        String successRedirectUrl = "http://localhost:8080";

        TaurusBaseVo<InvoiceVo> responseVo = InvoiceService.create(
                externalId, amount, description, invoiceDuration, successRedirectUrl);
        InvoiceVo invoiceVo = responseVo.getData();
        Assert.assertEquals(responseVo.getCode(), successCode);
        Assert.assertEquals(invoiceVo.getStatus(), "UNPAID");

        Long invoiceId = invoiceVo.getInvoiceId();
        TaurusBaseVo<Object> expireVo = InvoiceService.expire(invoiceId);
        Assert.assertEquals(expireVo.getCode(), successCode);

        TaurusBaseVo<InvoiceVo> getResponseVo = InvoiceService.getById(invoiceId);
        InvoiceVo getInvoiceVo = getResponseVo.getData();
        Assert.assertEquals(getResponseVo.getCode(), successCode);
        Assert.assertEquals(getInvoiceVo.getStatus(), "EXPIRED");
    }
}