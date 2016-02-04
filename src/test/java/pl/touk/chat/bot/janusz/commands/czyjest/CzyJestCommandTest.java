package pl.touk.chat.bot.janusz.commands.czyjest;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CzyJestCommandTest {

    public static final String NICKNAME_UNKNOWN_TO_COMMAND = "kot";

    @Test
    public void shouldRespondWithErrorMessage() {
        // when
        String result = new CzyJestCommand().invoke("sender", ImmutableList.of(NICKNAME_UNKNOWN_TO_COMMAND));

        // then
        assertThat(result).isEqualTo(CzyJestCommand.ERROR_MESSAGE);
    }
}