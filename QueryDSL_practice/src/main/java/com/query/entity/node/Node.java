package com.query.entity.node;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class Node {

    String nodeName;
    Long weight;

    public Node(String nodeName, Long weight) {
        this.nodeName = nodeName;
        this.weight = weight;
    }

    public Node(Long weight, String nodeName) {
        this.nodeName = nodeName;
        this.weight = weight;
    }
}
