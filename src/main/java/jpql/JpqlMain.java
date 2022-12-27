package jpql;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {

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

            //멤버 정보 생성
            Member member1 = new Member();
            member1.setUserName("member1");
            member1.changeTeam(team);
            member1.setType(MemberType.ADMIN);
            em.persist(member1);





            String query = "select m from Member m " +
                    "where m.type = :userType";
            List<Member> resultList = em.createQuery(query, Member.class)
                    .setParameter("userType", MemberType.ADMIN)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : resultList) {
                System.out.println("member : " + member);
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
