package com.nalivayko.pool.repair_shop.web.tags;

import com.nalivayko.pool.repair_shop.model.enums.ReviewStatus;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Tag that changes color of ReviewStatus
 */
public class StateColorTag extends SimpleTagSupport {

    private static final String SUCCESS_TEXT_COLOR_CLASS = "text-success";
    private static final String DANGER_TEXT_COLOR_CLASS = "text-danger";
    private static final String format = "<span class=\"%s\">%s</span>";
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void doTag() throws IOException {
        String color;
        switch (ReviewStatus.valueOf(status)) {
            case ACCEPTED: {
                color = SUCCESS_TEXT_COLOR_CLASS;
                break;
            }
            case REJECTED: {
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
