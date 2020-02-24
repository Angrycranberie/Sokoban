package Vue;

import java.awt.*;

// Classe servant uniquement à casser la dépendance à Swing
public class ImageSokoban {
    Image img;

    ImageSokoban(Image i) {
        img = i;
    }

    Image image() {
        return img;
    }
}
