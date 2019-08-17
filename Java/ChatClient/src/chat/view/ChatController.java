package chat.view;

import chat.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController {
	@FXML
	ListView<String> userList;
	@FXML
	TextField chatField;
	@FXML
	TextArea chatArea;
	@FXML
	Button sendButton;
	
	
	private MainApp mainApp;
	
	
	public ChatController() {
		
	}
	
	
	@FXML
	private void initialize() {
		chatArea.setText("채팅에 입장하였습니다\n");

	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	
	@FXML
	private void onEnter(ActionEvent ae){
		chatField.clear();
		
	}
	


}


	