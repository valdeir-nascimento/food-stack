package io.github.food.stack.application.menuitem.create;

import io.github.food.stack.application.CommandHandler;
import io.github.food.stack.domain.category.CategoryID;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.menuitem.MenuItem;
import io.github.food.stack.domain.menuitem.MenuItemGateway;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class CreateMenuItemUseCase implements CommandHandler<CreateMenuItemCommand, CreateMenuItemOutput> {

    private final MenuItemGateway menuItemGateway;

    public CreateMenuItemUseCase(final MenuItemGateway menuItemGateway) {
        this.menuItemGateway = Objects.requireNonNull(menuItemGateway);
    }

    @Override
    public Result<CreateMenuItemOutput> handle(final CreateMenuItemCommand command) {
        final var notification = Notification.create();

        final var menuItem = MenuItem.newMenuItem(
            command.name(),
            command.description(),
            command.price(),
            command.imageUrl(),
            command.available(),
            CategoryID.from(command.categoryId())
        );

        menuItem.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var savedMenuItem = this.menuItemGateway.create(menuItem);

        return Result.success(CreateMenuItemOutput.from(savedMenuItem));
    }
}
