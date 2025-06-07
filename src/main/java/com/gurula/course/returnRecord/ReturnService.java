package com.gurula.course.returnRecord;

import com.gurula.course.order.dto.ReturnRequestDTO;

public interface ReturnService {
    void applyReturn(ReturnRequestDTO dto);

    void approveReturn(String id);

    void editReason(ReturnRequestDTO dto);

    void rejectReturn(String id);
}
