package com.jpa.controller;

import com.jpa.model.entity.ConsumerEntity;
import com.jpa.model.entity.ProductEntity;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class ConsumerController {

    public void consumeProduct(EntityManager em) {
        em.getTransaction().begin();

        ProductEntity pd1=new ProductEntity();
        pd1.setProductName("티비");
        pd1.setPrice(12200230);

        ProductEntity pd2=new ProductEntity();
        pd2.setProductName("아이폰");
        pd2.setPrice(13000000);

        ProductEntity pd3=new ProductEntity();
        pd3.setProductName("갤럭시");
        pd3.setPrice(13200000);

        ProductEntity pd4=new ProductEntity();
        pd4.setProductName("김치냉장고");
        pd4.setPrice(53200000);

        ConsumerEntity c1=new ConsumerEntity();
        c1.setConsumerId("gamja123");
        c1.setConsumerPwd("1234");
        c1.setConsumerName("감자1");
        c1.setConsumerEmail("woogamjaa@dmawd.codk");

        ConsumerEntity c2=new ConsumerEntity();
        c2.setConsumerId("minyoung");
        c2.setConsumerPwd("12345");
        c2.setConsumerName("민영2");
        c2.setConsumerEmail("minyoung@dmawd.codk");

        ConsumerEntity c3=new ConsumerEntity();
        c3.setConsumerId("yejin");
        c3.setConsumerPwd("12323145");
        c3.setConsumerName("예진");
        c3.setConsumerEmail("yejin@dmawd.codk");

        em.persist(pd1);
        em.persist(pd2);
        em.persist(pd3);
        em.persist(pd4);

        em.persist(c1);
        em.persist(c2);
        em.persist(c3);

        pd1.setConsumerList(new ArrayList(List.of(c1)));
        pd2.setConsumerList(new ArrayList(List.of(c1,c2)));
        pd3.setConsumerList(new ArrayList(List.of(c1,c2,c3)));
        pd4.setConsumerList(new ArrayList(List.of(c1,c2,c3)));

        em.getTransaction().commit();
        em.clear();

        ProductEntity findProduct=em.find(ProductEntity.class, 2);
        System.out.println(findProduct);
        findProduct.getConsumerList().forEach(consumer-> {
            System.out.println("회원이 구매한 상품");
            for (ProductEntity p : consumer.getProductList()) {
                System.out.println(p.getProductName()+""+p.getPrice());
            }
        });
        }
}
