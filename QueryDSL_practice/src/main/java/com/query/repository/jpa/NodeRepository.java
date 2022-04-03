package com.query.repository.jpa;

import com.query.entity.node.EntityNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends JpaRepository<EntityNode, Long> {
}
