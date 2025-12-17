package io.github.food.stack.application.menuitem.update;

import io.github.food.stack.application.CommandHandler;
import io.github.food.stack.domain.control.Result;
import io.github.food.stack.domain.exception.DomainException;
import io.github.food.stack.domain.exception.NotFoundException;
import io.github.food.stack.domain.menuitem.MenuItem;
import io.github.food.stack.domain.menuitem.MenuItemGateway;
import io.github.food.stack.domain.menuitem.MenuItemID;
import io.github.food.stack.domain.validation.handler.Notification;

import java.util.Objects;

public class UpdateMenuItemUseCase implements CommandHandler<UpdateMenuItemCommand, UpdateMenuItemOutput> {

    private final MenuItemGateway menuItemGateway;

    public UpdateMenuItemUseCase(final MenuItemGateway menuItemGateway) {
        this.menuItemGateway = Objects.requireNonNull(menuItemGateway);
    }

    @Override
    public Result<UpdateMenuItemOutput> handle(final UpdateMenuItemCommand command) {
        final var id = MenuItemID.from(command.id());

        final var maybeMenuItem = this.menuItemGateway.findById(id);
        if (maybeMenuItem.isEmpty()) {
            return Result.failure(NotFoundException.with(MenuItem.class, id));
        }

        final var menuItem = maybeMenuItem.get();
        final var notification = Notification.create();

        menuItem.update(
            command.name(),
            command.description(),
            command.price(),
            command.imageUrl(),
            command.available()
        );

        menuItem.validate(notification);

        if (notification.hasError()) {
            return Result.failure(DomainException.with(notification.getErrors()));
        }

        final var updatedMenuItem = this.menuItemGateway.update(menuItem);

        return Result.success(UpdateMenuItemOutput.from(updatedMenuItem));
    }
}
