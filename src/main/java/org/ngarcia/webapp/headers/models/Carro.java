package org.ngarcia.webapp.headers.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<ItemCarro> getItems() {
        return items;
    }

    public int getTotal() {
        return items.stream().mapToInt( i -> i.getImporte()).sum();
    }
}
