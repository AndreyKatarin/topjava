package ru.javawebinar.topjava.command;


public enum CommandEnum {

    CREATE {
        {
            command = new CreateCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
