

package com.zt.eweb.framework.event.stream.dispatch;

import lombok.AccessLevel;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.zt.eweb.framework.event.stream.DispatchableDomainEvent;
import com.zt.eweb.framework.common.base.model.DomainEvent;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public abstract class AbstractProjection implements EventStreamDispatcher {

    private static final String PROJECTION_METHOD_NAME = "when";

    private static Map<String, Method> projectionMethods =
            new HashMap<String, Method>();

    @Autowired
    @Qualifier("parentEventStreamDispatcher")
    @Setter(AccessLevel.PROTECTED)
    protected EventStreamDispatcher parentEventStreamDispatcher;

    @PostConstruct
    public void init() {
        parentEventStreamDispatcher.registerEventDispatcher(this);
    }

    protected AbstractProjection() {
        super();
    }

    protected void projectWhen(DispatchableDomainEvent aDispatchableDomainEvent) {

        if (!this.understands(aDispatchableDomainEvent)) {
            return;
        }

//        System.out.println("Dispatching: " + aDispatchableDomainEvent.domainEvent().getClass().getSimpleName());

        DomainEvent domainEvent = aDispatchableDomainEvent.domainEvent();

        Class<? extends AbstractProjection> rootType = this.getClass();

        Class<? extends DomainEvent> eventType = domainEvent.getClass();

        String key = rootType.getName() + ":" + eventType.getName();

        Method mutatorMethod = projectionMethods.get(key);

        if (mutatorMethod == null) {
            mutatorMethod = this.cacheProjectionMethodFor(key, rootType, eventType);
        }

        try {
            mutatorMethod.invoke(this, domainEvent);

        } catch (InvocationTargetException e) {
            if (e.getCause() != null) {
                throw new RuntimeException(
                        "Method "
                                + PROJECTION_METHOD_NAME
                                + "("
                                + eventType.getSimpleName()
                                + ") failed. See cause: "
                                + e.getMessage(),
                        e.getCause());
            }

            throw new RuntimeException(
                    "Method "
                            + PROJECTION_METHOD_NAME
                            + "("
                            + eventType.getSimpleName()
                            + ") failed. See cause: "
                            + e.getMessage(),
                    e);

        } catch (IllegalAccessException e) {
            throw new RuntimeException(
                    "Method "
                            + PROJECTION_METHOD_NAME
                            + "("
                            + eventType.getSimpleName()
                            + ") failed because of illegal access. See cause: "
                            + e.getMessage(),
                    e);
        }
    }

    protected boolean understandsAnyOf(
            Class<?> aDispatchedType,
            Class<?>[] anUnderstoodEventTypes) {

        for (Class<?> eventType : anUnderstoodEventTypes) {
            if (aDispatchedType == eventType) {
                return true;
            }
        }

//        System.out.println(this.getClass().getSimpleName() + " doesn't understand: " + aDispatchedType.getSimpleName());

        return false;
    }

    private Method cacheProjectionMethodFor(
            String aKey,
            Class<? extends AbstractProjection> aRootType,
            Class<? extends DomainEvent> anEventType) {

        synchronized (projectionMethods) {
            try {
                Method method = this.hiddenOrPublicMethod(aRootType, anEventType);

                method.setAccessible(true);

                projectionMethods.put(aKey, method);

                return method;

            } catch (Exception e) {
                throw new IllegalArgumentException(
                        "I do not understand "
                                + PROJECTION_METHOD_NAME
                                + "("
                                + anEventType.getSimpleName()
                                + ") because: "
                                + e.getClass().getSimpleName() + ">>>" + e.getMessage(),
                        e);
            }
        }
    }

    private Method hiddenOrPublicMethod(
            Class<? extends AbstractProjection> aRootType,
            Class<? extends DomainEvent> anEventType)
            throws Exception {

        Method method = null;

        try {

            // assume protected or private...

            method = aRootType.getDeclaredMethod(
                    PROJECTION_METHOD_NAME,
                    anEventType);

        } catch (Exception e) {

            // then public...

            method = aRootType.getMethod(
                    PROJECTION_METHOD_NAME,
                    anEventType);
        }

        return method;
    }
}
