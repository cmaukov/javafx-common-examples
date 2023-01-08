package com.bmstechpro.javafxexamples;
/* javafx-examples
 * @created 01/07/2023
 * @author Konstantin Staykov
 */

import javafx.scene.Node;

public class DraggableMaker {
    private double mouseAnchorX;
    private double mouseAnchorY;

    public void makeDraggable(Node node) {
        node.setOnMousePressed(event -> {
            mouseAnchorX = event.getSceneX() - node.getTranslateX();
            mouseAnchorY = event.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(event -> {
            System.out.println("X:" + (event.getSceneX() - mouseAnchorX));
            System.out.println("Y:" + (event.getSceneY() - mouseAnchorY));
            int v =(int) (event.getSceneY() - mouseAnchorY) % 15; // helper to snap to coordinate like a grid
//            System.out.println("v = " + v);
//            if(v!=0){
//                System.out.println("calc Y: "+((event.getSceneY() - mouseAnchorY)-v) );
//            }

            node.setTranslateX(event.getSceneX() - mouseAnchorX);
//            node.setTranslateY(event.getSceneY() - mouseAnchorY);
            node.setTranslateY(event.getSceneY() - mouseAnchorY-v);
        });
    }

}
