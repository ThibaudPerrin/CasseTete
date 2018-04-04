package casseTete.view;

import casseTete.model.Symbole;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CasePane extends Pane {
	
	
	public CasePane(Symbole s) {
		Image image = new Image("File:image/../"+s+".png");
		ImageView pic = new ImageView(image);
		pic.setFitWidth(100);
		pic.setFitHeight(100);
		this.getChildren().addAll(pic);
	}
	
}
