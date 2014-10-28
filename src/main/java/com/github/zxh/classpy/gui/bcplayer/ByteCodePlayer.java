package com.github.zxh.classpy.gui.bcplayer;

import com.github.zxh.classpy.classfile.MethodInfo;
import com.github.zxh.classpy.classfile.attribute.AttributeInfo;
import com.github.zxh.classpy.classfile.attribute.CodeAttribute;
import com.github.zxh.classpy.classfile.bytecode.Instruction;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
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
        super.setTitle("Bytecode Player - " + method.getDesc());
        
        BorderPane root = new BorderPane();
        
        
        SplitPane sp = new SplitPane();
        sp.getItems().add(createBytecodeTable());
        sp.getItems().add(new Label("a"));
        sp.getItems().add(new Label("a"));
        root.setCenter(sp);
        
        super.setScene(new Scene(root, 400, 300));
    }
    
    private TableView<Instruction> createBytecodeTable() {
        TableView<Instruction> table = new TableView<>();
        TableColumn<Instruction, String> pcCol = new TableColumn<>("PC");
        //table.getColumns().addAll(pcCol);
        table.getColumns().add(pcCol);
        
        CodeAttribute codeAttr = getCodeAttribute(method);
        if (codeAttr != null) {
            codeAttr.getCode().getSubComponents();
        }
        return table;
    }
    
    private CodeAttribute getCodeAttribute(MethodInfo method) {
        for (AttributeInfo attr : method.getAttributes().getSubComponents()) {
            if (attr instanceof CodeAttribute) {
                return (CodeAttribute) attr;
            }
        }
        return null;
    }
    
    
    
}
