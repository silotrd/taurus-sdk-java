/*
 * Copyright 2021 silot.ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.silot.taurus.model.disbursement;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class DisbursementVo implements Serializable {

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
