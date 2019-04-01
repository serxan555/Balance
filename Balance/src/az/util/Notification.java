/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.util;


import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

/**
 *
 * @author SS555
 */
public class Notification {
    public static void callNotification(String text, String title) {
        Notifications notifications = Notifications.create()
                .text(text)
                .title(title)
                .position(Pos.TOP_RIGHT)
                .darkStyle()
                .hideAfter(javafx.util.Duration.seconds(3));
        notifications.showInformation();
    }
}
