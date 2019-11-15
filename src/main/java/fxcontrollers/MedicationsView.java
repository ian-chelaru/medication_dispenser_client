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
    private static final LocalTime changeTime = LocalTime.of(10,34,0);
    private static final LocalTime morningStartTime = LocalTime.of(5,0,0);
    private static final LocalTime afternoonStartTime = LocalTime.of(12,0,0);
    private static final LocalTime eveningStartTime = LocalTime.of(17,0,0);
    private static final LocalTime nightStartTime = LocalTime.of(21,0,0);

    private GRPCClient grpcClient;

    private List<Label> morningLabelList = new ArrayList<>();
    private List<Label> afternoonLabelList = new ArrayList<>();
    private List<Label> eveningLabelList = new ArrayList<>();

    private List<Button> morningButtonList = new ArrayList<>();
    private List<Button> afternoonButtonList = new ArrayList<>();
    private List<Button> eveningButtonList = new ArrayList<>();


    @FXML
    private VBox labelsBox;

    @FXML
    private VBox buttonsBox;

    @FXML
    private Label time;

    @FXML
    public void initialize()
    {
        setUpTimer();

        grpcClient = new GRPCClient();

        labelsBox.setSpacing(5);
        buttonsBox.setSpacing(5);

        downloadMedicationPlan();
        initDisplay();
    }

    private void downloadMedicationPlan()
    {
        List<Medication> medications = grpcClient.getMedications();

        for (Medication medication : medications)
        {
            parseDailyInterval(medication);
        }
    }

    private void parseDailyInterval(Medication medication)
    {
        String dailyInterval = medication.getDailyInterval();
        String[] intervals = dailyInterval.split("-");
        if ("1".equals(intervals[0]))
        {
            Label label = createLabel(medication.getMedicationName());
            morningLabelList.add(label);
            morningButtonList.add(createButton(label));
        }
        if ("1".equals(intervals[1]))
        {
            Label label = createLabel(medication.getMedicationName());
            afternoonLabelList.add(label);
            afternoonButtonList.add(createButton(label));
        }
        if ("1".equals(intervals[2]))
        {
            Label label = createLabel(medication.getMedicationName());
            eveningLabelList.add(label);
            eveningButtonList.add(createButton(label));
        }
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
            grpcClient.sendMedicationTaken(label.getText());
        });
        return button;
    }

    private void setUpTimer()
    {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO,e ->
        {
            LocalTime currentTime = LocalTime.now();

            if (areLocalTimesEqual(currentTime,changeTime))
            {
                downloadMedicationPlan();
                System.out.println("Medication plan downloaded");
            }

            if (areLocalTimesEqual(currentTime,morningStartTime))
            {
                display(morningLabelList, morningButtonList);
            }

            if (areLocalTimesEqual(currentTime,afternoonStartTime))
            {
                sendNotTakenMessages();
                display(afternoonLabelList, afternoonButtonList);
            }

            if (areLocalTimesEqual(currentTime, eveningStartTime))
            {
                sendNotTakenMessages();
                display(eveningLabelList, eveningButtonList);
            }

            if (areLocalTimesEqual(currentTime, nightStartTime))
            {
                sendNotTakenMessages();
                display(new ArrayList<>(), new ArrayList<>());
            }

            time.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private boolean areLocalTimesEqual(LocalTime time1,LocalTime time2)
    {
        return (time1.getHour() == time2.getHour()) && (time1.getMinute() == time2.getMinute()) &&
                (time1.getSecond() == time2.getSecond());
    }

    private void display(List<Label> labelList, List<Button> buttonList)
    {
        labelsBox.getChildren().clear();
        labelsBox.getChildren().addAll(labelList);

        buttonsBox.getChildren().clear();
        buttonsBox.getChildren().addAll(buttonList);
    }

    private void initDisplay()
    {
        LocalTime currentTime = LocalTime.now();

        if (currentTime.isAfter(morningStartTime) && currentTime.isBefore(afternoonStartTime))
        {
            display(morningLabelList, morningButtonList);
        }
        if (currentTime.isAfter(afternoonStartTime) && currentTime.isBefore(eveningStartTime))
        {
            display(afternoonLabelList, afternoonButtonList);
        }
        if (currentTime.isAfter(eveningStartTime) && currentTime.isBefore(nightStartTime))
        {
            display(eveningLabelList, eveningButtonList);
        }
        if (currentTime.isAfter(nightStartTime) && currentTime.isBefore(morningStartTime))
        {
            display(new ArrayList<>(), new ArrayList<>());
        }
    }

    private void sendNotTakenMessages()
    {
        labelsBox.getChildren().forEach(node -> {
            Label label = (Label) node;
            grpcClient.sendMedicationNotTaken(label.getText());
        });
    }
}
