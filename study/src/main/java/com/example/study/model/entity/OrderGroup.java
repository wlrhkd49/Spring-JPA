package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString(exclude = {"user", "orderDetailList"})
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String orderType; // 주문의 형태 : 일괄 or 개별

    private String revAddress;

    private String revName;

    private String paymentType; // 카드 or 현금

    private BigDecimal totalPrice;

    private Integer totalQuantity;

    private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // OrderGroup : User N : 1
    @ManyToOne
    //private Long userId; 객체로 관리하므로
    private User user; // 이 이름이 User의 mapped = user와 같아야함


    // OrderGroup : OrderDetail -> 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy =  "orderGroup")
    private List<OrderDetail> orderDetailList;
}
