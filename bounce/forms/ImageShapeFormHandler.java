package bounce.forms;

import bounce.ImageRectangleShape;
import bounce.NestingShape;
import bounce.ShapeModel;
import bounce.forms.util.Form;
import bounce.forms.util.FormHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageShapeFormHandler  implements FormHandler {
    private ShapeModel _model;
    private NestingShape _parentOfNewShape;

    public ImageShapeFormHandler(ShapeModel model, NestingShape parent){
        _model = model;
        _parentOfNewShape = parent;
    }

    @Override
    public void processForm(Form form) {
        long startTime = System.currentTimeMillis();
        //ImageRectangleShape imageShape;


        //SwingWorker<Image, Void> output = new SwingWorker<Image, Void>()

        class output extends SwingWorker<Image, Void>{
            File imageFile = (File)form.getFieldValue(File.class, ImageFormElement.IMAGE);
            int width = form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
            int deltaX = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
            int deltaY = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);


            @Override
            protected Image doInBackground() throws Exception {

                //System.out.println(imageFile.getAbsolutePath());
                // Read field values from the form.

                // Load the original image (ImageIO.read() is a blocking call).
                BufferedImage fullImage = null;
                try {
                    fullImage = ImageIO.read(imageFile);
                } catch(IOException e) {
                    System.out.println(e);
                    System.out.println("Error loading image.");
                }

                int fullImageWidth = fullImage.getWidth();
                int fullImageHeight = fullImage.getHeight();

                BufferedImage scaledImage = fullImage;

                // Scale the image if necessary.
                if(fullImageWidth > width) {
                    double scaleFactor = (double)width / (double)fullImageWidth;
                    int height = (int)((double)fullImageHeight * scaleFactor);
                    scaledImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = scaledImage.createGraphics();
                    g.drawImage(fullImage, 0, 0, width, height, null);

                }

                return scaledImage; //new ImageRectangleShape(deltaX, deltaY, scaledImage);
            }

            @Override
            protected void done(){
                try{
                    Image image = get();

                    ImageRectangleShape imageShape = new ImageRectangleShape(deltaX, deltaY, image);
                    _model.add(imageShape, _parentOfNewShape);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        (new output()).execute();

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Image loading and scaling took " + elapsedTime + "ms.");
    }


}
