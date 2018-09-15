import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    private StringBuilder builder = new StringBuilder();
    private TextField textField;
    private double CurrentNum;
    private double secondNum;
    private int Operator;
    private boolean computed;
    private Label display;

    @Override
    public void start(Stage primaryStage) {

        VBox vBox = new VBox();
        GridPane btGrid = new GridPane();

        display = new Label("0");
        display.setPadding(new Insets(0,10,0,10));
        display.minWidthProperty().bind(vBox.widthProperty());
        display.minHeightProperty().bind(vBox.heightProperty().divide(7));
        display.setFont(Font.font("Tahoma",28));
        display.setAlignment(Pos.CENTER_RIGHT);

        textField = new TextField("0");
        textField.setPadding(new Insets(0,10,0,10));
        textField.minWidthProperty().bind(vBox.widthProperty());
        textField.minHeightProperty().bind(vBox.heightProperty().divide(7));
        textField.setFont(Font.font("Tahoma",28));
        textField.setAlignment(Pos.CENTER_RIGHT);
        
        Button bt1 = new Button("1");
        Button bt2 = new Button("2");
        Button bt3 = new Button("3");
        Button bt4 = new Button("4");
        Button bt5 = new Button("5");
        Button bt6 = new Button("6");
        Button bt7 = new Button("7");
        Button bt8 = new Button("8");
        Button bt9 = new Button("9");
        Button bt0 = new Button("0");
        Button btPlus = new Button("+");
        Button btMinus = new Button("-");
        Button btMultiply = new Button("×");
        Button btDivide = new Button("÷");
        Button btRoot = new Button("√");
        Button btBack = new Button("←");
        Button btClear = new Button("C");
        Button btDot = new Button(".");
        Button btNega = new Button("±");
        Button btEqual = new Button("=");

        Button[] btList = {bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btPlus, btMinus, btMultiply, btDivide, btRoot, btBack, btClear, btDot, btNega, btEqual};
        for (Button bt: btList) {
            bt.minWidthProperty().bind(vBox.widthProperty().divide(4));
            bt.minHeightProperty().bind(vBox.heightProperty().divide(7));
            bt.setFont(Font.font("Tahoma",FontWeight.LIGHT,24));
            bt.setTextFill(Color.WHITE);
        }

        Button[] numberList = {bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9};
        for (Button bt: numberList) {
            bt.setOnAction(event ->  {
                if (computed)
                    clear();
                if (builder.toString().equals("0"))
                    builder.deleteCharAt(0);
                int val = Integer.parseInt(bt.getText());
                builder.append(val);
                display.setText(builder.toString());
            });
        }

        Button[] leftBt = {bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, btRoot, btBack, btClear, btDot, btNega};
        for (Button bt: leftBt) {
            bt.setId("button");
        }
        Button[] rightBt = {btPlus, btMinus, btMultiply, btDivide};
        for (Button bt: rightBt) {
            bt.setId("button-operator");
        }
        btEqual.setId("button-equal");

        btPlus.setOnAction(event ->  {
            computed = false;
            CurrentNum = Double.parseDouble(builder.toString());
            display.setText(builder.toString() + " +");
            builder = new StringBuilder();
            Operator = 1;

        });
        btMinus.setOnAction(event ->  {
            computed = false;
            CurrentNum = Double.parseDouble(builder.toString());
            display.setText(builder.toString() + " -");
            builder = new StringBuilder();
            Operator = 2;
        });
        btMultiply.setOnAction(event ->  {
            computed = false;
            CurrentNum = Double.parseDouble(builder.toString());
            display.setText(builder.toString() + " ×");
            builder = new StringBuilder();
            Operator = 3;
        });
        btDivide.setOnAction(event ->  {
            computed = false;
            CurrentNum = Double.parseDouble(builder.toString());
            display.setText(builder.toString() + " ÷");
            builder = new StringBuilder();
            Operator = 4;
        });
        btRoot.setOnAction(event ->  {
            if (Double.parseDouble(builder.toString()) > 0) {
                CurrentNum = Math.sqrt(Double.parseDouble(builder.toString()));
                builder = new StringBuilder(Double.toString(CurrentNum));
                if (builder.charAt(builder.length()-1) == '0' && builder.charAt(builder.length()-2) == '.') {
                    builder.deleteCharAt(builder.length()-1);
                    builder.deleteCharAt(builder.length()-1);
                }
                computed = true;
                display.setText(builder.toString());
            }
        });
        btBack.setOnAction(event ->  {
            if (computed)
                clear();
            else {
                if (builder.length() > 1) {
                    builder.deleteCharAt(builder.length() - 1);
                    display.setText(builder.toString());
                }
                else
                    builder = new StringBuilder("0");
                display.setText(builder.toString());
            }

        });
        btClear.setOnAction(event ->  {
            clear();
        });
        btDot.setOnAction(event ->  {
            if (builder.indexOf(".") < 0) {
                builder.append(".");
                display.setText(builder.toString());
            }
        });
        btNega.setOnAction(event ->  {
            if (builder.charAt(0) == '-')
                builder.deleteCharAt(0);
            else
                builder.insert(0, '-');

            display.setText(builder.toString());
        });
        btEqual.setOnAction(event ->  {
            if (Operator > 0) {
                if (!computed)
                    secondNum = Double.parseDouble(builder.toString());
                switch (Operator) {
                    case 1: CurrentNum += secondNum; break;
                    case 2: CurrentNum -= secondNum; break;
                    case 3: CurrentNum *= secondNum; break;
                    case 4: CurrentNum /= secondNum;
                }
                builder = new StringBuilder(Double.toString(CurrentNum));
                if (builder.charAt(builder.length()-1) == '0' && builder.charAt(builder.length()-2) == '.') {
                    builder.deleteCharAt(builder.length()-1);
                    builder.deleteCharAt(builder.length()-1);
                }
                display.setText(builder.toString());
                computed = true;
            }
        });

        btGrid.add(bt1, 0,3);
        btGrid.add(bt2, 1,3);
        btGrid.add(bt3, 2,3);
        btGrid.add(bt4, 0,2);
        btGrid.add(bt5, 1,2);
        btGrid.add(bt6, 2,2);
        btGrid.add(bt7, 0,1);
        btGrid.add(bt8, 1,1);
        btGrid.add(bt9, 2,1);
        btGrid.add(bt0, 1,4);
        btGrid.add(btPlus, 3, 0);
        btGrid.add(btMinus, 3, 1);
        btGrid.add(btMultiply, 3,2);
        btGrid.add(btDivide, 3, 3);
        btGrid.add(btRoot, 2, 0);
        btGrid.add(btBack, 1,0);
        btGrid.add(btClear, 0,0);
        btGrid.add(btDot, 2,4);
        btGrid.add(btNega, 0,4);
        btGrid.add(btEqual, 3,4);

        vBox.getChildren().add(textField);
        vBox.getChildren().add(display);
        vBox.getChildren().add(btGrid);

        Scene scene = new Scene(vBox);
        scene.getStylesheets().add(getClass().getResource("theme.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.setWidth(400);
        primaryStage.setHeight(500);
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(500);
        primaryStage.show();
    }

    private void clear() {
        CurrentNum = 0;
        secondNum = 0;
        Operator = 0;
        computed = false;
        builder = new StringBuilder("0");
        display.setText(builder.toString());
    }
}
