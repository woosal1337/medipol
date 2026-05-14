package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Creating the widget
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);


        // Intro label
        Text text = new Text();
        text.setText("All graphic widgets in one");
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 25));

        GridPane.setConstraints(text, 0, 0);

        // Small intro label
        Text text2 = new Text();
        text2.setText("Here is some text.... and a place to enter text");
        text2.setFont(Font.font("verdana", FontWeight.LIGHT, FontPosture.REGULAR, 13));

        GridPane.setConstraints(text2, 0, 1);


        // Text field 1
        TextField textfield = new TextField("This is my text");

        GridPane.setConstraints(textfield, 0, 2);

        // Text field 2
        TextField textfield2 = new TextField("This is the default Text should you decide not to type anything");

        GridPane.setConstraints(textfield2, 0, 5);

        // Text field 3
        TextField textfield3 = new TextField("A second multi-line");
        textfield3.setMinWidth(300);
        textfield3.setPrefWidth(300);

        GridPane.setConstraints(textfield3, 1, 5);

        // Creating checkbox 1
        CheckBox checkbox = new CheckBox("My first checkbox!");

        GridPane.setConstraints(checkbox, 0, 3);

        // Creating checkbox 2
        CheckBox checkbox2 = new CheckBox("My second checkbox!");

        GridPane.setConstraints(checkbox2, 1, 3);

        // Creating radiobox
        RadioButton radioButton = new RadioButton("My first radio!");

        GridPane.setConstraints(radioButton, 0, 4);

        // Creating radiobox 2
        RadioButton radioButton2 = new RadioButton("My second radio!");

        GridPane.setConstraints(radioButton2, 1, 4);

        // Creating combox
        ComboBox combobox = new ComboBox();
        combobox.getItems().add("Combobox 1");
        combobox.getItems().add("Combobox 2");
        combobox.getItems().add("Combobox 3");
        combobox.getSelectionModel().selectFirst();

        GridPane.setConstraints(combobox, 0, 6);

        // Creating scrollbar
        Slider slider = new Slider(0, 100, 0);

        GridPane.setConstraints(slider, 1, 6);

        // Creating a listview
        ListView listview = new ListView();
        listview.getItems().add("Listbox 1");
        listview.getItems().add("Listbox 2");
        listview.getItems().add("Listbox 3");

        GridPane.setConstraints(listview, 0, 7);

        // Creating scrollbar 2
        ScrollBar scrollbar2 = new ScrollBar();
        scrollbar2.setMin(0);
        scrollbar2.setMax(100);
        scrollbar2.setValue(85);
        scrollbar2.setOrientation(Orientation.VERTICAL);
        scrollbar2.setUnitIncrement(12);
        scrollbar2.setBlockIncrement(10);

        GridPane.setConstraints(scrollbar2, 1, 7);

        // Creating scrollbar 3
        ScrollBar scrollbar3 = new ScrollBar();
        scrollbar3.setMin(0);
        scrollbar3.setMax(100);
        scrollbar3.setValue(85);
        scrollbar3.setOrientation(Orientation.VERTICAL);
        scrollbar3.setUnitIncrement(12);
        scrollbar3.setBlockIncrement(10);

        GridPane.setConstraints(scrollbar3, 2, 7);

        // Creating scrollbar 4
        ScrollBar scrollbar4 = new ScrollBar();
        scrollbar4.setMin(0);
        scrollbar4.setMax(100);
        scrollbar4.setValue(85);
        scrollbar4.setOrientation(Orientation.VERTICAL);
        scrollbar4.setUnitIncrement(12);
        scrollbar4.setBlockIncrement(10);

        GridPane.setConstraints(scrollbar4, 3, 7);

        // Creating spinner
        Spinner spinner = new Spinner(0, 10, 4);
        Spinner spinner2 = new Spinner(11, 20, 15);
        Spinner spinner3 = new Spinner(21, 30, 25);

        Label spinnerlabel = new Label("Column 1");

        ListView spinnerlist = new ListView();
        spinnerlist.getItems().add(spinnerlabel);
        spinnerlist.getItems().add(spinner);
        spinnerlist.getItems().add(spinner2);
        spinnerlist.getItems().add(spinner3);


        GridPane.setConstraints(spinnerlist, 4, 7);

        // Drawing a line
        Line line = new Line(0, 0, 790, 0);
        ;

        GridPane.setConstraints(line, 0, 8);

        // Creating choose a folder
        Text text3 = new Text();
        text3.setText("Choose A Folder");
        text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        GridPane.setConstraints(text3, 0, 9);

        // Creating your folder
        Text text4 = new Text();
        text4.setText("Your Folder");
        text4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        GridPane.setConstraints(text4, 0, 10);

        // Text field 3
        TextField textfield4 = new TextField("Default folder");

        GridPane.setConstraints(textfield4, 0, 11);

        // Creating button 1
        Button button = new Button("Browse");

        GridPane.setConstraints(button, 1, 11);

        // Creating button 2
        Button button2 = new Button("Submit");

        GridPane.setConstraints(button2, 1, 12);

        // Creating button 3
        Button button3 = new Button("Cancel");

        GridPane.setConstraints(button3, 2, 12);


        // My first checbox listener
        checkbox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    textfield3.setText("My first checkbox has been clicked!");
                    System.out.println("My first checkbox has been clicked!");
                });

        // My second checbox listener
        checkbox2.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    textfield3.setText("My second checkbox has been clicked!");
                    System.out.println("My second checkbox has been clicked!");
                });

        // My first radio button listener
        radioButton.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    textfield3.setText("My first radio button has been clicked!");
                    System.out.println("My first radio button has been clicked!");
                });

        // My second radio button listener
        radioButton2.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    textfield3.setText("My second radio button has been clicked!");
                    System.out.println("My second radio button has been clicked!");
                });

        // My first slider listener
        slider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                textfield3.setText("The main slider value is: " + String.valueOf(newValue.intValue()));
                System.out.println("The main slider value is: " + String.valueOf(newValue.intValue()));
            }
        });

        // My first slider listener
        scrollbar2.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                textfield3.setText("The 1st slider value is: " + String.valueOf(newValue.intValue()));
                System.out.println("The 1st slider value is: " + String.valueOf(newValue.intValue()));
            }
        });

        // My first slider listener
        scrollbar3.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                textfield3.setText("The 2nd slider value is: " + String.valueOf(newValue.intValue()));
                System.out.println("The 2nd slider value is: " + String.valueOf(newValue.intValue()));
            }
        });

        // My first slider listener
        scrollbar4.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                textfield3.setText("The 3rd slider value is: " + String.valueOf(newValue.intValue()));
                System.out.println("The 3rd slider value is: " + String.valueOf(newValue.intValue()));
            }
        });

        // Combobox listener
        combobox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    textfield3.setText((String) newValue);
                    System.out.println(newValue);
                }
        );


        // Spinner 1 listener
        spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                    textfield3.setText("Spinner 1 value: " + newValue);
                    System.out.println("Spinner 1 value: " + newValue);
                }
        );

        // Spinner 2 listener
        spinner2.valueProperty().addListener((obs, oldValue, newValue) -> {
            textfield3.setText("Spinner 2 value: " + newValue);
            System.out.println("Spinner 2 value: " + newValue);
        });

        // Spinner 3 listener
        spinner3.valueProperty().addListener((obs, oldValue, newValue) -> {
            textfield3.setText("Spinner 3 value: " + newValue);
            System.out.println("Spinner 3 value: " + newValue);
        });

        // Button 1 action handler
        button.setOnAction(a -> textfield3.setText("Browser button was just clicked!"));
        button.setOnAction(a -> System.out.println("Browser button was just clicked!"));

        // Button 2 action handler
        button2.setOnAction(a -> System.out.println("Submit button was just clicked!"));

        // Button 3 action handler
        button3.setOnAction(a -> System.out.println("Cancel button was just clicked!"));


        // Combining all the objects under a grid object
        grid.getChildren().addAll(
                text,
                text2,
                textfield,
                textfield3,
                checkbox,
                checkbox2,
                radioButton,
                radioButton2,
                textfield2,
                combobox,
                slider,
                listview,
                scrollbar2,
                scrollbar3,
                scrollbar4,
                spinnerlist,
                line,
                text3,
                text4,
                textfield4,
                button,
                button2,
                button3
        );

        Scene scene = new Scene(grid, 1600, 800);
        Stage stage = new Stage();
        stage.setTitle("Everything bagel");
        stage.setScene(scene);
        //Displaying the contents of the stage
        stage.show();
    }
}