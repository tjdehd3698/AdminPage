package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //order_detail table과 연결

//@Data를 orderdetail,item,user클래스가 전부 참조 중이므로 to_string이 중복 참조여서 overflow발생한다. 그래서 user와 item은 제외시켜준다.
//연관관계 설정에 대한 변수에 대해서는 exclude 해주는 것이 좋다.
@ToString(exclude = {"orderGroup","item"})

@EntityListeners(AuditingEntityListener.class)
@Builder                    //생성자 여러가지 생성가능
@Accessors(chain = true)    //setter를 chain형태로 가능
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime arrivalDate;

    private Integer quantity;

    private BigDecimal totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    //OrderDetail : Item = N : 1
    @ManyToOne
    private Item item;

    // OrderDetail : OrderGroup = N : 1
    @ManyToOne
    private OrderGroup orderGroup;

    /*//N : 1
    @ManyToOne
    private User user;  //반드시 객체를 적어야 한다.

    //N : 1
    @ManyToOne
    private Item item;*/
}
