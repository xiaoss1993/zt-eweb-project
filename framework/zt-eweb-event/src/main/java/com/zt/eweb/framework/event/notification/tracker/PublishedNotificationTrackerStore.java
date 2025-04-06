

package com.zt.eweb.framework.event.notification.tracker;

import com.zt.eweb.framework.event.notification.Notification;

import java.util.List;


public interface PublishedNotificationTrackerStore {

    public PublishedNotificationTracker publishedNotificationTracker();

    public PublishedNotificationTracker publishedNotificationTracker(String aTypeName);

    public void trackMostRecentPublishedNotification(PublishedNotificationTracker aPublishedNotificationTracker, List<Notification> aNotifications);

    public String typeName();
}
