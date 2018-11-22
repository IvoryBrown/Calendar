package application.setting.tooltip;

import javafx.scene.control.Tooltip;

public class Popup extends Tooltip {
	public Popup(String message) {
		this.setOpacity(0.8);
		this.setText(message);
		
	}
}
