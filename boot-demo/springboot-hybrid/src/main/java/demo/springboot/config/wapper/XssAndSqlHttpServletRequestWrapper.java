package demo.springboot.config.wapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author LILUO
 * @date 2020/07/08
 */
public class XssAndSqlHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public XssAndSqlHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        String value = request.getParameter(name);
        if (!StringUtils.isEmpty(value)) {
            value = StringEscapeUtils.escapeHtml4(value);
        }
        return value;
    }
    @Override
    public String[] getParameterValues(String name) {
        String[] parameterValues = super.getParameterValues(name);
        if (parameterValues == null) {
            return null;
        }
        for (int i = 0; i < parameterValues.length; i++) {
            String value = parameterValues[i];
            parameterValues[i] = StringEscapeUtils.escapeHtml4(value);
        }
        return parameterValues;
    }
}
