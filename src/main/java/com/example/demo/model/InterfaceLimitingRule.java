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
@Table(name = "interface_limit_rule")
public class InterfaceLimitingRule {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "interface_name")
    private String interfaceName;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "interface_url")
    private String interfaceUrl;

    @Column(name = "init_limit")
    private Integer initLimit;

    @Column(name = "init_interval")
    private Integer initInterval;

    @Column(name = "max_limit")
    private Integer maxLimit;

    @Column(name = "can_adjusted")
    private Boolean canAdjusted;

    @Column(name = "console_show")
    private Boolean consoleShow;

    @Column(name = "last_alter_time")
    private Date lastAlterTime;

    @Column(name = "adjustment_range")
    private Integer adjustmentRange;

    @Column(name = "univalence")
    private Long univalence;

    @Column(name = "rule_url_pattern")
    private String ruleUrlPattern;

    @Column(name = "rule_type")
    private String ruleType;

    @Column(name = "rule_http_method")
    private String ruleHttpMethod;

    @Column(name = "rule_limit")
    private Integer ruleLimit;

    @Column(name = "rule_interval")
    private Integer ruleInterval;

    @Column(name = "rule_api")
    private String ruleApi;

    @Column(name = "rule_target")
    private String ruleTarget;

    @Column(name = "rule_order")
    private Integer ruleOrder;

    @Column(name = "example_url")
    private String exampleUrl;

    //本月限流峰值
    @Transient
    private Integer maxValueOfMonth;

}
