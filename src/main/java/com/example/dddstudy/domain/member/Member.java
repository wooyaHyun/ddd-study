package com.example.dddstudy.domain.member;

import com.example.dddstudy.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @EmbeddedId
    private MemberId memberId;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private boolean blocked;
}
