package io.github.food.stack.application.menuitem.retrieve;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.menuitem.MenuItem;
import io.github.food.stack.domain.menuitem.MenuItemGateway;
import io.github.food.stack.domain.menuitem.MenuItemID;

import java.util.Objects;

public class GetMenuItemByIdUseCaseImpl implements GetMenuItemByIdUseCase {

    private final MenuItemGateway menuItemGateway;

    public GetMenuItemByIdUseCaseImpl(final MenuItemGateway menuItemGateway) {
        this.menuItemGateway = Objects.requireNonNull(menuItemGateway);
    }

    @Override
    public Result<MenuItemOutput> execute(final GetMenuItemByIdQuery query) {
        final var id = MenuItemID.from(query.id());

        return this.menuItemGateway.findById(id)
                .map(MenuItemOutput::from)
                .map(Result::success)
                .orElseGet(() -> Result.failure(NotFoundException.with(MenuItem.class, id)));
    }
}
