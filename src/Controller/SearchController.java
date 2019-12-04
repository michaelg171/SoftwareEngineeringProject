package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import Model.*;
import java.util.ArrayList;

/* TODO
 * 
 * I could not get data to display using sample data, so that still needs to be fixed.
 * 
 * A table was added.
 * Load items into an ArrayList in loadSearchData()
 * The ArrayList is converted to an ObservableList<>
 * A TableView is created using the ObservableList<>
 */

public class SearchController implements Initializable, EventHandler<ActionEvent> {
	
	ArrayList<String>options = new ArrayList<String>();
	private String curFxml = "../View/Search.fxml";
	
	@FXML
	Button leftBtn;
	@FXML
	Button rightBtn;
	@FXML
	Button homeBtn;
	@FXML
	Button loginBtn;
	@FXML
	Button searchBtn;
	@FXML
	Button settingsBtn;
	@FXML
	Button cartBtn;
	@FXML
	ComboBox<String> optionsComboBox;
	@FXML
	TextField searchBox;
	
	// LOCAL
	@FXML
	TableView<Item> results;

	ArrayList<Item>loadList;
	ObservableList<Item>items;
	// @FXML
	// TableView<Item>results;
		
	@Override
	public void handle(ActionEvent e) {
				
		if( e.getSource() == homeBtn ) {
			
			goToView("../View/Main.fxml");
		}
		
		else if( loginBtn == e.getSource()) {

			goToView("../View/Login.fxml");
		}
		
		else if( searchBtn == e.getSource()) {

			goToView("../View/Search.fxml");
		}
		
		else if( settingsBtn == e.getSource()) {
			
			if(true == MainController.isLoggedIn) {
				goToView("../View/Settings.fxml");
			}
			
			// redirect user to the login menu if they are not signed in
			// you need to be signed in to change your account settings
			else {
				goToView("../View/Login.fxml");
			}
		}
		
		else if( cartBtn == e.getSource()) {

			goToView("../View/Cart.fxml");
		}
		
		else if( leftBtn == e.getSource()) {
			goToView(MainController.backwardView);
		}
		
		else if( rightBtn == e.getSource()) {
			forwardTrick();
			goToView(MainController.forwardView);
		}
	}
	
	// set the variables in MainController before switching views
	public void passVar() {
		MainController.selectedOption = optionsComboBox.getSelectionModel().getSelectedIndex();
	}
	
	// code to simplify changing views
	public void goToView(String xmlPath) {
		
		try {
			passVar();
			MainController.backwardView = curFxml;
			MainController.forwardView = xmlPath;
			Parent root = FXMLLoader.load(getClass().getResource(xmlPath));
			Main.stage.setScene(new Scene(root, 1200, 800));
			Main.stage.show();
		}
		
		catch(IOException error) {
			System.out.print("\n\n\tError: Could not change scenes\n\n");
			error.printStackTrace();
		}
	}
	
	public void forwardTrick() {
		String temp = MainController.forwardView;
		MainController.forwardView = MainController.backwardView;
		MainController.backwardView = temp;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("Switched to Search View!");
		
		loadSearchResults();
		
		setUpNavigationBar();
	}
	
	public void loadSearchResults() {
		
		// load items to display into an ArrayList
		loadList = new ArrayList<Item>();
		loadList.add(new Item("Hello", "There", 1, 2));
		// loadList.add(...);
		// loadList.add(...);
		
		// create ObservableList from ArrayList
		items = FXCollections.observableList(loadList);
		
		
		TableColumn<Item, String> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
		
		TableColumn<Item, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Item, Double> priceCol = new TableColumn<>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		TableColumn<Item, Integer> quantityCol = new TableColumn<>("Quantity");
		quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		results = new TableView<Item>();
		results.setItems(items);
		
		results.getColumns().addAll(idCol, nameCol, priceCol, quantityCol);
		
	}
	
	public void setUpNavigationBar() {
		
		// Navigation Tabs
		leftBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/leftArrow.png"), leftBtn.getPrefWidth()-30, leftBtn.getPrefHeight()-30, true, true)));
		rightBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/rightArrow.png"), rightBtn.getPrefWidth()-30, rightBtn.getPrefHeight()-30, true, true)));
		homeBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/logo.png"), homeBtn.getPrefWidth()-30, homeBtn.getPrefHeight()-30, true, true)));
		searchBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/search.png"), searchBtn.getPrefWidth()-30, searchBtn.getPrefHeight()-30, true, true)));
		settingsBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/settings.png"), settingsBtn.getPrefWidth()-30, settingsBtn.getPrefHeight()-30, true, true)));
		cartBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/cart.png"), cartBtn.getPrefWidth(), cartBtn.getPrefHeight()-10, true, true)));
		
		if(MainController.isLoggedIn == false) {
			loginBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/person.png"), loginBtn.getPrefWidth()-10, loginBtn.getPrefHeight()-30, true, true)));
		}
		
		else {
			loginBtn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("../Images/login.png"), loginBtn.getPrefWidth()-10, loginBtn.getPrefHeight()-30, true, true)));
		}
		
		// Search Options
		options.add("All");
		options.add("Produce");
		options.add("Grains");
		options.add("Drinks");
		options.add("Snacks");
		
		// set options for combo box
		ObservableList<String> observableOptions = FXCollections.observableArrayList(options);
		optionsComboBox.setItems(observableOptions);
		optionsComboBox.getSelectionModel().selectFirst();
		optionsComboBox.getSelectionModel().select(MainController.selectedOption);
	}
	
}