package io.github.food.stack.domain.menuitem;

public interface MenuItemGateway {

    MenuItem create(MenuItem menuItem);

    MenuItem update(MenuItem menuItem);

    MenuItem findById(MenuItemID id);

    void deleteById(MenuItemID id);
}
