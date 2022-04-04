package com.query.entity.node;

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
