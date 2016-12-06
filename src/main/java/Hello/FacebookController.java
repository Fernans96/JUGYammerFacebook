package Hello;

import Common.Result;
import Facebook.Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by quent on 03/12/2016.
 */
@RestController
@RequestMapping("/Facebook")
public class FacebookController {
    Client client = new Facebook.Client();

    @RequestMapping("/Auth")
    public Result AuthFacebook(@RequestParam(value = "code") String code) {
        if (client.AuthClient(code) == true) {
            return new Result(true, "Successful Authentication");
        } else {
            return new Result(false, "Bad Authentication Code");
        }
    }
}
