package com.example.study.model.entity;

import com.example.study.model.enumclass.UserStatus;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data                       //to_string 생성
@NoArgsConstructor          //파라미터 없는 생성자 생성
@AllArgsConstructor         //기본 생성자 getter,setter 모두 생성
@Entity                     //==table
@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder                    //생성자 여러가지 생성가능
@Accessors(chain = true)    //setter를 chain형태로 가능
//@Table(name='user')   class 의 이름이 DB와 같다면 할 필요 없다
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;      //REGISTERED / UNREGISTERED / WAITING

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    //User : OrderGroup = 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;

    /*//1 : N - orderDetail과의 관계 - list로 형성
    //mappedBy는 어떠한 변수에 mapping할 것인지 설정,mapping할 클래스의 변수 이름과 같아야 한다.(user클래스의 user)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderDetail> orderDetailList;*/

}
