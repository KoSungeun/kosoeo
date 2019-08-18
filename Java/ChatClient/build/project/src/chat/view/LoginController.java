
package chat.view;

import java.io.IOException;
import java.net.Socket;

import chat.MainApp;
import chat.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
	@FXML
	private TextField serverIPField;
	@FXML
	private TextField idField;
	@FXML
	private PasswordField passwordField;

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
	@FXML
	private Button joinButton;

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
	public String getIPField() {
		return serverIPField.getText();
	}

	@FXML
	private void handleLogin() {
		StringUtil su = new StringUtil();
		String id = idField.getText();
		String password = passwordField.getText();
		if(id.trim().equals("") || password.trim().equals("")) {
			mainApp.showAlert("로그인 오류", "ID/PASSWORD를 입력해주세요");
			return;
		}
		try {
			mainApp.serverConnect(serverIPField.getText());
			String response = mainApp.Login(id, password);
			String state = su.rSplit(response, 1);
			String msg = su.rSplit(response, 2);
			if(!state.equals("null")) {
				mainApp.showChat();
			} else {
				mainApp.showAlert("로그인 오류", msg);
			}
		} catch (IOException e) {
			mainApp.showAlert("로그인 오류", "서버에 접속할 수 없습니다.");
		}		
	}
	
	
	@FXML
	private void handleJoin() {
		mainApp.showJoinDialog();
	}
	
	@FXML
	private void onEnter() {
		handleLogin();
	}
}
