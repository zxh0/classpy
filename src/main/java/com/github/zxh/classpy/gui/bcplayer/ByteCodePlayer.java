package com.github.zxh.classpy.gui.bcplayer;

import com.github.zxh.classpy.classfile.MethodInfo;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
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
        super.setTitle("Bytecode Player - " + method.getDesc());
        
        SplitPane sp1 = new SplitPane();
        sp1.setOrientation(Orientation.HORIZONTAL);
        sp1.getItems().add(new LocalVarTable(method));
        sp1.getItems().add(new StackTable(method));
        
        SplitPane sp2 = new SplitPane();
        sp2.setOrientation(Orientation.VERTICAL);
        sp2.getItems().add(new InstructionTable(method));
        sp2.getItems().add(sp1);
        
        BorderPane root = new BorderPane();
        root.setCenter(sp2);
        
        super.setScene(new Scene(root, 600, 400));
    }
    
}
