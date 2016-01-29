package pl.touk.chat.bot.janusz.commands.czyjest;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.touk.chat.bot.janusz.commands.JanuszCommand;

import java.util.Arrays;
import java.util.List;

public class CzyJestCommand implements JanuszCommand {

    public static final String CZYJEST_API_PATH = "http://czyjestkuba.touk.pl/api/";
    public static final String ERROR_MESSAGE = "nie wiem";
    private static final List<String> AVAILABLE_NICKNAMES = Arrays.asList("kuba");

    Logger log = LoggerFactory.getLogger(CzyJestCommand.class);

    @Override
    public String invoke(String sender, List<String> args) {
        if (!args.isEmpty()) {
            try {
                return askCzyJest(args.get(0));
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }
        return ERROR_MESSAGE;
    }

    private String askCzyJest(String arg) throws UnirestException {
        if (AVAILABLE_NICKNAMES.contains(arg)) {
            try {
                return czyJest(arg);
            } catch (UnirestException e) {
                log.error("Ups something gone wrong {}", e.getMessage());
                e.printStackTrace();
                throw e;
            }
        }
        return ERROR_MESSAGE;
    }

    private String czyJest(String arg) throws UnirestException {
        HttpRequest request = Unirest.get(CZYJEST_API_PATH + arg);

        log.info("Asking for czy jest");
        String response = request.asString().getBody().trim();
        log.info("Stack response: {}", response);

        switch (Integer.valueOf(response)) {
            case 3 :
            case 2 : return "jest";
            case 1 : return "raczej nie ma";
            case 0 :
            default: return "nie ma";
        }
    }
}
