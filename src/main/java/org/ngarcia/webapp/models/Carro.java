package org.ngarcia.webapp.models;

import java.util.*;

public class Carro {

    private List<ItemCarro> items;

    public Carro() {
        items = new ArrayList<>();
    }

    public void addItem(ItemCarro item) {

        if(items.contains(item)) {
            Optional<ItemCarro> optionalItem = items.stream()
                    .filter(i -> i.equals(item))
                    .findAny();

            ItemCarro i = optionalItem.get();
            i.setCantidad(i.getCantidad()+1);
        }
        else {
            items.add(item);
        }
    }
    
    public void updateItem(ItemCarro item) {
        if(items.contains(item)) {
            Optional<ItemCarro> optionalItem = items.stream()
                    .filter(i -> i.equals(item))
                    .findAny();

            ItemCarro i = optionalItem.get();
            i.setCantidad(item.getCantidad());
        }
    }
    
    public void deleteItem(Long id) {
        //items.remove(id);
        for( ItemCarro item : items) {
            if(item.getProducto().getId().equals(id)) {
                items.remove(item);
                break;
            }
        }
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public int getTotal() {
        return items.stream().mapToInt( i -> i.getImporte()).sum();
    }
}
