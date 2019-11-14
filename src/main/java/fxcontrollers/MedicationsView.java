package fxcontrollers;

import grpc.GRPCClient;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static grpc.MedicationServiceOuterClass.*;


public class MedicationsView
{
    private static final LocalTime changeTime = LocalTime.of(0,50,40);
    private static final LocalTime startMorningTime = LocalTime.of(5,0,0);
    private static final LocalTime startAfternoonTime = LocalTime.of(12,0,0);
    private static final LocalTime startEveningTime = LocalTime.of(17,0,0);
    private static final LocalTime startNightTime = LocalTime.of(21,0,0);

    @FXML
    private VBox labelsBox;

    @FXML
    private VBox buttonsBox;

    @FXML
    private Label time;

    private GRPCClient grpcClient;

    @FXML
    public void initialize()
    {
        setUpTimer();

        grpcClient = new GRPCClient();

        labelsBox.setSpacing(5);
        buttonsBox.setSpacing(5);
    }

    private void downloadMedicationPlan()
    {
        List<Button> buttonList = new ArrayList<>();
        List<Label> labelList = new ArrayList<>();

        List<Medication> medications = grpcClient.getMedications();

        for (Medication medication : medications)
        {
            Label label = createLabel(medication.getMedicationName());
            labelList.add(label);
            buttonList.add(createButton(label));
        }

        labelsBox.getChildren().clear();
        labelsBox.getChildren().addAll(labelList);

        buttonsBox.getChildren().clear();
        buttonsBox.getChildren().addAll(buttonList);
    }

    private Label createLabel(String medicationName)
    {
        Label label = new Label(medicationName);
        label.setPrefHeight(30);
        return label;
    }

    private Button createButton(Label label)
    {
        Button button = new Button("Taken");
        button.setPrefWidth(100);
        button.setPrefHeight(30);
        button.setOnAction(e ->
        {
            labelsBox.getChildren().remove(label);
            buttonsBox.getChildren().remove(button);
        });
        return button;
    }

    private void setUpTimer()
    {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO,e ->
        {
            LocalTime currentTime = LocalTime.now();
            if ((currentTime.getHour() == changeTime.getHour()) &&
                    (currentTime.getMinute() == changeTime.getMinute()) &&
                    (currentTime.getSecond() == changeTime.getSecond()))
            {
                downloadMedicationPlan();
            }
            time.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
