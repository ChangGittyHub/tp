package trackr.logic.commands;

import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.logic.commands.task.ClearTaskCommand;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.TaskList;
import trackr.model.UserPrefs;

public class ClearTaskCommandTest {

    @Test
    public void execute_emptyTaskList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearTaskCommand(),
                model,
                String.format(ClearTaskCommand.MESSAGE_SUCCESS, ModelEnum.TASK),
                expectedModel);
    }

    @Test
    public void execute_nonEmptyTaskList_success() {
        Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalOrderList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
                getTypicalOrderList(), new UserPrefs());
        expectedModel.setTaskList(new TaskList());

        assertCommandSuccess(new ClearTaskCommand(),
                model,
                String.format(ClearTaskCommand.MESSAGE_SUCCESS, ModelEnum.TASK),
                expectedModel);
    }

}
