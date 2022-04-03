package com.query.repository;

import com.query.entity.node.*;
import com.query.repository.jpa.NodeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.query.entity.node.QEntityNode.entityNode;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NodeMasterRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final NodeRepository nodeRepository;

    public EntityNode create(NodeDto nodeDto) {
        return nodeRepository.save(EntityNode.builder().nodeName(nodeDto.getNodeName()).weight(nodeDto.getWeight()).build());
    }

    public void connectNode(Long parent, Long child) {
        EntityNode toParent = jpaQueryFactory.select(entityNode)
                .from(entityNode)
                .where(entityNode.id.eq(parent)).fetchOne();
        EntityNode toChild = findNode(child);
        toChild.setParent(toParent);
        toParent.getChild().add(toChild);
        nodeRepository.save(toParent);
        nodeRepository.save(toChild);
    }

    private EntityNode findNode(Long id) {
        return jpaQueryFactory.query()
                .select(entityNode)
                .from(entityNode)
                .where(entityNode.id.eq(id))
                .fetchOne();
    }

    public NodeVO node(Long id) {
        EntityNode parent = jpaQueryFactory.query().select(entityNode)
                .from(entityNode)
                .leftJoin(entityNode.child)
                .where(entityNode.id.eq(id))
                .fetchJoin()
                .fetchOne();
        return new NodeVO(
                parent.getNodeName(), parent.getWeight(),
                parent.getChild().stream().map(NodeSub::new).collect(Collectors.toList())
        );
    }

    public List<NodeVO> nodeList() {
        List<EntityNode> parents = jpaQueryFactory
                .selectFrom(entityNode)
                .leftJoin(entityNode.child)
                .fetchJoin()
                .fetch();

        return parents.stream().map((p) -> new NodeVO(
                p.getNodeName(), p.getWeight(),
                p.getChild().stream().map(NodeSub::new).collect(Collectors.toList()))
        ).filter(distinctByKey(Node::getNodeName)).distinct().collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}