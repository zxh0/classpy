package com.github.zxh.classpy.gui.bcplayer;

import com.github.zxh.classpy.classfile.MethodInfo;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author zxh
 */
public class ByteCodePlayer extends Stage {
    
    private final MethodInfo method;

    public ByteCodePlayer(MethodInfo method) {
        this.method = method;
        super.setTitle("Bytecode Player");
        
        BorderPane root = new BorderPane();
        
        
        SplitPane sp = new SplitPane();
        sp.getItems().add(new Label("a"));
        sp.getItems().add(new Label("a"));
        sp.getItems().add(new Label("a"));
        root.setCenter(sp);
        
        super.setScene(new Scene(root, 400, 300));
    }
    
//    private TableView<?> createBytecodeTable(method.) {
//        
//    }
    
    
    
}
