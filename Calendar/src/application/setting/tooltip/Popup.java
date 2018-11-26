package application.setting.tooltip;

import javafx.scene.control.Tooltip;

public class Popup extends Tooltip {
	public Popup(String message) {
		this.setStyle(setStyleCSS());
		this.setOpacity(0.8);
		this.setText(message);

	}

	private String setStyleCSS() {
		String css = "-fx-background-color: #000033; -fx-font-size: 18.0px; -fx-font-family: 'Arial Narrow'; -fx-font-weight: bold; -fx-text-fill: #FEFEFE;";
		return css;
	}
}
