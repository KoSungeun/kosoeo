package chat.view;

import java.io.IOException;

import chat.MainApp;
import chat.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JoinController {
	@FXML
	TextField idField;
	@FXML
	PasswordField passwordField;
	@FXML
	PasswordField checkField;

	private Stage dialogStage;
	private MainApp mainApp;
	private boolean okClicked = false;

	public JoinController() {

	}

	@FXML
	private void initialize() {
		
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {

			StringUtil su = new StringUtil();
			String id = idField.getText();
			String password = passwordField.getText();
			try {

				String ip = mainApp.loginController.getIPField();
				mainApp.serverConnect(ip);

				String response = mainApp.join(id, password);
				String state = su.rSplit(response, 0);
				String msg = su.rSplit(response, 1);
				if (!state.equals("join")) {
					mainApp.showChat();
				} else {
					mainApp.showAlert("로그인 오류", msg);
				}
				okClicked = true;
				dialogStage.close();
			} catch (IOException e) {
				mainApp.showAlert("로그인 오류", "서버에 접속할 수 없습니다.");
				okClicked = true;
				dialogStage.close();
			}
		}

	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (idField.getText() == null || idField.getText().length() == 0) {
			errorMessage += "아이디를 입력하지 않았습니다\n";
		}
		if (passwordField.getText() == null || passwordField.getText().length() == 0) {
			errorMessage += "비밀번호를 입력하지 않았습니다\n";
		}
		if (!passwordField.getText().equals(checkField.getText())) {
			errorMessage += "입력한 비밀번호가 다릅니다\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("입력값 오류");
			alert.setHeaderText("값을 입력해주세요");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}

	}

}
