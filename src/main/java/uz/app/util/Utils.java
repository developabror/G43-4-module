package uz.app.util;

public interface Utils {

    String START_TEST = "start test";
    String INFO="info";
    String CONTACT_US="contact us";
    String[][] mainMenu = {
            {INFO, START_TEST},
            {CONTACT_US}
    };

    String FILIALS = "filials";
    String PRODUCTS = "products";
    String MAIN_ASKED = "main asked";
    String WORKING_DAYS = "working days";
    String BACK = "back";
    String WORKING_HOURS = "working hours";
    String[][] info = {
            {FILIALS, PRODUCTS, MAIN_ASKED},
            {WORKING_DAYS, WORKING_HOURS},
            {BACK}
    };


}
