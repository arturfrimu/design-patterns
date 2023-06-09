package com.arturfrimu.pattern.proxy.image;

public class ProxyPatternDemo {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("Image1.jpg");
        Image image2 = new ProxyImage("Image2.jpg");

        // Image will be loaded and displayed
        image1.display();
        // Image will be displayed without loading
        image1.display();
        // Image will be loaded and displayed
        image2.display();
        // Image will be displayed without loading
        image2.display();
    }
}
