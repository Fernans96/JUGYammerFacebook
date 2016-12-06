package Hello;

import Common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by quent on 03/12/2016.
 */
@RestController
@RequestMapping("/Yammer")
public class YammerController {
    Yammer.Client client = new Yammer.Client();

    @RequestMapping("/Auth")
    public Result AuthYammer(@RequestParam(value = "code") String code) {
        if (client.AuthClient(code) == true) {
            System.out.println(client.message.getReceived(null, null, null, 1));
            return new Result(true, "Successful Authentication");
        } else {
            return new Result(false, "Bad Authentication Code");
        }
    }
}
