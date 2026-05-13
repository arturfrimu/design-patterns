package com.arturfrimu.pattern.proxy.image;

public class ProxyImage implements Image {
    private ImageImpl realImage;
    private final String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new ImageImpl(filename);
        }
        realImage.display();
    }
}
