package com.souf.jpabootstrap;

import com.souf.jpabootstrap.entity.Event;
import com.souf.jpabootstrap.entity.LoginRequest;
import com.souf.jpabootstrap.jpa.JpaEntityManagerFactory;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class MainClass {

    public static void main(String[] args) {
        EntityManager em = getJpaEntityManager(
                "jdbc:mysql://localhost:3306/appadevdb", "dbuser", "dbpassword");

        System.out.println("Start time: " + new Date());

        em.getTransaction().begin();
        for (int i = 1; i <= 34192; i++) {
            em.persist(Event.builder()
                    .eventID(1 + new Random().nextInt(10))
                    .tranTs(Instant.now())
                    .message("Message: " + UUID.randomUUID().toString())
                    .tranId(UUID.randomUUID().toString())
                    .build()
            );
            if ((i % 10000) == 0) {
                em.getTransaction().commit();
                em.clear();
                em.getTransaction().begin();
            }
        }
        em.getTransaction().commit();

        em.getTransaction().begin();
        for (int i = 1; i <= 12075; i++) {
            em.persist(LoginRequest.builder()
                    .tranId(UUID.randomUUID().toString())
                    .loginInfo("Login Info: " + UUID.randomUUID().toString())
                    .password("Password" + new Random().nextInt(10))
                    .userName("User" + new Random().nextInt(10))
                    .tranTs(Instant.now())
                    .build()
            );
            if ((i % 10000) == 0) {
                em.getTransaction().commit();
                em.clear();
                em.getTransaction().begin();
            }
        }
        em.getTransaction().commit();

        System.out.println("End time: " + new Date());
    }

    public static EntityManager getJpaEntityManager(String db_url, String db_user_name, String db_password) {
         final EntityManager ENTITY_MANAGER = new JpaEntityManagerFactory(
                 db_url,db_user_name,db_password,
                 new Class[]{Event.class,LoginRequest.class}
                 ).getEntityManager();
        return ENTITY_MANAGER;
    }

}
