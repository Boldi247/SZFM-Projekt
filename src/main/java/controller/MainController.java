package controller;

import com.itextpdf.text.DocumentException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import exceptions.model.DataModel;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public Text aramOsszDijText;
    @FXML
    public Text gazOsszDijText;
    @FXML
    private TextField aramTarifa_A;
    @FXML
    private TextField aramTarifa_B;
    @FXML
    private TextField gazFogyasztas;
    @FXML
    private Spinner<Integer> gyermekSzamValaszto;
    @FXML
    private ChoiceBox<String> honapValaszto;

    private final DataModel dataModel = new DataModel();

    private SceneController sceneController;

    private PdfCreatorController pdfCreatorController = new PdfCreatorController();

    private final String[] honapValasztoErtekek = {
            "Január",
            "Február",
            "Március",
            "Április",
            "Május",
            "Június",
            "Július",
            "Augusztus",
            "Szeptember",
            "Október",
            "November",
            "December"
    };

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        sceneController = new SceneController();

        int gyermekSzamMax = 15;

        honapValaszto.getItems().addAll(honapValasztoErtekek);
        honapValaszto.setValue(honapValasztoErtekek[0]);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, gyermekSzamMax);
        gyermekSzamValaszto.setValueFactory(valueFactory);
    }

    public void onExit() {
        Platform.exit();
    }

    public void calculate(ActionEvent event) throws DocumentException, FileNotFoundException {
        if (isUserInputValid()) {
            String aramOsszDij = String.valueOf(dataModel.calculateAram());
            String gazOsszDij = String.valueOf(dataModel.calculateGaz());

            aramOsszDijText.setText(aramOsszDij + " Ft");
            gazOsszDijText.setText(gazOsszDij + " Ft");

            pdfCreatorController.createAramGazPdf(aramOsszDij, gazOsszDij);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Az adatok vagy nincsenek, vagy nem jól lettek megadva!");
            alert.setTitle("Hiba");
            alert.setResizable(false);
            alert.show();
        }
    }

    private boolean isUserInputValid() {
        if (aramTarifa_A.getText().isEmpty())
            aramTarifa_A.setText("0");

        if (aramTarifa_B.getText().isEmpty())
            aramTarifa_B.setText("0");

        if (gazFogyasztas.getText().isEmpty())
            gazFogyasztas.setText("0");

        try {
            dataModel.setInputHaviAram_A(Integer.parseInt(aramTarifa_A.getText()));
            dataModel.setInputHaviAram_B(Integer.parseInt(aramTarifa_B.getText()));
            dataModel.setInputHaviGaz(Integer.parseInt(gazFogyasztas.getText()));
            dataModel.setInputGyerekSzam(gyermekSzamValaszto.getValue());
            dataModel.setInputHonap(honapValaszto.getValue());
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public void saveDataFromFirstScene(
            int aramPiaciAr,
            int aramCsokkentettAr,
            int aramPiaciAr_B,
            int aramCsokkentettAr_B,
            int gazPiaciAr,
            int gazCsokkentettAr
    ) {
        dataModel.setAramPiaciAr(aramPiaciAr);
        dataModel.setAramCsokkentettAr(aramCsokkentettAr);
        dataModel.setAramPiaciAr_B(aramPiaciAr_B);
        dataModel.setAramCsokkentettAr_B(aramCsokkentettAr_B);
        dataModel.setGazPiaciAr(gazPiaciAr);
        dataModel.setGazCsokkentettAr(gazCsokkentettAr);
    }
}
