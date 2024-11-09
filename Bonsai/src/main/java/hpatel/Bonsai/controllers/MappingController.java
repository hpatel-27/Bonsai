package hpatel.Bonsai.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for the URL mappings for Bonsai. The controller returns the
 * approprate HTML page in the /src/main/resources/templates folder. For a
 * larger application, this should be split across multiple controllers.
 *
 * @author Harsh Patel
 */
@Controller
public class MappingController {

    /**
     * Handles a GET request for the landing page.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/index", "/" } )
    public String index ( final Model model ) {
        return "index";
    }

    /**
     * Handles a GET request for the login page.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/login", "/login.html" } )
    public String login ( final Model model ) {
        return "login";
    }

    /**
     * Handles a GET request for the recipes page.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/staff/recipes", "/staff/recipes.html" } )
    public String recipes ( final Model model ) {
        return "recipes";
    }

    /**
     * Handles a GET request for the add recipe page.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/staff/add-recipe", "/staff/add-recipe.html" } )
    public String addRecipe ( final Model model ) {
        return "add-recipe";
    }

    /**
     * Handles a GET request for the edit recipe page
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/staff/edit-recipe", "/staff/edit-recipe.html" } )
    public String editRecipe ( final Model model ) {
        return "edit-recipe";
    }

    /**
     * Handles a GET request for the add ingredient page.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/admin/add-ingredients", "/admin/add-ingredients.html" } )
    public String addIngredients ( final Model model ) {
        return "add-ingredients";
    }

    /**
     * Handles a GET request for the inventory page.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/admin/inventory", "/admin/inventory.html" } )
    public String inventory ( final Model model ) {
        return "inventory";
    }

    /**
     * Handles a GET request for the place order page.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/place-order", "/place-order.html" } )
    public String placeOrder ( final Model model ) {
        return "place-order";
    }

    /**
     * Handles a GET request for the order history page.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/order-history", "/order-history.html" } )
    public String orderHistory ( final Model model ) {
        return "order-history";
    }

    /**
     * Handles a GET request for the manage users page.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/admin/users", "/admin/users.html" } )
    public String users ( final Model model ) {
        return "users";
    }

    /**
     * Handles a GET request for the add barista page.
     *
     * @param model
     *            underlying UI model
     * @return contents of the page
     */
    @GetMapping ( { "/admin/add-barista", "/admin/add-barista.html" } )
    public String addBarista ( final Model model ) {
        return "add-barista";
    }

}
