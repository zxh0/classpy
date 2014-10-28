package com.github.zxh.classpy.gui.bcplayer;

import com.github.zxh.classpy.classfile.MethodInfo;
import com.github.zxh.classpy.classfile.attribute.CodeAttribute;
import com.github.zxh.classpy.classfile.bytecode.Instruction;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author zxh
 */
public class InstructionTable extends TableView<Instruction> {

    public InstructionTable(MethodInfo method) {
        TableColumn<Instruction, String> pcCol = new TableColumn<>("PC");
        pcCol.setCellValueFactory(new PropertyValueFactory<>("pc"));
        
        TableColumn<Instruction, String> instCol = new TableColumn<>("Instruction");
        instCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        
        super.getColumns().add(pcCol);
        super.getColumns().add(instCol);
        super.setSortPolicy(t -> false); // no sort
        
        CodeAttribute codeAttr = method.findAttribute(CodeAttribute.class);
        if (codeAttr != null) {
            List<Instruction> insts = codeAttr.getCode().getSubComponents();
            super.setItems(FXCollections.observableArrayList(insts));
        }
    }
    
}
