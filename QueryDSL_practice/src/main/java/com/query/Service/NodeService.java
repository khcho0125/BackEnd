package com.query.Service;

import com.query.entity.node.EntityNode;
import com.query.entity.node.NodeDto;
import com.query.entity.node.NodeVO;
import com.query.repository.NodeMasterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class NodeService {

    private final NodeMasterRepository repository;

    public EntityNode createNode(NodeDto nodeDto) {
        return repository.create(nodeDto);
    }

    public NodeVO show(Long id) {
        return repository.node(id);
    }

    public void connect(Long parent, Long child) {
        repository.connectNode(parent, child);
    }

    public List<NodeVO> showList() {
        return repository.nodeList();
    }
}
