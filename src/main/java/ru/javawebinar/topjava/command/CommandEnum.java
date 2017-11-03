package ru.javawebinar.topjava.command;


public enum CommandEnum {


    ADD_MEAL {
        {
            command = new CreateCommand();
        }
    },
    UPDATE_FORM {
        {
            command = new UpdateFormCommand();
        }
    },

    UPDATE_MEAL {
        {
            command = new UpdateCommand();
        }
    },
    DELETE_MEAL {
        {
            command = new DeleteCommand();
        }
    }
    ;

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
