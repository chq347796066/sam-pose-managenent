package com.sam.pose.server.impl;

import com.sam.pose.bean.ItemServiceBO;
import com.sam.pose.dao.ItemServiceRepository;
import com.sam.pose.server.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemServiceRepository itemServiceRepository;
    @Override
    public Iterable<ItemServiceBO> findAll() {
        return itemServiceRepository.findAll();
    }
}
