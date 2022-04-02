package com.query.controller;

import com.query.Service.NodeService;
import com.query.entity.node.NodeVO;
import com.query.entity.node.EntityNode;
import com.query.entity.node.NodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NodeController {

    private final NodeService nodeService;

    @GetMapping("/list/v3")
    public List<NodeVO> getNodeList() {
        return nodeService.nodeList();
    }

    @PostMapping("/node")
    public EntityNode create(@RequestBody NodeDto nodeDto) {
        return nodeService.create(nodeDto);
    }

    @PostMapping("/connect")
    public void connect(@RequestParam Long from, @RequestParam Long to) {
        nodeService.connectNode(from, to);
    }
}
