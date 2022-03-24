package com.zmeodz;

import Controller.LoginController;
import javafx.application.Application;



public class GetLink {

    

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        launch(args);
        Application.launch(LoginController.class);

    }
}
