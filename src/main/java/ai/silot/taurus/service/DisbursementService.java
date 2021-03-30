package ai.silot.taurus.service;

import ai.silot.taurus.config.Taurus;
import ai.silot.taurus.model.TaurusBaseVo;
import ai.silot.taurus.model.TaurusListVo;
import ai.silot.taurus.model.disbursement.DisbursementVo;
import ai.silot.taurus.util.TaurusHttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DisbursementService {

    public static TaurusBaseVo<DisbursementVo> getById(Long disbursementId) throws IOException {
        String url = String.format("%s%s?disbursementId=%s",
                Taurus.serverUrl,
                "/api/v1/disbursement",
                disbursementId);
        Type type = new TypeToken<TaurusBaseVo<DisbursementVo>>() {
        }.getType();
        return TaurusHttpUtil.get(url, type);
    }

    public static TaurusBaseVo<DisbursementVo> create(String externalId,
                                                      BigDecimal amount,
                                                      String bankCode,
                                                      String accountHolderName,
                                                      String accountNumber,
                                                      String description) throws IOException {
        String url = String.format("%s%s",
                Taurus.serverUrl,
                "/api/v1/disbursement");
        Type type = new TypeToken<TaurusBaseVo<DisbursementVo>>() {
        }.getType();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("externalId", externalId);
        paramMap.put("amount", amount);
        paramMap.put("bankCode", bankCode);
        paramMap.put("accountHolderName", accountHolderName);
        paramMap.put("accountNumber", accountNumber);
        paramMap.put("description", description);
        return TaurusHttpUtil.post(url, type, new Gson().toJson(paramMap));
    }

    public static TaurusBaseVo<TaurusListVo<DisbursementVo>> list(Integer limit,
                                                                  Long lastDisbursementId,
                                                                  String status) throws IOException {
        String url = String.format("%s%s?limit=%s&lastDisbursementId=%s&status=%s",
                Taurus.serverUrl,
                "/api/v1/disbursement/list",
                limit == null ? "" : limit,
                lastDisbursementId == null ? "" : lastDisbursementId,
                status == null ? "" : status);
        Type type = new TypeToken<TaurusBaseVo<TaurusListVo<DisbursementVo>>>() {
        }.getType();
        return TaurusHttpUtil.get(url, type);
    }

}
