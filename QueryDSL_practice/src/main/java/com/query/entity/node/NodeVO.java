package com.query.entity.node;

import lombok.Getter;
import java.util.List;

@Getter
public class NodeVO extends Node {

    private final List<NodeSub> child;

    public NodeVO(String name, Long weight, List<NodeSub> child) {
        super(name, weight);
        this.child = child;
    }
}
