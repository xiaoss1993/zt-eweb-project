package com.zt.eweb.framework.common.domain;

import java.io.Serializable;

/**
 * Entity interface
 *
 *
 * @date 2021-02-01
 **/
public interface Entity<T> extends Serializable {

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param other The other entity.
     * @return true if the identities are the same, regardless of other attributes.
     */
    boolean sameIdentityAs(T other);
}
