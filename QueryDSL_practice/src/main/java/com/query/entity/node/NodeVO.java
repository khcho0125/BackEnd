package com.query.entity.node;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import java.util.List;

@Getter
public class NodeVO extends Node {

    private final Long id;
    private final List<NodeSub> child;

    @QueryProjection
    public NodeVO(Long id, String name, Long weight, List<NodeSub> child) {
        super(name, weight);
        this.id = id;
        this.child = child;
    }
}
