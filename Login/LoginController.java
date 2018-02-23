@Controller
@SessionAttributes
public class LoginController {


@RequestMapping(value = "/Login", method = RequestMethod.GET)
public ModelAndView displayLogin(@RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "logout", required = false) String logout,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

    ModelAndView modelForLogin = new ModelAndView();

    if (error != null) {

        // Include login failure message
        modelForLogin.addObject("loginFailure", "Invalid username and password!");

    }

    if ("user".equals(logout)) {

        // Include logout message
        modelForLogin.addObject("msg", "You've been logged out successfully.");
    }

    else {

        modelForLogin.addObject("msg","");
    }


    modelForLogin.setViewName("Login");

    return modelForLogin;

}
