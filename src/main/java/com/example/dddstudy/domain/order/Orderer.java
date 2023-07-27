package com.example.dddstudy.domain.order;

import com.example.dddstudy.domain.member.Member;
import com.example.dddstudy.domain.member.MemberId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Orderer {

    //회원 애그리거트를 참조할때 애그리거트 루트를 참조하면 문제를 야기할 수 있음, ID를 통해서만 참조
    @AttributeOverride(name = "id", column = @Column(name = "orderer_id"))
    private MemberId memberId;

    @Column(name = "orderer_name")
    private String name;

    public Orderer(MemberId memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderer orderer = (Orderer) o;
        return Objects.equals(memberId, orderer.memberId) && Objects.equals(name, orderer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name);
    }
}
