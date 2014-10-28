package com.github.zxh.classpy.gui.bcplayer;

import com.github.zxh.classpy.classfile.MethodInfo;
import com.github.zxh.classpy.classfile.attribute.CodeAttribute;
import com.github.zxh.classpy.classfile.attribute.LocalVariableTableAttribute;
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
public class LocalVarTable extends TableView<LocalVar> {

    public LocalVarTable(MethodInfo method) {
        TableColumn<LocalVar, String> slotCol = new TableColumn<>("Slot");
        slotCol.setCellValueFactory(new PropertyValueFactory<>("slot"));
        
        TableColumn<LocalVar, String> valCol = new TableColumn<>("Value");
        //valCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getDesc()));
        
        super.getColumns().add(slotCol);
        super.getColumns().add(valCol);
        super.setSortPolicy(t -> false); // no sort
        
        CodeAttribute codeAttr = method.findAttribute(CodeAttribute.class);
        if (codeAttr != null) {
            LocalVariableTableAttribute localVarTableAttr = codeAttr.findAttribute(LocalVariableTableAttribute.class);
            if (localVarTableAttr != null) {
                int maxLocals = codeAttr.getMaxLocals().getValue();
                super.setItems(createVars(maxLocals));
            }
        }
    }
    
    private static ObservableList<LocalVar> createVars(int maxLocals) {
        List<LocalVar> vars = IntStream.range(0, maxLocals)
                .mapToObj(i -> new LocalVar(i))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(vars);
    }
    
}
