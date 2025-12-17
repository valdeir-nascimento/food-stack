package io.github.food.stack.application.menuitem.delete;

import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.menuitem.MenuItemGateway;
import io.github.food.stack.domain.menuitem.MenuItemID;

import java.util.Objects;

public class DeleteMenuItemUseCaseImpl implements DeleteMenuItemUseCase {

    private final MenuItemGateway menuItemGateway;

    public DeleteMenuItemUseCaseImpl(final MenuItemGateway menuItemGateway) {
        this.menuItemGateway = Objects.requireNonNull(menuItemGateway);
    }

    @Override
    public Result<Void> execute(final DeleteMenuItemCommand command) {
        this.menuItemGateway.deleteById(MenuItemID.from(command.id()));
        return Result.success(null);
    }
}
