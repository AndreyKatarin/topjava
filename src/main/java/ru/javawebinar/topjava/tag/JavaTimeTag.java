package ru.javawebinar.topjava.tag;


import ru.javawebinar.topjava.util.DateUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;

public class JavaTimeTag extends TagSupport {

    private LocalDateTime value;
    private String pattern;

    public void setValue(LocalDateTime value) {
        this.value = value;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String to = DateUtil.parseLocalDateTime(value, pattern);

            pageContext.getOut().write(to);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
