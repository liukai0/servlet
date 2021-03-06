/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.seam.servlet.http;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.seam.servlet.event.ImplicitServletObjectsHolder;

/**
 * A producer for implicit HTTP Servlet objects, specifically the {@link HttpServletRequest}, {@link HttpServletResponse} and
 * {@link HttpSession}. References are obtained from the {@link ImplicitServletObjectsHolder}.
 * <p/>
 * TODO should probably throw IllegalStateException if accessed outside request
 *
 * @author Nicklas Karlsson
 * @author <a href="http://community.jboss.org/people/dan.j.allen">Dan Allen</a>
 */
public class ImplicitHttpServletObjectsProducer implements Serializable {
    @Inject
    private ImplicitServletObjectsHolder holder;

    @Produces
    @RequestScoped
    protected HttpSession getHttpSession() {
        return holder.getHttpSession();
    }

    @Produces
    @Typed(HttpServletRequestContext.class)
    @RequestScoped
    protected HttpServletRequestContext getHttpServletRequestContext() {
        return holder.getHttpServletRequestContext();
    }

    @Produces
    @Typed(HttpServletRequest.class)
    @RequestScoped
    protected HttpServletRequest getHttpServletRequest() {
        return holder.getHttpServletRequest();
    }

    @Produces
    @Typed(HttpServletResponse.class)
    @RequestScoped
    protected HttpServletResponse getHttpServletResponse() {
        return holder.getHttpServletResponse();
    }

    @Produces
    @RequestScoped
    protected List<Cookie> getCookies() {
        return Arrays.asList(getHttpServletRequest().getCookies());
    }

    @Produces
    @ContextPath
    protected String getContextPath() {
        return getHttpServletRequest().getContextPath();
    }
}
