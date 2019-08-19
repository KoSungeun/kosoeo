package chat;

import java.io.IOException;
import java.net.Socket;

import chat.model.Room;
import chat.view.ChatController;
import chat.view.InviteController;
import chat.view.JoinController;
import chat.view.LoginController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane mainLayout;
	private Socket socket;
	private ObservableList<Room> roomData = FXCollections.observableArrayList();
	
	public LoginController loginController;

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
			
			loginController = loader.getController();
			loginController.setMainApp(this);
			
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
			controller.setMainApp(this, socket);


		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	
	public boolean showJoinDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Join.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("회원가입");
			dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
			JoinController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean showInviteDialog(String msg) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Invite.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("초대");
			dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
			InviteController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setInviteMsg(msg);
			
			dialogStage.showAndWait();
			
			return controller.isOkClicked();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	

	public static void main(String[] args) {
		launch(args);
	}
	
	public void serverConnect(String ServerIP) throws IOException {	
		socket = new Socket(ServerIP, 9999); 
	}
	public String Login(String id, String password) {
		String request = String.format("login/%s/%s", id, password);
		Thread sender = new Sender(socket, request);
		String response = null;
		Receiver receiver = new Receiver(socket);
		sender.start();
		response = receiver.call();
		return response;
	}
	
	public String join(String id, String password) {
		String request = String.format("join/%s/%s", id, password);
		Thread sender = new Sender(socket, request);
		String response = null;
		Receiver receiver = new Receiver(socket);
		sender.start();
		response = receiver.call();
		return response;
	}
	
	
	public void showAlert(String title, String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}
	public Socket getSocket() {
		return this.socket;
	}
	
	public ObservableList<Room> getRoomData(){
		return roomData;
	}
}
