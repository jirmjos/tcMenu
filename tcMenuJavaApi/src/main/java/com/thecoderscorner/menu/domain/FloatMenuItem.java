/*
 * Copyright (c) 2018 https://www.thecoderscorner.com (Nutricherry LTD).
 * This product is licensed under an Apache license, see the LICENSE file in the top-level directory.
 */

package com.thecoderscorner.menu.domain;

import com.thecoderscorner.menu.domain.state.FloatMenuState;
import com.thecoderscorner.menu.domain.state.MenuState;
import com.thecoderscorner.menu.domain.util.MenuItemVisitor;

import java.util.Objects;

/**
 * FloatMenuItem represents a menu item that uses a floating point value. It is not editable on the device
 * because it does not really represent absolute values, but is sometimes useful for conveying status.
 */
public class FloatMenuItem extends MenuItem<Float> {

    private final int numDecimalPlaces;

    public FloatMenuItem() {
        super("", -1, -1, null, false);
        // needed for serialisation
        this.numDecimalPlaces = 0;
    }

    public FloatMenuItem(String name, int id, String functionName, int eepromAddr, int numDecimalPlaces) {
        super(name, id, eepromAddr, functionName, false);
        this.numDecimalPlaces = numDecimalPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatMenuItem that = (FloatMenuItem) o;
        return getNumDecimalPlaces() == that.getNumDecimalPlaces() &&
                getId() == that.getId() &&
                getEepromAddress() == that.getEepromAddress() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getFunctionName(), that.getFunctionName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumDecimalPlaces(), getName(), getId(), getEepromAddress(), getFunctionName());
    }

    public int getNumDecimalPlaces() {
        return numDecimalPlaces;
    }

    @Override
    public MenuState<Float> newMenuState(Float value, boolean changed, boolean active) {
        return new FloatMenuState(changed, active, value);
    }

    @Override
    public void accept(MenuItemVisitor visitor) {
        visitor.visit(this);
    }
}
