import com.twk.tank.ResourceMgr;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.Assert.*;

public class ResourceMgrTest {

    @Test
    public void testImageLoading() {
        try {
            BufferedImage  image = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("D:\\JavaIdeaProject\\TankOnlinee\\src\\images\\tankL.gif"));
            assertNotNull(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

