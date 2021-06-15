package com.servlet.filters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EncodingFilterTest {

    @InjectMocks
    private EncodingFilter encodingFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private ServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpSession session;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(ServletResponse.class);
        filterChain = mock(FilterChain.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void doFilter() throws IOException, ServletException {
        when(request.getParameter("sessionLocale")).thenReturn("ua");
        when(request.getSession()).thenReturn(session);
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");
        when(response.getCharacterEncoding()).thenReturn("UTF-8");
        when(request.getSession().getAttribute("lang")).thenReturn("ua");

        encodingFilter.doFilter(request, response, filterChain);

        verify(response, times(1)).setContentType("text/html;charset=UTF-8");
        verify(response, times(1)).setCharacterEncoding("UTF-8");
        Assert.assertEquals(response.getContentType(), "text/html;charset=UTF-8");
        Assert.assertEquals(response.getCharacterEncoding(), "UTF-8");
        Assert.assertEquals(request.getSession().getAttribute("lang"),"ua");

        verify(filterChain, times(1)).doFilter(eq(request), eq(response));
    }
}