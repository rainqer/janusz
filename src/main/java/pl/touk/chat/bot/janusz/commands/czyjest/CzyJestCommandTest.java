package pl.touk.chat.bot.janusz.commands.czyjest;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

public class CzyJestCommandTest {

    @Test
    public void asksStack() {
        CzyJestCommand command = new CzyJestCommand();
        command.invoke("sender", ImmutableList.of("kot"));
    }
}