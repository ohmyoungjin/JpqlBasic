package jpql;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
public class Member {

    public Member() {

    }

    public Member(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    private int age;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    //Member => Team 연관 관계 편의 메서드
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    //Order => Member 연관 관계 편의 메서드
    public void addOrder(Order order) {
        orders.add(order);
        order.setMember(this);
    }
}
