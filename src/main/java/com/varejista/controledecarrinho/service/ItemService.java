package com.varejista.controledecarrinho.service;

import com.varejista.controledecarrinho.exception.ObjectNotFoundException;
import com.varejista.controledecarrinho.models.Item;
import com.varejista.controledecarrinho.models.Item;
import com.varejista.controledecarrinho.respository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Integer id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElseThrow(() ->
                new ObjectNotFoundException("Item \"%s\" não encontrado")
        );
    }

    public Item insert(Item item) {
        return itemRepository.save(item);
    }

    public void delete(Integer id) {
        Item item = findById(id);
        itemRepository.delete(item);
    }

    /*public Item update(Item item) {
        Item oldItem = findById(item.getId());
        item.setId(oldItem.getId());
        return itemRepository.save(item);
    }*/
}
