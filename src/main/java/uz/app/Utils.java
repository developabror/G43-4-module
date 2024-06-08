package uz.app;

public interface Utils {

    String INFO = "info";
    String SEND_NUMBER="send number";
    String[][] mainMenu = {
            {"contact us", INFO},
            {SEND_NUMBER}
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
