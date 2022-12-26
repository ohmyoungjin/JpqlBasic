package jpql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //연관 관계 편의 메서드
    public void addTeam(Member member) {
        members.add(member);
        member.setTeam(this);
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> memberList) {
        this.members = memberList;
    }
}
