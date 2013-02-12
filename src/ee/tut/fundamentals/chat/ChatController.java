package ee.tut.fundamentals.chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller that receives events from the visible chat form.
 * Only reacts when [Enter] is pressed in the message field
 * Then it invokes the ChatClient to send the message to the server.
 */
public class ChatController implements Initializable, MessageListener {

  private ChatClient chatClient;

  @FXML
  private TextField say;

  @FXML
  private TextArea chat;



  /**
   * Fired when user presses the [Enter] key when the text box has focus.
   */
  @FXML
  public void handleButtonAction(ActionEvent event) {
      String text = say.getText().trim();
      if (text.length()==0) {
        return;
      }

      try {
        //send the value of the text box to the server
        chatClient.postMessage(text);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      say.setText("");
  }


  /**
   * Great, we received an IM from the server. Let's display it
   */
  @Override
  public void onMessage(String msg) {
    String chatText = chat.getText();
    if (chatText.length()==0) {
      chat.setText(msg);
    }
    else {
      chat.setText(chatText + "\n" + msg);
    }

  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    say.requestFocus();
  }

  public void setChatClient(ChatClient chatClient) {
    this.chatClient = chatClient;
  }

}
