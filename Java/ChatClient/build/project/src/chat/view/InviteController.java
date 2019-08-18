package chat.view;

import chat.MainApp;
import chat.StringUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InviteController {

	@FXML
	private Label inviteMsg;

	private Stage dialogStage;
	private MainApp mainApp;
	private boolean okClicked = false;

	public InviteController() {

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
	public void setInviteMsg(String msg) {
		inviteMsg.setText(msg);
	}

	@FXML
	private void handleOk() {
		okClicked = true;
		dialogStage.close();
	}

	@FXML
	private void handleCancel() {

		dialogStage.close();
	}

}
