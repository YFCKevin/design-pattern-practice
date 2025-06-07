package com.gurula.course.member;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "members")
public class Member {
    @Id
    private String id;
    private String name;
    private List<String> courseIds = new ArrayList<>();
}
