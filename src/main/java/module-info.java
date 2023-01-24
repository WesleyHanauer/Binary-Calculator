module calculator.binarycalculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens calculator.binarycalculator to javafx.fxml;
    exports calculator.binarycalculator;
}
