package chat;

import java.io.IOException;
import java.net.Socket;

import chat.view.ChatController;
import chat.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane mainLayout;
	private Socket socket;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("ChatClient");
		

		initRootLayout();
		showLogin();

	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Main.fxml"));
			mainLayout = (BorderPane) loader.load();

			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showLogin() {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
			AnchorPane loginView = (AnchorPane) loader.load();
			
			mainLayout.setCenter(loginView);
			
			LoginController controller = loader.getController();
			controller.setMainApp(this);
			
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public void showChat() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Chat.fxml"));
			GridPane chatView = (GridPane) loader.load();
			
			mainLayout.setCenter(chatView);
			
			ChatController controller = loader.getController();
			controller.setMainApp(this);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	

	public static void main(String[] args) {
		launch(args);
	}
	
	public void serverConnect(String ServerIP) {	
		try {
			socket = new Socket(ServerIP, 9999); 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public String Login(String id, String password) {
		String request = String.format("login/%s/%s", id, password);
		Thread sender = new Sender(socket, request);
		Receiver receiver = new Receiver(socket);
		sender.start();
		String response = null;
		try {
			response = receiver.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	public void showAlert(String title, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
