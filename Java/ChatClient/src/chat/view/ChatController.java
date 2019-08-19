package chat.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import chat.MainApp;
import chat.StringUtil;
import chat.model.Room;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	@FXML
	TableView<Room> roomTable;
	@FXML
	TableColumn<Room, String> rnoColumn;
	@FXML
	TableColumn<Room, String> nameColumn;
	@FXML
	TableColumn<Room, String> sizeColumn;
	@FXML
	TableColumn<Room, String> passColumn;

	BufferedReader in = null;
	PrintWriter out = null;

	boolean isInvite = false;

	public ChatController() {

	}

	@FXML
	private void initialize() {
		userList.setItems(FXCollections.observableArrayList());
		rnoColumn.setCellValueFactory(callData -> callData.getValue().rnoProperty());
		nameColumn.setCellValueFactory(callData -> callData.getValue().nameProperty());
		sizeColumn.setCellValueFactory(callData -> callData.getValue().sizeProperty());
		passColumn.setCellValueFactory(callData -> callData.getValue().passProperty());
	}

	public void setMainApp(MainApp mainApp, Socket socket) {


		StringUtil su = new StringUtil();
//		roomTable.getSelectionModel().selectedItemProperty().addListener(e-> {
//			
//		});
		Runnable r = () -> {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String response;
				roomTable.setItems(mainApp.getRoomData());
				while (in != null) {
					response = in.readLine();
					if (response == null) {
						Platform.runLater(() -> {
						mainApp.showLogin();
						mainApp.showAlert("접속오류", "서버와 접속이 종료됐습니다");
						});
						break;
	
					}
					if (response.startsWith("/user")) {
						StringTokenizer st = new StringTokenizer(response, "/");
						Platform.runLater(() -> {
							userList.getItems().clear();
							while (st.hasMoreTokens()) {
								String token = st.nextToken();
								if (!token.equals("user") || token.equals("")) {
									userList.getItems().add(token);
								}
							}
						});

					} else if (response.startsWith("/invite")) {
						String userId = su.rSplit(response, 2);
						String msg = su.rSplit(response, 3);
						Platform.runLater(() -> {
							if (!isInvite) {
								isInvite = true;
								if (mainApp.showInviteDialog(msg)) {
									out.println("/response/" + userId + "/y");
								} else {
									out.println("/response/" + userId + "/n");
								}
								isInvite = false;
							} else {
								out.println("/response/" + userId + "/busy");
							}
						});

					} else if (response.startsWith("/room")) {
						StringTokenizer st = new StringTokenizer(response.substring(6), "@");
						Platform.runLater(() -> {
							mainApp.getRoomData().clear();
							while (st.hasMoreTokens()) {
								String token = st.nextToken();
								mainApp.getRoomData()
										.add(new Room(su.rSplit(token, 0), su.rSplit(token, 1),
												su.rSplit(token, 3) + "/" + su.rSplit(token, 2), su.rSplit(token, 4)));
							}
						});
						

					} else {
						chatArea.appendText(response + "\n");
					}

				}

			} catch (IOException e) {
				mainApp.showLogin();
				mainApp.showAlert("접속오류", "서버와 접속이 종료됐습니다");
			}
		};

		Runnable s = () -> {
			try {
				out = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				System.out.println("예외S3:" + e);
			}
		};

		new Thread(r).start();
		new Thread(s).start();
	}

	@FXML
	private void onEnter(ActionEvent ae) {
		String msg = chatField.getText();
		out.println(msg);
		if (msg.startsWith("/to")) {
			try {
				chatField.setText(msg.substring(0, msg.indexOf(" ", msg.indexOf(" ") + 1)) + " ");
				chatField.positionCaret(chatField.getText().length());
			} catch (StringIndexOutOfBoundsException e2) {
				chatField.clear();
			}
		} else {
			chatField.clear();
		}
		
	}

}
