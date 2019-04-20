import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calculator extends Application {
    private Input input = new Input();    

    private Scene scene;
    private GridPane pane = new GridPane();
    private GridPane monitor = new GridPane();
    private GridPane keypad = new GridPane();

    private Label programmer = new Label("≡   Programmer");
    private Label displayExpression = new Label();
    private Label displayCurrentNumber = new Label("0");
    private Label hex = new Label("Hex");
    private Label dec = new Label("Dec");
    private Label oct = new Label("Oct");
    private Label bin = new Label("Bin");
    private Label displayHex = new Label("0");
    private Label displayDec = new Label("0");
    private Label displayOct = new Label("0");
    private Label displayBin = new Label("0");

    private String[][] buttonContents = {
        {"⍌", "⍞", "WORD", "", "MS", "M"},
        {"Lsh", "Rsh", "Or", "Xor", "Not", "And"},
        {"↑", "Mod", "CE", "C", "⌫", "÷"},
        {"A", "B", "7", "8", "9", "×"},     
        {"C", "D", "4", "5", "6", "-"},
        {"E", "F", "1", "2", "3", "+"}, 
        {"(", ")", "±", "0", ".", "="}
    };
    private Button[][] buttons = new Button[buttonContents.length][buttonContents[0].length];

    private void refresh(boolean scale) {
        String expression = input.getExpression();
        displayExpression.setText(expression);
        
        if(!scale)
            return;

        int currentNumber = input.getOperand();
        displayCurrentNumber.setText(currentNumber + "");

        String hex = Integer.toHexString(currentNumber);
        String dec = Integer.toString(currentNumber);
        String oct = Integer.toOctalString(currentNumber);
        String bin = Integer.toBinaryString(currentNumber);

        displayHex.setText(hex);
        displayDec.setText(dec);
        displayOct.setText(oct);
        displayBin.setText(bin);
    }

    private EventHandler<ActionEvent> getButtonHandler(String content) {
        EventHandler<ActionEvent> handler;

        switch(content) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                handler = (ActionEvent e) -> {input.setOperand(content);};
                break;
            case "+":
            case "-":
            case "×":
            case "÷":
            case "Mod":
            case "Or":
            case "Xor":
            case "And":
            case "Lsh":
            case "Rsh":
            case "(":
            case ")":
                handler = (ActionEvent e) -> input.appendOperator(content);;
                break;
            case "⌫":
                handler = (ActionEvent e) -> input.backspace();;
                break;
            case "Not":
                handler = (ActionEvent e) -> input.not();;
                break;
            case "C":
                handler = (ActionEvent e) -> input.clear();;
                break;
            case "CE":
                handler = (ActionEvent e) -> input.clearEntry();
                break;
            case "±":
                handler = (ActionEvent e) -> input.switchSign();
                break;
            case "=":
                handler = (ActionEvent e) -> input.getResult();
                break;
            default:
                handler = (ActionEvent e) -> { };
                break;
        }

        return handler;
    }

    @Override
    public void start(Stage primaryStage) {
        programmer.getStyleClass().add("programmer");
        programmer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        displayExpression.getStyleClass().add("display-expression");
        displayCurrentNumber.getStyleClass().add("display-current-number");
        displayExpression.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        displayCurrentNumber.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        hex.getStyleClass().add("scale");
        dec.getStyleClass().add("scale");
        oct.getStyleClass().add("scale");
        bin.getStyleClass().add("scale");
        hex.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        dec.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        oct.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        bin.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        displayHex.getStyleClass().add("display-scale");
        displayDec.getStyleClass().add("display-scale");
        displayOct.getStyleClass().add("display-scale");
        displayBin.getStyleClass().add("display-scale");
        displayHex.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        displayDec.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        displayOct.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        displayBin.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        RowConstraints mro1 = new RowConstraints();
        RowConstraints mro2 = new RowConstraints();
        RowConstraints mro3 = new RowConstraints();
        RowConstraints mro4 = new RowConstraints();
        RowConstraints mro5 = new RowConstraints();
        RowConstraints mro6 = new RowConstraints();
        RowConstraints mro7 = new RowConstraints();
        mro1.setPercentHeight(3000.0 / 170);
        mro2.setPercentHeight(3000.0 / 170);
        mro3.setPercentHeight(3000.0 / 170);
        mro4.setPercentHeight(2000.0 / 170);
        mro5.setPercentHeight(2000.0 / 170);
        mro6.setPercentHeight(2000.0 / 170);
        mro7.setPercentHeight(2000.0 / 170);
        ColumnConstraints mco1 = new ColumnConstraints();
        ColumnConstraints mco2 = new ColumnConstraints();
        mco1.setPercentWidth(15);
        mco2.setPercentWidth(85);

        monitor.add(programmer, 0, 0, 2, 1);
        monitor.add(displayExpression, 1, 1, 2, 1);
        monitor.add(displayCurrentNumber, 1, 2, 2, 1);
        monitor.add(hex, 0, 3);
        monitor.add(dec, 0, 4);
        monitor.add(oct, 0, 5);
        monitor.add(bin, 0, 6);
        monitor.add(displayHex, 1, 3);
        monitor.add(displayDec, 1, 4);
        monitor.add(displayOct, 1, 5);
        monitor.add(displayBin, 1, 6);
        monitor.getRowConstraints().addAll(mro1, mro2, mro3, mro4, mro5, mro6, mro7);
        monitor.getColumnConstraints().addAll(mco1, mco2);
        monitor.setPrefSize(360, 170);
        monitor.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        monitor.setAlignment(Pos.BOTTOM_RIGHT);
        monitor.getStyleClass().add("monitor");

        for(int i = 0; i < buttonContents.length; i++) {
            for(int j = 0; j < buttonContents[i].length; j++) {
                String buttonContent = buttonContents[i][j];
                if(buttonContent.equals(""))
                    continue;
                
                Button button = new Button(buttonContent);
                button.setId(buttonContent);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setOnAction(getButtonHandler(buttonContent));

                if(buttonContent.equals("=") ||
                        buttonContent.equals("⌫") ||
                        buttonContent.equals("Not") ||
                        buttonContent.equals("CE") ||
                        buttonContent.equals("C") ||
                        Character.isDigit(buttonContent.charAt(0)))
                    button.armedProperty().addListener(ov -> refresh(true));
                else 
                    button.armedProperty().addListener(ov -> refresh(false));

                button.setOnMouseEntered(e -> {button.getStyleClass().add("enter-button");});
                button.setOnMouseExited(e -> {button.getStyleClass().remove("enter-button");});
                buttons[i][j] = button;

                button.getStyleClass().add("button");
                if(i <= 1)
                    button.getStyleClass().add("light-button");
                else if((3 <= i && i <= 5 && 0 <= j && j <= 1) || buttonContent.equals("."))
                    button.getStyleClass().add("unusable-button");
                else if(Character.isDigit(buttonContent.charAt(0)))
                    button.getStyleClass().add("bold-button");

                if(i == 0 && j == 2) {
                    button.getStyleClass().add("span-button");
                    keypad.add(button, j, i, 2, 1);
                }
                else
                    keypad.add(button, j, i);
            }
        }

        for(int i = 0; i < buttonContents.length; i++) {
            RowConstraints ro = new RowConstraints();
            
            if(i <= 1) 
                ro.setPercentHeight(100.0 / buttonContents.length - (buttonContents.length - 2) / 2);
            else
                ro.setPercentHeight(100.0 / buttonContents.length + 1);
            
            keypad.getRowConstraints().add(i, ro);
        }

        for(int i = 0; i < buttonContents[0].length; i++) {
            ColumnConstraints co = new ColumnConstraints();
            co.setPercentWidth(100.0 / buttonContents[0].length);
            keypad.getColumnConstraints().add(i, co);
        }

        keypad.getStyleClass().add("keypad");
        keypad.setPrefSize(360, 300);
        keypad.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        RowConstraints pro1 = new RowConstraints();
        RowConstraints pro2 = new RowConstraints();
        pro1.setPercentHeight(19600.0 / 596);
        pro2.setPercentHeight(40000.0 / 596);
        ColumnConstraints pco = new ColumnConstraints();
        pco.setPercentWidth(100);
        pane.getRowConstraints().addAll(pro1, pro2);
        pane.getColumnConstraints().add(pco);

        pane.add(monitor, 0, 0);
        pane.add(keypad, 0, 1);
        pane.setPrefSize(360, 596);
        pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        scene = new Scene(pane);
        scene.setOnKeyTyped(e -> {
            switch(e.getCharacter()) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    input.setOperand(e.getCharacter());
                    refresh(true);
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "|":
                case "^":
                case "&":
                case "(":
                case ")":
                    input.appendOperator(e.getCharacter());
                    refresh(false);
                    break;
                case "=":
                    input.getResult();
                    refresh(true);
                    break;
            }
        });

        scene.setOnKeyPressed(e -> {
            switch(e.getCode()) {
                case BACK_SPACE:
                    input.backspace();
                    refresh(true);
                    break;
                case ENTER:
                    input.getResult();
                    refresh(true);
                    break;
            }
        });

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

