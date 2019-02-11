package com.sam.pose.server;

import com.sam.pose.bean.ItemServiceBO;


public interface ItemService {
    Iterable<ItemServiceBO> findAll();
}
