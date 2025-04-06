

package com.zt.eweb.framework.event.notification;


import com.zt.eweb.framework.event.store.EventStore;
import com.zt.eweb.framework.event.store.StoredEvent;
import com.zt.eweb.framework.common.base.model.DomainEvent;

import java.util.ArrayList;
import java.util.List;


public class NotificationLogFactory {

    // this could be a configuration
    private static final int NOTIFICATIONS_PER_LOG = 2;

    private EventStore eventStore;

    public static int notificationsPerLog() {
        return NOTIFICATIONS_PER_LOG;
    }

    public NotificationLogFactory(EventStore anEventStore) {
        super();

        this.setEventStore(anEventStore);
    }

    public NotificationLog createCurrentNotificationLog() {
        return this.createNotificationLog(
                this.calculateCurrentNotificationLogId(eventStore));
    }

    public NotificationLog createNotificationLog(
            NotificationLogId aNotificationLogId) {

        long count = this.eventStore().countStoredEvents();

        NotificationLogInfo info = new NotificationLogInfo(aNotificationLogId, count);

        return this.createNotificationLog(info);
    }

    private NotificationLogInfo calculateCurrentNotificationLogId(
            EventStore anEventStore) {

        long count = anEventStore.countStoredEvents();

        long remainder = count % NOTIFICATIONS_PER_LOG;

        if (remainder == 0 && count > 0) {
            remainder = NOTIFICATIONS_PER_LOG;
        }

        long low = count - remainder + 1;

        // ensures a minted id value even though there may
        // not be a full set of notifications at present
        long high = low + NOTIFICATIONS_PER_LOG - 1;

        return new NotificationLogInfo(new NotificationLogId(low, high), count);
    }

    private NotificationLog createNotificationLog(
            NotificationLogInfo aNotificationLogInfo) {

        List<StoredEvent> storedEvents =
                this.eventStore().allStoredEventsBetween(
                        aNotificationLogInfo.notificationLogId().low(),
                        aNotificationLogInfo.notificationLogId().high());

        boolean archivedIndicator =
                aNotificationLogInfo.notificationLogId().high() < aNotificationLogInfo.totalLogged();

        NotificationLogId next = archivedIndicator ?
                aNotificationLogInfo.notificationLogId().next(NOTIFICATIONS_PER_LOG) :
                null;

        NotificationLogId previous =
                aNotificationLogInfo.notificationLogId().previous(NOTIFICATIONS_PER_LOG);

        NotificationLog notificationLog =
                new NotificationLog(
                        aNotificationLogInfo.notificationLogId().encoded(),
                        NotificationLogId.encoded(next),
                        NotificationLogId.encoded(previous),
                        this.notificationsFrom(storedEvents),
                        archivedIndicator);

        return notificationLog;
    }

    private List<Notification> notificationsFrom(List<StoredEvent> aStoredEvents) {
        List<Notification> notifications =
                new ArrayList<Notification>(aStoredEvents.size());

        for (StoredEvent storedEvent : aStoredEvents) {
            DomainEvent domainEvent = storedEvent.toDomainEvent();

            Notification notification =
                    new Notification(storedEvent.eventId(), domainEvent);

            notifications.add(notification);
        }

        return notifications;
    }

    private EventStore eventStore() {
        return eventStore;
    }

    private void setEventStore(EventStore anEventStore) {
        this.eventStore = anEventStore;
    }
}
