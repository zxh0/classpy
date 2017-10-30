package com.github.zxh.classpy.gui.parsed;

import com.github.zxh.classpy.helper.font.FontHelper;
import com.github.zxh.classpy.common.FileComponent;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import static com.github.zxh.classpy.gui.parsed.HexText.BYTES_PER_ROW;

public class HexPane extends ScrollPane {

    private final HexText hex;
    private final TextArea textArea1;
    private final TextArea textArea2;
    private final TextArea textArea3;

    private HBox hbox;

    public HexPane(HexText hex) {
        this.hex = hex;
        textArea1 = new TextArea(hex.rowHeaderText);
        textArea2 = new TextArea(hex.bytesText);
        textArea3 = new TextArea(hex.asciiString);

        initTextArea();

        hbox = new HBox();

        hbox.getChildren().addAll(textArea1, textArea2, textArea3);

        setContent(hbox);
    }

    public void select(FileComponent cc) {
        int byteOffset = cc.getOffset();

        int rowIndex = byteOffset / BYTES_PER_ROW;
        int rows = textArea3.getText().length() / (BYTES_PER_ROW + 1);

        textArea2.positionCaret(calcBytesTextPosition(cc.getOffset()));
        textArea2.selectPositionCaret(calcBytesTextPosition(cc.getOffset() + cc.getLength()) - 1);

        textArea3.positionCaret(calcAsciiTextPosition(cc.getOffset()));
        textArea3.selectPositionCaret(calcAsciiTextPosition(cc.getOffset() + cc.getLength()));

        double height = getHeight();
        double textHeight = textArea2.getHeight();

        double vvalue = (((double) rowIndex) / rows * textHeight / (textHeight - height) - height / 2 / textHeight);

        if (((Double) vvalue).isInfinite() || ((Double) vvalue).isNaN()) {

        } else if (vvalue < 0) {
            this.setVvalue(0);
        } else if (vvalue > 1) {
            this.setVvalue(1);
        } else {
            this.setVvalue(vvalue);
        }
    }

    private void initTextArea() {
        textArea1.setFont(FontHelper.textFont);
        textArea2.setFont(FontHelper.textFont);
        textArea3.setFont(FontHelper.textFont);

        textArea1.setPrefColumnCount(6);
        textArea2.setPrefColumnCount(46);
        textArea3.setPrefColumnCount(16);

        int rowCount = hex.rowHeaderText.length() / 9 + 1;
        textArea1.setPrefRowCount(rowCount);
        textArea2.setPrefRowCount(rowCount);
        textArea3.setPrefRowCount(rowCount);

        textArea1.setContextMenu(new AsciiPaneMenu(textArea1));
        textArea2.setContextMenu(new HexPaneMenu(textArea2));
        textArea3.setContextMenu(new AsciiPaneMenu(textArea3));

        textArea1.setEditable(false);
        textArea2.setEditable(false);
        textArea3.setEditable(false);

        textArea1.setStyle("-fx-text-fill: grey;");
    }

    private int calcBytesTextPosition(int byteOffset) {
        int rowIndex = byteOffset / BYTES_PER_ROW;
        int colIndex = byteOffset % BYTES_PER_ROW;

        return (49 * rowIndex) + (colIndex * 3);
    }

    private int calcAsciiTextPosition(int byteOffset) {
        int rowIndex = byteOffset / BYTES_PER_ROW;
        int colIndex = byteOffset % BYTES_PER_ROW;

        return (17 * rowIndex) + (colIndex);
    }
}
