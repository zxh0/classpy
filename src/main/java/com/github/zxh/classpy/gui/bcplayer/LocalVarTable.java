package com.github.zxh.classpy.gui.bcplayer;

import com.github.zxh.classpy.classfile.MethodInfo;
import com.github.zxh.classpy.classfile.attribute.CodeAttribute;
import com.github.zxh.classpy.classfile.attribute.LocalVariableTableAttribute;
import com.github.zxh.classpy.classfile.attribute.LocalVariableTableAttribute.LocalVariableTableEntry;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author zxh
 */
public class LocalVarTable extends TableView<VarSlot> {

    private List<LocalVariableTableEntry> vars;
    
    public LocalVarTable(MethodInfo method) {
        TableColumn<VarSlot, String> slotCol = new TableColumn<>("Slot");
        slotCol.setCellValueFactory(new PropertyValueFactory<>("slot"));
        slotCol.setMinWidth(64);
        
        TableColumn<VarSlot, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setMinWidth(64);
        
        TableColumn<VarSlot, String> valCol = new TableColumn<>("Value");
        valCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        valCol.setMinWidth(64);
        
        super.getColumns().add(slotCol);
        super.getColumns().add(nameCol);
        super.getColumns().add(valCol);
        super.setSortPolicy(t -> false); // no sort
        
        CodeAttribute codeAttr = method.findAttribute(CodeAttribute.class);
        if (codeAttr != null) {
            int maxLocals = codeAttr.getMaxLocals().getValue();
            super.setItems(createVars(maxLocals));
            LocalVariableTableAttribute varTableAttr = codeAttr.findAttribute(LocalVariableTableAttribute.class);
            if (varTableAttr != null) {
                vars = varTableAttr.getLocalVariableTable().getSubComponents();
            }
        }
        
        setVarNames(super.getItems(), 0);
    }
    
    private static ObservableList<VarSlot> createVars(int maxLocals) {
        List<VarSlot> vars = IntStream.range(0, maxLocals)
                .mapToObj(i -> new VarSlot(i))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(vars);
    }
    
    private void setVarNames(ObservableList<VarSlot> slots, int pc) {
        if (vars != null) {
            vars.stream().forEach((var) -> {
                int startPc = var.getStartPc().getValue();
                int endPc = startPc + var.length().getValue() - 1;
                if (pc >= startPc && pc <= endPc) {
                    slots.get(var.getIndex().getValue()).setName(var.getDesc());
                }
            });
        }
    }
    
}
