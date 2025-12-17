package io.github.food.stack.domain.menuitem;

import java.util.Optional;

public interface MenuItemGateway {

    MenuItem create(MenuItem menuItem);

    MenuItem update(MenuItem menuItem);

    Optional<MenuItem> findById(MenuItemID id);

    void deleteById(MenuItemID id);
}
