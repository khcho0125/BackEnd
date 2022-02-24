package com.onproject.Repository;

import com.onproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {

    @Query("select u from Member u where u.id=:id and u.password=:password")
    Member selectUserInfo(@Param("id")String id, @Param("password")String password);
}
