package trackr.logic.commands;

import static java.util.Objects.requireNonNull;
import static trackr.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.item.Item;

/**
 * Lists all items in the item list to the user.
 */
public abstract class ListItemCommand<T extends Item> extends Command {

    public static final String MESSAGE_SUCCESS = "Listed all %ss";

    private final ModelEnum modelEnum;

    /**
     * Creates an ListItemCommand to list all the items.
     */
    public ListItemCommand(ModelEnum modelEnum) {
        requireNonNull(modelEnum);
        this.modelEnum = modelEnum;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS, modelEnum);
        return new CommandResult(String.format(MESSAGE_SUCCESS, modelEnum.toString().toLowerCase()));
    }
}