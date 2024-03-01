package org.firstinspires.ftc.teamcode.LearnJavaForFTCTextbook.Ch16ComputerVision;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

// OpenCV & AprilTag for Computer Vision that I need to know

public class FirstVisionProcessor implements VisionProcessor {

    // This creates a rectangle in camera coordinates (x & y in left corner)
    public Rect rectLeft = new Rect(110, 42, 40, 40);
    public Rect rectMiddle = new Rect(160, 42, 40, 40);
    public Rect rectRight = new Rect(210, 42, 40, 40);
    Selected selection = Selected.NONE;

    Mat submat = new Mat();
    Mat hsvMat = new Mat();

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
    }

    // Called once per frame of video. Mat = Matrix, which is an OpenCV type
    // You don't want to modify the frame being passed in because all other processors will also be getting the same frame (diff from typical OpenCV pipeline)
    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        // Converts color space from RGB to HSV (Hue, Saturation, Value). Useful because the background is gray mats in FTC
        // Hue is the shade of color, saturation is intensity of color, and value is how bright it is
        Imgproc.cvtColor(frame, hsvMat, Imgproc.COLOR_RGB2HSV);

        // Gets average saturation of each rectangle by calling a made to get average saturation
        double satRectLeft = getAvgSaturation(hsvMat, rectLeft);
        double satRectMiddle = getAvgSaturation(hsvMat, rectMiddle);
        double satRectRight = getAvgSaturation(hsvMat, rectRight);

        // Returns which rectangle had the highest saturation (least gray). In doing this, we can detect any type of TSE (I think that just means anything other than the mats)
        if ((satRectLeft > satRectMiddle) && (satRectLeft > satRectRight)) {
            return Selected.LEFT;
        } else if ((satRectMiddle > satRectLeft) && (satRectMiddle > satRectRight)) {
            return Selected.MIDDLE;
        }
        return Selected.RIGHT; // If we don't see anything, default is RIGHT
    }

    // Creates a sub-matrix based off the rectangle passed in. Then gets the average of each pixel in this rectangle.
    // Since we are working in HSV, the 0th is Hue, the 1st is Saturation, and the 2nd is Value
    protected double getAvgSaturation(Mat input, Rect rect) {
        submat = input.submat(rect);
        Scalar color = Core.mean(submat);
        return color.val[1];
    }

    // The Rect here should be the OpenCV one, not the android.graphics one
    // Converts from our OpenCV camera rectangle to an android.graphics.Rect. Diff bc OpenCV has top, left, width, height; android.graphics.Rect has left, top, right, bottom
    // OpenCV uses camera coordinates, while android one uses screen coordinates
    private android.graphics.Rect makeGraphicsRect(Rect rect, float scaleBmpPxToCanvasPx) {
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
        int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);

        return new android.graphics.Rect(left, top, right, bottom);
    }

    // Allows us to draw on top of frame to be seen on the camera view and on the image you can see on the Driver Station
    // Can see camera on Driver Station when in init, plug into Control Hub and click 3 dots for camera stream
    // canvas = Canvas we will draw on; onScreenWidth - width of canvas in pixels (same with height); scaleBmpPxToCanvasPx - helps us convert from the
    // coordinates in the processFrame to those on the canvas; scaleCanvasDensity - lets us draw text annotations that are the same regardless of screen size;
    // userContext - this has the object that was returned by the previous processFrame
    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        Paint selectedPaint = new Paint(); // Selected rectangle color for rectangle that's selected by the camera
        selectedPaint.setColor(Color.RED);
        selectedPaint.setStyle(Paint.Style.STROKE); // Just does an outline, instead of filling it too
        selectedPaint.setStrokeWidth(scaleCanvasDensity * 4);

        Paint nonSelectedPaint = new Paint(selectedPaint);
        nonSelectedPaint.setColor(Color.GREEN);

        android.graphics.Rect drawRectangleLeft = makeGraphicsRect(rectLeft, scaleBmpPxToCanvasPx);
        android.graphics.Rect drawRectangleMiddle = makeGraphicsRect(rectMiddle, scaleBmpPxToCanvasPx);
        android.graphics.Rect drawRectangleRight = makeGraphicsRect(rectRight, scaleBmpPxToCanvasPx);

        selection = (Selected) userContext; // takes the userContext and cast it to what we know is returned (our enumerated type) and save it to the class

        switch(selection) {
            case LEFT:
                canvas.drawRect(drawRectangleLeft, selectedPaint);
                canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
                canvas.drawRect(drawRectangleRight, nonSelectedPaint);
                break;
            case MIDDLE:
                canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
                canvas.drawRect(drawRectangleMiddle, selectedPaint);
                canvas.drawRect(drawRectangleRight, nonSelectedPaint);
                break;
            case RIGHT:
                canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
                canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
                canvas.drawRect(drawRectangleRight, selectedPaint);
                break;
            case NONE:
                canvas.drawRect(drawRectangleLeft, nonSelectedPaint);
                canvas.drawRect(drawRectangleMiddle, nonSelectedPaint);
                canvas.drawRect(drawRectangleRight, nonSelectedPaint);
                break;
        }
    }

    public Selected getSelection() {
        return selection;
    }

    public enum Selected {
        NONE,
        LEFT,
        MIDDLE,
        RIGHT
    }

}
