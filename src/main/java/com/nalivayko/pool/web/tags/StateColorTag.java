package com.nalivayko.pool.web.tags;

import com.nalivayko.pool.model.enums.ReviewStatus;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class StateColorTag extends SimpleTagSupport {

    private static final String SUCCESS_TEXT_COLOR_CLASS = "text-success";
    private static final String DANGER_TEXT_COLOR_CLASS = "text-danger";
    private static final String format = "<span class=\"%s\">%s</span>";
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void doTag() throws JspException, IOException {
        String color;
        switch (ReviewStatus.valueOf(status)) {
            case ACCEPTED: {
                color = SUCCESS_TEXT_COLOR_CLASS;
                break;
            }
            case DECLINED: {
                color = DANGER_TEXT_COLOR_CLASS;
                break;
            }
            default: {
                color = "";
                break;
            }
        }
        getJspContext().getOut().write(String.format(format, color, status));
    }
}
