package com.gurula.course.returnRecord;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "return_record")
public class ReturnRecord {
    @Id
    private String id;
    private String orderId;
    private String orderItemId;
    private String reason;
    private Long applyReturnAt;       // 客戶申請退貨日期
    private Long approveReturnAt;     // 同意退貨日期
    private Long rejectAt;            // 拒絕退貨日期
}
