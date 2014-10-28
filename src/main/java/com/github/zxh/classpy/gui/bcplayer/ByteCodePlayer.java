package com.github.zxh.classpy.gui.bcplayer;

import com.github.zxh.classpy.classfile.MethodInfo;
import com.github.zxh.classpy.classfile.attribute.CodeAttribute;
import com.github.zxh.classpy.classfile.attribute.LocalVariableTableAttribute;
import com.github.zxh.classpy.classfile.attribute.LocalVariableTableAttribute.LocalVariableTableEntry;
import com.github.zxh.classpy.classfile.bytecode.Instruction;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        
        BorderPane root = new BorderPane();
        
        
        SplitPane sp = new SplitPane();
        sp.getItems().add(createBytecodeTable());
        sp.getItems().add(createLocalVarTable());
        //sp.getItems().add(new Label("a"));
        root.setCenter(sp);
        
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
    
    private TableView<?> createLocalVarTable() {
        TableColumn<LocalVariableTableEntry, String> slotCol = new TableColumn<>("Slot");
        slotCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        
        TableColumn<LocalVariableTableEntry, String> valCol = new TableColumn<>("Value");
        //instCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        
        TableView<LocalVariableTableEntry> table = new TableView<>();
        table.getColumns().add(slotCol);
        table.getColumns().add(valCol);
        table.setSortPolicy(t -> false); // no sort
        
        LocalVariableTableAttribute localVarTableAttr = method.findAttribute(LocalVariableTableAttribute.class);
        if (localVarTableAttr != null) {
            List<LocalVariableTableEntry> vars = localVarTableAttr.getLocalVariableTable().getSubComponents();
            table.setItems(FXCollections.observableArrayList(vars));
        }
        
        return table;
    }
    
}
