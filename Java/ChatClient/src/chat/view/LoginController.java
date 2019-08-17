
package chat.view;

import java.io.IOException;
import java.net.Socket;

import chat.MainApp;
import chat.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField serverIPField;
	@FXML
	private TextField idField;
	@FXML
	private TextField passwordField;

	@FXML
	private Label serverIPLabel;
	@FXML
	private Label idLabel;
	@FXML
	private Label passwordLabel;

	@FXML
	private Button loginButton;
	@FXML
	private Button exitButton;

	private MainApp mainApp;
	

	public LoginController() {
		
	}

	public Socket serverConnect(String ServerIP) throws IOException {
		Socket socket = new Socket(ServerIP, 9999); // 소켓 객체 생성
		return socket;
	}

	@FXML
	private void initialize() {
		serverIPField.setText("localhost");
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

	}

	@FXML
	private void handleLogin()  {
		StringUtil su = new StringUtil();
		mainApp.serverConnect(serverIPField.getText());
		String response = mainApp.Login(idField.getText(), passwordField.getText());
		String state = su.rSplit(response, 1);
		String msg = su.rSplit(response, 2);
		System.out.println(state);
		if(!state.equals("null")) {
			mainApp.showChat();
		} else {
			mainApp.showAlert("로그인 오류", msg);
		}
	}
}
