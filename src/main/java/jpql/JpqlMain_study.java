package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpqlMain_study {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //팀 정보 생성
            Team team = new Team();
            team.setName("PC");
            em.persist(team);

            //상품 정보 생성
            Product product = new Product();
            product.setName("마우스");
            product.setPrice(1000);
            product.setStockAmount(999);
            em.persist(product);

            //멤버 정보 생성
            Member member1 = new Member();
            member1.setUserName("member1");
            member1.changeTeam(team);
            em.persist(member1);

            //주문 정보 생성
            Order order = new Order();
            //주문자
            order.setMember(member1);
            //배송지 정보
            order.setAddress(new Address("hwa", "sang", "81"));
            //물품 정보
            order.setProduct(product);
            em.persist(order);






            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
