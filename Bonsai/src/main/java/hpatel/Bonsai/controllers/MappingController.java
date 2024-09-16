// package hpatel.Bonsai.controllers;
//
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
//
/// **
// * Controller class for the URL mappings for CoffeeMaker. The controller
// returns
// * the approprate HTML page in the /src/main/resources/templates folder. For a
// * larger application, this should be split across multiple controllers.
// *
// * @author Harsh Patel
// */
// @Controller
// public class MappingController {
// /**
// * Handles a GET request for the landing page.
// *
// * @param model
// * underlying UI model
// * @return contents of the page
// */
// @GetMapping ( { "/index", "/" } )
// public String index ( final Model model ) {
// return "index";
// }
//
// /**
// * Handles a GET request for the login page.
// *
// * @param model
// * underlying UI model
// * @return contents of the page
// */
// @GetMapping ( { "/login", "/login.html" } )
// public String login ( final Model model ) {
// return "login";
// }
//
// /**
// * Handles a GET request for the manage users page.
// *
// * @param model
// * underlying UI model
// * @return contents of the page
// */
// @GetMapping ( { "/admin/users", "/admin/users.html" } )
// public String users ( final Model model ) {
// return "users";
// }
// }
