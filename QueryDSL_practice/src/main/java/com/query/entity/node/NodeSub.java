package com.query.entity.node;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class NodeSub extends Node {

    public NodeSub(String name, Long weight) {
        super(name, weight);
    }

    public NodeSub(EntityNode entityNode) {
        super(entityNode.getNodeName(), entityNode.getWeight());
    }
}
