package com.example.myproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class JavaFXOpenCVApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Charger la bibliothèque native OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Créer une image OpenCV
        Mat mat = Mat.eye(200, 200, CvType.CV_8UC3);
        Imgproc.rectangle(mat, new org.opencv.core.Point(50, 50), new org.opencv.core.Point(150, 150),
                new Scalar(0, 255, 0), -1);

        // Convertir l'image OpenCV en WritableImage pour JavaFX
        WritableImage writableImage = convertMatToImage(mat);

        // Afficher l'image dans une interface JavaFX
        ImageView imageView = new ImageView(writableImage);
        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 300, 300);
        System.out.print(false);

        primaryStage.setTitle("JavaFX with OpenCV");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Méthode pour convertir une Mat d'OpenCV en WritableImage pour JavaFX
    private WritableImage convertMatToImage(Mat mat) {
        WritableImage writableImage = new WritableImage(mat.width(), mat.height());
        for (int i = 0; i < mat.height(); i++) {
            for (int j = 0; j < mat.width(); j++) {
                double[] data = mat.get(i, j);
                int r = (int) data[0];
                int g = (int) data[1];
                int b = (int) data[2];
                writableImage.getPixelWriter().setArgb(j, i, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
        return writableImage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}