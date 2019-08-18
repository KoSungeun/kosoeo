package chat.view;

import java.io.IOException;

import chat.MainApp;

import javafx.fxml.FXML;

import javafx.scene.control.TextArea;

import javafx.scene.control.ToggleButton;

public class MainController {
	@FXML
	public TextArea logArea;
	@FXML
	public ToggleButton serverOnOff;
	boolean onOff = false;
	
	private MainApp mainApp;
	
	
	public MainController() {
		
	}
	
	
	@FXML
	private void initialize() {

	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	private void handleOnOff() throws IOException {
		if(onOff) {
			serverOnOff.setText("서버시작");
			onOff = false;
			mainApp.closeServer();
			
		} else {
			serverOnOff.setText("서버종료");
			onOff = true;
			mainApp.serverStart();
			
		}
	}
	
	
}
