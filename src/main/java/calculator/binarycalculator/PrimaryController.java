package calculator.binarycalculator;

import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class PrimaryController {
    @FXML
    private ComboBox<String> selection;

     @FXML
    private TextField number;
     
    @FXML
    private TextField result;
    
    @FXML
    void convert(ActionEvent event) {
        String numberString = number.getText();
        String binaryResult = "";
        
        if(verifyAction().equals("Binary")){
            if(!number.getText().equals("")){
                try {
                    int number = Integer.parseInt(numberString);
                    while(number>0){
                        binaryResult = String.valueOf(number%2) + binaryResult;
                        number /= 2;
                    }
                    result.setText(binaryResult);
                } catch (NumberFormatException e) {
                    result.setText("The integer limit 2,147,483,647");
                }
            }
        }
        
        else if(verifyAction().equals("Decimal")){
            if(!number.getText().equals("")){
                boolean isBinary = true;
                for (int i=0;i<8;i++) {
                    if(numberString.contains(String.valueOf(i+2))){
                        isBinary = false;
                    }
                }
                if(numberString.length()<31){
                    if(isBinary==true){
                        int number = Integer.parseInt(numberString, 2);
                        String decimalResult = String.valueOf(number);
                        result.setText(decimalResult);
                    }
                    else if(isBinary==false){
                        result.setText("The informed number isn't binary.");
                    }
                }
                else{
                    result.setText("The limit for this calculator is 31 bits.");
                }
            }
        }
    }
    
    public String verifyAction(){
        String convertion = selection.getSelectionModel().getSelectedItem();
        return convertion;
    }
    
    @FXML
    public void initialize(){
        selection.setItems(FXCollections.observableArrayList(
            "Binary", "Decimal"
        ));
        selection.setValue("Binary");
        
        UnaryOperator<TextFormatter.Change> filter = change -> {
        String text = change.getText();
        if (text.matches("[0-9]*")) {
                return change;
        }
        return null;
        };

        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        number.setTextFormatter(textFormatter);
    }
}
