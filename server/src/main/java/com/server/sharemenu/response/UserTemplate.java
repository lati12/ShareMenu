package com.server.sharemenu.response;

import com.server.sharemenu.common.Template;
import com.server.sharemenu.common.User;

public class UserTemplate {
    private User user;
    private Template template;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
