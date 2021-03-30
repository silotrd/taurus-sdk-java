package ai.silot.taurus.service;

import ai.silot.taurus.config.Taurus;
import ai.silot.taurus.model.TaurusBaseVo;
import ai.silot.taurus.model.TaurusListVo;
import ai.silot.taurus.model.invoice.InvoiceVo;
import ai.silot.taurus.util.TaurusHttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class InvoiceService {

    public static TaurusBaseVo<InvoiceVo> getById(Long invoiceId) throws IOException {
        String url = String.format("%s%s?invoiceId=%s",
                Taurus.serverUrl,
                "/api/v1/invoice",
                invoiceId);
        Type type = new TypeToken<TaurusBaseVo<InvoiceVo>>() {
        }.getType();
        return TaurusHttpUtil.get(url, type);
    }

    public static TaurusBaseVo<InvoiceVo> create(String externalId,
                                                 BigDecimal amount,
                                                 String description,
                                                 Integer invoiceDuration,
                                                 String successRedirectUrl) throws IOException {
        String url = String.format("%s%s",
                Taurus.serverUrl,
                "/api/v1/invoice");
        Type type = new TypeToken<TaurusBaseVo<InvoiceVo>>() {
        }.getType();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("externalId", externalId);
        paramMap.put("amount", amount);
        paramMap.put("description", description);
        paramMap.put("invoiceDuration", invoiceDuration);
        paramMap.put("successRedirectUrl", successRedirectUrl);
        return TaurusHttpUtil.post(url, type, new Gson().toJson(paramMap));
    }

    public static TaurusBaseVo<TaurusListVo<InvoiceVo>> list(Integer limit,
                                                             Long lastInvoiceId,
                                                             String status) throws IOException {
        String url = String.format("%s%s?limit=%s&lastInvoiceId=%s&status=%s",
                Taurus.serverUrl,
                "/api/v1/invoice/list",
                limit == null ? "" : limit,
                lastInvoiceId == null ? "" : lastInvoiceId,
                status == null ? "" : status);
        Type type = new TypeToken<TaurusBaseVo<TaurusListVo<InvoiceVo>>>() {
        }.getType();
        return TaurusHttpUtil.get(url, type);
    }

    public static TaurusBaseVo<Object> expire(Long invoiceId) throws IOException {
        String url = String.format("%s%s",
                Taurus.serverUrl,
                "/api/v1/invoice/expire");
        Type type = new TypeToken<TaurusBaseVo<Object>>() {
        }.getType();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("invoiceId", invoiceId);
        return TaurusHttpUtil.post(url, type, new Gson().toJson(paramMap));
    }

}
