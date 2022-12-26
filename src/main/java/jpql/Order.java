package jpql;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDERS_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Embedded
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    //product => order 연관 관계 편의 메서드
    public void changeProduct(Product product) {
        this.product = product;
        product.getOrders().add(this);
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    //order => member 연관 관계 편의 메서드
    public void changeMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
}
