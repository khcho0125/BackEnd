package com.query.Service;

import com.query.entity.node.*;
import com.query.repository.NodeRepository;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.query.entity.node.QEntityNode.entityNode;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NodeService {

    private final JPAQueryFactory jpaQueryFactory;
    private final NodeRepository nodeRepository;

    public EntityNode create(NodeDto nodeDto) {
        return nodeRepository.save(EntityNode.builder().nodeName(nodeDto.getNodeName()).weight(nodeDto.getWeight()).build());
    }

    public List<NodeVO> nodeList() {
        return jpaQueryFactory.query()
                .select(new QNodeVO(entityNode.id, entityNode.nodeName, entityNode.weight, null))
                .from(entityNode)
                .fetch();
    }

    public void connectNode(Long from, Long to) {
        JPAQuery<Set<EntityNode>> query = jpaQueryFactory.select(entityNode.child)
                .from(entityNode)
                .where(entityNode.id.eq(from));

        Set<EntityNode> nodeSet = query.fetchOne();

        jpaQueryFactory.update(entityNode)
                .where(entityNode.id.eq(from))
                .set(entityNode.child, Expressions.asSimple(nodeSet).as("child")).execute();

    }

    private EntityNode findNode(Long id) {
        return jpaQueryFactory.query()
                .select(entityNode)
                .from(entityNode)
                .where(entityNode.id.eq(id))
                .fetchOne();
    }

    private com.querydsl.jpa.JPQLQuery<NodeSub> getChild() {
        return JPAExpressions.select(new QNodeSub(entityNode.id, entityNode.nodeName, entityNode.weight))
                        .from(entityNode)
                        .where(entityNode.weight.goe(5L));
    }

    private List<NodeSub> getChild2() {
        return jpaQueryFactory.select(new QNodeSub(entityNode.id, entityNode.nodeName, entityNode.weight))
                .from(entityNode)
                .where(entityNode.weight.goe(5L)).fetch();
    }
}
