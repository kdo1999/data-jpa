package study.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@ToString(of = {"id", "username", "age"})

@NamedQuery(
        name="Member.findByUsername",
        query="select m from Member m where m.username = :username"
) //컴파일시 문법오류가 발견되면 컴파일오류를 내준다.
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    /**
     * 연관관계 메서드
     * */
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

}
