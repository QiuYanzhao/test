package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interface_limit_change_log")
public class FlowLimitAdjustmentLog {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "flow_limit_id")
    private Long flowLimitId;

    @Column(name = "person_id")
    private String personId;

    @Column(name = "operation_time")
    private Date operationTime;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "app_key")
    private String appKey;
}
