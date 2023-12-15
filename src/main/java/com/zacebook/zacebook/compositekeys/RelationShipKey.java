package com.zacebook.zacebook.compositekeys;

import com.zacebook.zacebook.tables.User;
import java.io.Serializable;

public class RelationShipKey implements Serializable {
    private User source;
    private User target;

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }
}
