package com.example.demo.repository;

import com.example.demo.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    @Query(value = "select\n" +
            "        p1_0.id,\n" +
            "        p1_0.account_id,\n" +
            "        p1_0.category_id,\n" +
            "        p1_0.update_at,\n" +
            "        p1_0.description,\n" +
            "        p1_0.gu_id,\n" +
            "        p1_0.link,\n" +
            "        p1_0.publish_date,\n" +
            "        p1_0.title \n" +
            "    from post p1_0 \n" +
            "    join category c1_0 \n" +
            "            on c1_0.id=p1_0.category_id \n" +
            "    where\n" +
            "        (\n" +
            "            p1_0.link like :keyword \n" +
            "            or p1_0.title like :keyword \n" +
            "        ) \n" ,
            countQuery = "select count(p1_0.id) \n" +
                    "    from\n" +
                    "        post p1_0 \n" +
                    "    join\n" +
                    "        category c1_0 \n" +
                    "            on c1_0.id=p1_0.category_id \n" +
                    "    where\n" +
                    "        (\n" +
                    "            p1_0.link like :keyword \n" +
                    "            or p1_0.title like :keyword\n" +
                    "        ) \n"  ,
            nativeQuery = true
    )
    Page<Post> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

    Page<Post> findAllByAccountId(Long userId, Pageable pageable);

    Page<Post> findAllByCategoryId(Long categoryId,  Pageable pageable);

    List<Post> findByLink(String link);

    Optional<Post> findByTitle(String title);

    Optional<Post> findByGuId(String guId);

}
