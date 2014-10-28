package com.github.zxh.classpy.gui.bcplayer;

import com.github.zxh.classpy.classfile.MethodInfo;
import com.github.zxh.classpy.classfile.attribute.CodeAttribute;
import com.github.zxh.classpy.classfile.bytecode.Instruction;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        sp2.getItems().add(createBytecodeTable());
        sp2.getItems().add(sp1);
        
        BorderPane root = new BorderPane();
        root.setCenter(sp2);
        
        super.setScene(new Scene(root, 400, 300));
    }
    
    private TableView<Instruction> createBytecodeTable() {
        TableColumn<Instruction, String> pcCol = new TableColumn<>("PC");
        pcCol.setCellValueFactory(new PropertyValueFactory<>("pc"));
        
        TableColumn<Instruction, String> instCol = new TableColumn<>("Instruction");
        instCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        
        TableView<Instruction> table = new TableView<>();
        table.getColumns().add(pcCol);
        table.getColumns().add(instCol);
        table.setSortPolicy(t -> false); // no sort
        
        CodeAttribute codeAttr = method.findAttribute(CodeAttribute.class);
        if (codeAttr != null) {
            List<Instruction> insts = codeAttr.getCode().getSubComponents();
            table.setItems(FXCollections.observableArrayList(insts));
        }
        
        return table;
    }
    
}
