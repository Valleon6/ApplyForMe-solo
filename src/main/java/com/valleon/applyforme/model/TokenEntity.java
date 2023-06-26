package com.valleon.applyforme.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "otp")
@Entity
public class TokenEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String otp;

   @ManyToOne
   @JoinColumn(name = "member_id")
   private Member member;

    @Override
    public String toString() {
        return "PasswordResetTokenEntity{" +
                "id=" + id +
                ", otp='" + otp + '\'' +
                ", member=" + member.getEmailAddress() +
                '}';
    }
}
