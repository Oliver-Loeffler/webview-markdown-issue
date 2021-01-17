/*
 * Required modules:
 * 
 * 'javafx.controls', 'javafx.web'
 * 
 * JavaFX version used:
 * 11.0.2
 * 16-ea+6
 * 
 * Java version used:
 * 15.0.1
 * 
 * 
 */

package webview.test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class App extends Application {
	
	private WebView webView;
		
	private TextField resourceName;
	
	private void load(String url) {
		WebEngine engine = webView.getEngine();
		engine.load("about:blank");
		engine.load(url);
		
		resourceName.setText(url);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JavaFX WebView demo");
		
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        
        resourceName = new TextField();
        resourceName.setMaxWidth(Double.MAX_VALUE);
                
        VBox box = new VBox();
        box.getChildren().add(l);
        box.getChildren().add(resourceName);
        
        webView = new WebView();
       
        webView.setMinSize(800, 600);
        VBox.setVgrow(webView, Priority.ALWAYS);
                
        Button remoteHtml = new Button("Load remote Web site.");
        box.getChildren().add(remoteHtml);
        remoteHtml.setOnAction(evt->load("https://openjfx.io/"));
        
        URL localHtmlFile = Paths.get("testfiles/HelloWorld.html").toUri().toURL();
        Button localHtml = new Button("Load local HTML file.");
        box.getChildren().add(localHtml);
        localHtml.setOnAction(evt->load(localHtmlFile.toString()));
        
        Button remoteMarkDown = new Button("Load remote Markdown file.");
        box.getChildren().add(remoteMarkDown);
        remoteMarkDown.setOnAction(evt->load("https://raw.githubusercontent.com/openjdk/jfx/master/README.md"));
        
        Path localMdFile = Paths.get("testfiles/HelloWorld.MD");
        URL localMarkDownFile = localMdFile.toUri().toURL();
        Button localMarkDown = new Button("Load local Markdown (MD) file.");
        box.getChildren().add(localMarkDown);
        localMarkDown.setOnAction(evt->load(localMarkDownFile.toString()));
        
        URL localTextFile = Paths.get("testfiles/HelloWorld.TXT").toUri().toURL();
        Button localText = new Button("Load local TXT file.");
        box.getChildren().add(localText);
        localText.setOnAction(evt->load(localTextFile.toString()));
       
        Button localMdFileQuickView = new Button("View local MD file");
        localMdFileQuickView.setOnAction(evt->{
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setHeaderText("QuickView of local MD file: " + localMdFile.toAbsolutePath());
        	
        	try {
				String contents = Files.readString(localMdFile);
				alert.setContentText(contents);
			} catch (IOException e) {
				alert.setContentText("Failed to read local MD file");
			}
        	
        	Platform.runLater(()->alert.showAndWait());
        	
        });
        box.getChildren().add(localMdFileQuickView);
        box.getChildren().add(webView);
        
        Scene scene = new Scene(box, 800, 700);
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        
	}
	

	public static void main(String[] args) {
        launch();
    }
    
}

/*
 * testfiles/HelloWorld.html
 * 
 * <html>
 * <body>
 * <h1>Hello HTML!</h1>
 * <p>
 * This HTML content is loaded from a local file system resource.
 * </p>
 * </body>
 * </html>
 * 
 */

/*
 * testfiles/HelloWorld.MD
 * 
 * # Markdown
 * 
 * Markdown can be quite useful.
 * 
 * ## Chapter 1
 * 
 * Some text may follow here.
 * 
 * ## Chapter 2
 * 
 * This markdown is loaded from local file system.
 *
 * 
 */

/*
 * testfiles/HelloWorld.TXT
 * 
 * # Markdown
 * 
 * Markdown can be quite useful.
 * 
 * ## Chapter 1
 * 
 * Some text may follow here.
 * 
 * ## Chapter 2
 * 
 * This markdown is loaded from local file system.
 *
 * 
 */