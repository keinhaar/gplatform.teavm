package de.exware.gplatform.teavm.element;

import org.teavm.jso.canvas.CanvasImageSource;
import org.teavm.jso.canvas.CanvasRenderingContext2D;

import de.exware.gplatform.element.GPContext2d;
import de.exware.gplatform.element.GPImageElement;

public class TeavmGPContext2d implements GPContext2d
{
	private CanvasRenderingContext2D nativeRenderingContext2D;
	public TeavmGPContext2d(CanvasRenderingContext2D canvasRenderingContext2D) {
		this.nativeRenderingContext2D = nativeRenderingContext2D;
	}

    @Override
    public void setTransform(double i, double j, double k, double l, double m, double n)
    {
    	nativeRenderingContext2D.setTransform(i, j, k, l, m, n);
    }

    @Override
    public void save()
    {
    	nativeRenderingContext2D.save();
    }

    @Override
    public void translate(double d, double e)
    {
    	nativeRenderingContext2D.translate(d, e);
    }

    @Override
    public void moveTo(double x1, double y1)
    {
    	nativeRenderingContext2D.moveTo(x1, y1);
    }

    @Override
    public void lineTo(int x2, int y2)
    {
    	nativeRenderingContext2D.lineTo(x2, y2);
    }

    @Override
    public void stroke()
    {
    	nativeRenderingContext2D.stroke();
    }

    @Override
    public void restore()
    {
    	nativeRenderingContext2D.restore();
    }

    @Override
    public void rect(int x, int y, int width, int height)
    {
    	nativeRenderingContext2D.rect(x, y, width, height);
    }

    @Override
    public void beginPath()
    {
    	nativeRenderingContext2D.beginPath();
    }

    @Override
    public void bezierCurveTo(double x, double d, double e, double y, double xm, double y2)
    {
    	nativeRenderingContext2D.bezierCurveTo(x, y, e, y, xm, y2);
    }

    @Override
    public void fill()
    {
    	nativeRenderingContext2D.fill();
    }

    @Override
    public void fillRect(int x, int y, int width, int height)
    {
    	nativeRenderingContext2D.fillRect(x, y, width, height);
    }

    @Override
    public void fillText(String text, int x, int y)
    {
    	nativeRenderingContext2D.fillText(text, x, y);
    }

    @Override
    public void setStrokeColor(String hexColor)
    {
    	nativeRenderingContext2D.setStrokeStyle(hexColor);
    }

    @Override
    public void setFillColor(String hexColor)
    {
    	nativeRenderingContext2D.setFillStyle(hexColor);
    }

    @Override
    public void rotate(double i)
    {
    	nativeRenderingContext2D.rotate(i);
    }

    @Override
    public void scale(double x, double y)
    {
    	nativeRenderingContext2D.scale(x, y);
    }

    @Override
    public void setFont(String cssFontDescription)
    {
    	nativeRenderingContext2D.setFont(cssFontDescription);
    }

    @Override
    public void drawImage(GPImageElement imageElement, double x, double y)
    {
    	nativeRenderingContext2D.drawImage((CanvasImageSource) ((TeavmGPImageElement) imageElement).getNativeElement(), x, y);
    }

    @Override
    public void drawImage(GPImageElement imageElement, double x, double y, double w, double h)
    {
    	nativeRenderingContext2D.drawImage((CanvasImageSource) ((TeavmGPImageElement) imageElement).getNativeElement(), x, y, w, h);
    }

    @Override
    public void drawImage(GPImageElement imageElement, double srcX, double srcY, double srcH, double srcW, double x,
        double y, double w, double h)
    {
    	nativeRenderingContext2D.drawImage((CanvasImageSource) ((TeavmGPImageElement) imageElement).getNativeElement(), srcX, srcY, srcW, srcH, x, y, w, h);
    }
}
