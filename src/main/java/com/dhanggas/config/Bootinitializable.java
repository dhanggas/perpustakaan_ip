
package com.dhanggas.config;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContextAware;

public interface Bootinitializable extends Initializable, ApplicationContextAware{
    
    public void initConstruct();
       
    public void stage(Stage primaryStage);
    
    public Node initView();
}
